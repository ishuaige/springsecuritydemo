package com.niuma.ucenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员姓名
     */
    private String name;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 角色：1--超级管理员  0---用户
     */
    private Integer role;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}