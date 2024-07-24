package com.company.hotel.admin.entity;

import com.company.hotel.admin.utils.UUIdGenId;
import lombok.Data;
import lombok.extern.java.Log;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 自增ID */
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;
    /** 账号 */
    private String username;
    /** 密码 */
    private String password;
    private String usercode;
    private String tel;
    /** 角色名：Shiro 支持多个角色，而且接收参数也是 Set<String> 集合，但这里为了简单起见定义成 String 类型了 */
    private String rolename;
    /** 是否禁用 */
    private boolean locked;
}
