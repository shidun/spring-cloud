package com.imooc.user.Controller;

import com.imooc.user.constant.RedisConstant;
import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.service.impl.UserInfoServiceImpl;
import com.imooc.user.utils.CookieUtil;
import com.imooc.user.utils.ResultVoUtil;
import com.imooc.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : jack sd
 * @date : 2019/9/30
 */
@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserInfoServiceImpl userInfoService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        UserInfo userInfo = userInfoService.getByOpenid(openid);
        if (userInfo == null) {
            return ResultVoUtil.error("用户不存在");
        }
        if (userInfo.getRole() != 1) {
            return ResultVoUtil.error("权限错误");
        }
        CookieUtil.set(response, "openid", openid, 7200);
        return ResultVoUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletResponse response) {
        UserInfo userInfo = userInfoService.getByOpenid(openid);
        if (userInfo == null) {
            return ResultVoUtil.error("用户不存在");
        }
        if (userInfo.getRole() != 2) {
            return ResultVoUtil.error("权限错误");
        }

        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token), openid, 7200, TimeUnit.SECONDS);
        CookieUtil.set(response, "token", token, 7200);
        return ResultVoUtil.success();
    }
}
