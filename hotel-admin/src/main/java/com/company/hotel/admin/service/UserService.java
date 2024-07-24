package com.company.hotel.admin.service;

import com.company.hotel.admin.entity.ResultMsg;
import com.company.hotel.admin.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务.
 */
public interface UserService {
    public ResultMsg changeLock(String id, Boolean locked);
    public ResultMsg userPadgeInfo(int pageNum,int pageSize,String orderField,int orderType);

    public ResultMsg addUser(User user);

    public ResultMsg updateSelective(User user);
}
