package com.guocai.jdbc.service;

import com.guocai.jdbc.entity.DBRsource;

import java.util.List;
import java.util.Map;

/**
 * DBRsourceService 
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-27 20:59:04
 */
 
public interface DBRsourceService {

    List<DBRsource> find(Map<String, Object> map);
    int delete(DBRsource record);
    int deleteByPrimaryKey(Integer record);
    int deleteAllByPrimaryKey(List<Integer> records);
    int insert(DBRsource record);
    int insertAll(List<DBRsource> records);
    int update(DBRsource record);
    int updateAll(List<DBRsource> records);

}

