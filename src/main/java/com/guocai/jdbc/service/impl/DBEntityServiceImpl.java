package com.guocai.jdbc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guocai.jdbc.mapper.DBEntityMapper;
import com.guocai.jdbc.entity.DBEntity;
import com.guocai.jdbc.service.DBEntityService;

import java.util.List;
import java.util.Map;

/**
 * DBEntityServiceImpl 数据库数据源
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-27 18:57:45
 */
 
@Service
public class DBEntityServiceImpl implements DBEntityService
{
	private static final Log logger = LogFactory.getLog(DBEntityServiceImpl.class);
    @Autowired
    private DBEntityMapper entityMapper;

    @Override
    public List<DBEntity> find(Map<String, Object> map) {
        return entityMapper.find(map);
    }

    @Override
    public int delete(DBEntity record) {
        return entityMapper.delete(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer record) {
        return entityMapper.deleteByPrimaryKey(record);
    }

    @Override
    public int deleteAllByPrimaryKey(List<Integer> records) {
        return entityMapper.deleteAllByPrimaryKey(records);
    }

    @Override
    public int insert(DBEntity record) {
        return entityMapper.insert(record);
    }

    @Override
    public int insertAll(List<DBEntity> records) {
        return entityMapper.insertAll(records);
    }

    @Override
    public int update(DBEntity record) {
        return entityMapper.update(record);
    }

    @Override
    public int updateAll(List<DBEntity> records) {
        return entityMapper.updateAll(records);
    }

}
