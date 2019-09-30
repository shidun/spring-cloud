package com.imooc.user.repository;

import com.imooc.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : jack sd
 * @date : 2019/9/30
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByOpenid(String openid);
}
