package com.soft1721.jianyue.api.controller;

import com.soft1721.jianyue.api.entity.Like;
import com.soft1721.jianyue.api.entity.vo.FollowVO;
import com.soft1721.jianyue.api.entity.vo.LikeVO;
import com.soft1721.jianyue.api.service.LikeService;
import com.soft1721.jianyue.api.util.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 张文旭 on 2019/4/12.
 */
@RestController
@RequestMapping(value = "/api/like")
public class LikeController {
    @Resource
    private LikeService likeService;

    @PostMapping("/add")
    public ResponseResult likeUser(@RequestParam("fromUId") int fromUId, @RequestParam("toAId") int toAId) {
        Like like = new Like();
        like.setFromUId(fromUId);
        like.setToAId(toAId);
        likeService.insertLike(like);
        return ResponseResult.success();
    }

    @PostMapping("/cancel")
    public ResponseResult cancelLike(@RequestParam("fromUId") int fromUId, @RequestParam("toAId") int toAId) {
        likeService.deleteLike(fromUId, toAId);
        return ResponseResult.success();
    }
    @GetMapping("/list")
    public ResponseResult getLikesByUId (@RequestParam("fromUId") Integer fromUId) {
        List<LikeVO> likeVOList=likeService.getLikesByUId(fromUId);
        return  ResponseResult.success(likeVOList);
    }
}
