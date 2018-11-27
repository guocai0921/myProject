package com.guocai.user.service;

import com.guocai.user.entity.TUser;

import java.util.List;
import java.util.Map;

/**
 * TUserService 用户表
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-18 20:35:30
 */
 
public interface TUserService {
    List<TUser> find(Map<String, Object> map);
    int delete(TUser record);
    int deleteByPrimaryKey(Integer id);
    int deleteAllByPrimaryKey(List<Integer> records);
    int insert(TUser record);
    int insertAll(List<TUser> records);
    int update(TUser record);
    int updateAll(List<TUser> records);
}

