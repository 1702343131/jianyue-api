package com.soft1721.jianyue.api.controller;

import com.soft1721.jianyue.api.entity.Follow;
import com.soft1721.jianyue.api.entity.vo.FollowVO;
import com.soft1721.jianyue.api.service.FollowService;
import com.soft1721.jianyue.api.util.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 张文旭 on 2019/4/10.
 */
@RestController
@RequestMapping(value = "/api/follow")
public class FollowController {
    @Resource
    private FollowService followService;


    @PostMapping("/add")
    public ResponseResult followUser(@RequestParam("fromUId") int fromUId, @RequestParam("toUId") int toUId) {
        Follow follow = new Follow();
        follow.setFromUId(fromUId);
        follow.setToUId(toUId);
        followService.insertFollow(follow);
        return ResponseResult.success();
    }

    @PostMapping("/cancel")
    public ResponseResult cancelFollow(@RequestParam("fromUId") int fromUId, @RequestParam("toUId") int toUId) {
        followService.deleteFollow(fromUId, toUId);
        return ResponseResult.success();
    }
    @GetMapping("/list")
    public ResponseResult getFollowsByUId (@RequestParam("fromUId") Integer fromUId) {
        List<FollowVO> followVOList=followService.getFollowsByUId(fromUId);
        return  ResponseResult.success(followVOList);
    }
    @GetMapping("/xhfollow")
    public ResponseResult getFollow (@RequestParam("fromUId") Integer fromUId) {
        Follow follow = followService.getFollow1(fromUId);
        return  ResponseResult.success(follow);
    }
}