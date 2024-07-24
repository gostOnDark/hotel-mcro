package com.company.hotel.admin.mapper;

import com.company.hotel.admin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据名字查询用户信息.
     * @param name
     * @return
     */
    User findUserByName(@Param("name") String name);

    /**
     * 锁定/解锁用户
     * @param locked
     * @return
     */
    int changeLock(@Param("id") String userid, @Param("locked") Boolean locked);
}
