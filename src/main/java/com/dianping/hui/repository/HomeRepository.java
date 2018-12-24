package com.dianping.hui.repository;

import java.util.Random;

/**
 * @author: lanxinyu@meituan.com  2018-10-12 下午6:04
 * @Description:
 */
public interface HomeRepository {
    default Integer getNumOfPeople(){
        return new Random().nextInt(10);
    }
}
