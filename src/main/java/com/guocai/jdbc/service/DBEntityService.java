package com.guocai.jdbc.service;

import com.guocai.jdbc.entity.DBEntity;

import java.util.List;
import java.util.Map;

/**
 * DBEntityService 数据库数据源
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-27 18:57:45
 */
 
public interface DBEntityService {

    List<DBEntity> find(Map<String, Object> map);
    int delete(DBEntity record);
    int deleteByPrimaryKey(Integer record);
    int deleteAllByPrimaryKey(List<Integer> records);
    int insert(DBEntity record);
    int insertAll(List<DBEntity> records);
    int update(DBEntity record);
    int updateAll(List<DBEntity> records);

}

