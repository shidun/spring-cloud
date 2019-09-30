package com.imooc.user.service;

import com.imooc.user.dataobject.UserInfo;

/**
 * @author : jack sd
 * @date : 2019/9/30
 */
public interface UserInfoService {

    UserInfo getByOpenid(String openid);
}
