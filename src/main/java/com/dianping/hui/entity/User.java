package com.dianping.hui.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: lanxinyu@meituan.com  2018-10-22 下午4:30
 * @Description:
 */
@Data
public class User {
    @NotNull
    private Integer id;
    @NotNull
    @Size(min=5, max=20, message="{name.size}")
    private String name;
    @NotNull
    private Integer age;
    @Size(min=5, max=20, message="email.size")
    private String email;
    private String password;
    private GF gf;
}
