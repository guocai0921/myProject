package com.guocai.jdbc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guocai.jdbc.mapper.DBRsourceMapper;
import com.guocai.jdbc.entity.DBRsource;
import com.guocai.jdbc.service.DBRsourceService;

import java.util.List;
import java.util.Map;

/**
 * DBRsourceServiceImpl 
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-27 20:59:04
 */
 
@Service
public class DBRsourceServiceImpl implements DBRsourceService
{
	private static final Log logger = LogFactory.getLog(DBRsourceServiceImpl.class);
    @Autowired
    private DBRsourceMapper resourceMapper;

    @Override
    public List<DBRsource> find(Map<String, Object> map) {
        return resourceMapper.find(map);
    }

    @Override
    public int delete(DBRsource record) {
        return resourceMapper.delete(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer record) {
        return resourceMapper.deleteByPrimaryKey(record);
    }

    @Override
    public int deleteAllByPrimaryKey(List<Integer> records) {
        return resourceMapper.deleteAllByPrimaryKey(records);
    }

    @Override
    public int insert(DBRsource record) {
        return resourceMapper.insert(record);
    }

    @Override
    public int insertAll(List<DBRsource> records) {
        return resourceMapper.insertAll(records);
    }

    @Override
    public int update(DBRsource record) {
        return resourceMapper.update(record);
    }

    @Override
    public int updateAll(List<DBRsource> records) {
        return resourceMapper.updateAll(records);
    }

}
