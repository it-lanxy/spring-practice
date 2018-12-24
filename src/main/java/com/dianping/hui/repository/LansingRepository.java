package com.dianping.hui.repository;

import com.dianping.hui.entity.GF;

import java.util.List;
public interface LansingRepository {

    default String getGirlFriend(){return "";}
    default List<String> getGirlFriends(){return null;}
    default GF save(GF pair){return null;}
}
