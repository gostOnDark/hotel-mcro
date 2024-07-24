package com.company.hotel.admin.service.impl;

import com.company.hotel.admin.entity.ResultMsg;
import com.company.hotel.admin.entity.User;
import com.company.hotel.admin.mapper.UserMapper;
import com.company.hotel.admin.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public ResultMsg changeLock(String userid,Boolean locked) {
         userMapper.changeLock(userid,locked);
        return ResultMsg.ok();
    }
    /**
     * @param pageNum 页码.
     * @param pageSize 每页数
     * @param orderField 排序字段
     * @param orderType 1升序 0 降序
     * @return
     */
    @Override
    public ResultMsg userPadgeInfo(int pageNum,int pageSize,String orderField,int orderType) {
        String orderStr = "";
        switch (orderType){
            case 1:
                orderStr=orderField+ " asc";
                break;
            case 0:
                orderStr = orderField +" desc";
            default:
                break;
        }
        PageInfo<Object> pageInfo = PageHelper.startPage(pageNum,pageSize).setOrderBy(orderStr).doSelectPageInfo(()->this.userMapper.selectAll());
        return ResultMsg.ok(pageInfo);
    }

    @Override
    public ResultMsg addUser(User user) {
        userMapper.insertSelective(user);
        return ResultMsg.ok(user);
    }

    /**
     * 更新用户信息.
     * @param user
     * @return
     */
    @Override
    public ResultMsg updateSelective(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return ResultMsg.ok(user);
    }

}
