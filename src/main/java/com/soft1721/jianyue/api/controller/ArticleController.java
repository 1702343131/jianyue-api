package com.soft1721.jianyue.api.controller;

import com.github.pagehelper.PageHelper;
import com.soft1721.jianyue.api.entity.Article;
import com.soft1721.jianyue.api.entity.Follow;
import com.soft1721.jianyue.api.entity.Like;
import com.soft1721.jianyue.api.entity.vo.ArticleVO;
import com.soft1721.jianyue.api.entity.vo.CommentVO;
import com.soft1721.jianyue.api.service.*;
import com.soft1721.jianyue.api.util.MsgConst;
import com.soft1721.jianyue.api.util.PoiUtil;
import com.soft1721.jianyue.api.util.ResponseResult;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张文旭 on 2019/4/8.
 */
@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private CommentService commentService;
    @Resource
    private ImgService imgService;
    @Resource
    private FollowService followService;
    @Resource
    private LikeService likeService;
    @GetMapping(value = "/list")
    public ResponseResult getAll() {
        List<ArticleVO> articleList = articleService.selectAll();
        return ResponseResult.success(articleList);
    }

    @GetMapping(value = "/{aId}")
    public ResponseResult getArticleById(@PathVariable("aId") int aId,@RequestParam("userId") int userId) {
        ArticleVO article = articleService.getArticleById(aId);
        int toUId = article.getUId();
        int toAId=article.getId();

        Map<String, Object> map = new HashMap<>();
        Follow follow = followService.getFollow(userId, toUId);
        if (follow != null) {
            map.put("followed", MsgConst.FOLLOWED);
        } else {
            map.put("followed", MsgConst.NO_FOLLOWED);
        }
        Like like = likeService.getLike(userId, toAId);
        if (like != null) {
            map.put("Likeed", MsgConst.LIKEED);
        } else {
            map.put("Likeed", MsgConst.NO_LIKEED);
        }

        List<CommentVO> comments = commentService.selectCommentsByAId(aId);
        map.put("article", article);
        map.put("comments", comments);
        return ResponseResult.success(map);
    }
    @PostMapping("/add")
    public ResponseResult postArticle(@RequestParam("uId") int uId,
                                      @RequestParam("title") String title,
                                      @RequestParam("content") String content) {
        Article article = new Article();
        article.setUId(uId);
        article.setTitle(title);
        article.setContent(content);
        article.setCreateTime(new Date());
        articleService.insertArticle(article);
        //新增文章后，将获取到的自增主键返回给客户端，方便图片地址的写入
        return ResponseResult.success(article.getId());
    }
    @GetMapping("/user")
    public ResponseResult getArticleByuId (@RequestParam("uId") int uId) {
        List<ArticleVO> articleVOList=articleService.getArticleByuId(uId);
        return  ResponseResult.success(articleVOList);
    }
    @GetMapping("/delete/{id}")
    public ResponseResult deleteArticle(@PathVariable("id") int id){
        articleService.deleteArticle(id);
        return ResponseResult.success();
    }
    @GetMapping("/selectAll")
    public List<ArticleVO> selectAll(int page, int size) {
        //第一参数：第几页。 第二参数：每页几条。基于拦截器模式直接使用即可。
        PageHelper.startPage(page,size);
        return articleService.selectAll();
    }
    //创建表头
    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1,12*256);
        sheet.setColumnWidth(3,17*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);


        cell = row.createCell(1);
        cell.setCellValue("显示名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);
    }

    //生成Article表excel
    @GetMapping(value = "/getart")
    public String getArticle(HttpServletResponse response) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");
        createTitle(workbook,sheet);
        List<ArticleVO> rows = articleService.selectAll();

        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        //新增数据行，并且设置单元格数据
        int rowNum=1;
        for(ArticleVO articleVO:rows){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(articleVO.getId());
            row.createCell(1).setCellValue(articleVO.getUId());
            row.createCell(2).setCellValue(articleVO.getTitle());
            row.createCell(3).setCellValue(articleVO.getContent());
            row.createCell(4).setCellValue(articleVO.getNickname());
            //  row.createCell(5).setCellValue(articleVO.getAvatar());
            HSSFCell cell = row.createCell(3);
            cell.setCellValue(articleVO.getCreateTime());
            cell.setCellStyle(style);
            rowNum++;
        }

        String fileName = "导出excel例子.xls";

        //生成excel文件
        buildExcelFile(fileName, workbook);

        //浏览器下载excel
        buildExcelDocument(fileName,workbook,response);

        return "download excel";
    }

    //生成excel文件
    protected void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    //浏览器下载excel
    protected void buildExcelDocument(String filename,HSSFWorkbook workbook,HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}