package com.dianping.hui.entity;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: lanxinyu@meituan.com  2018-10-13 下午5:20
 * @Description:
 */
@Data
public class GF {

    @NotNull
    private Integer index;
    @NotNull
    @Size(min=100,max=160)
    private String name;
    @NotNull
    private String body;

    public GF() {

    }

    public GF(Integer index, String name) {
        this.index = index;
        this.name = name;
    }
}
