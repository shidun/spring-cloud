package com.imooc.user.service.impl;

import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.repository.UserInfoRepository;
import com.imooc.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jack sd
 * @date : 2019/9/30
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo getByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
