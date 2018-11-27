package com.guocai.jdbc.service;

import com.guocai.common.myUtils.TaotaoResult;
import com.guocai.jdbc.entity.JDBCEntity;

/**
 * @description: Oracle数据库服务层
 * @auther: Sun GuoCai
 * @datetime: 2018/11/8 9:15
 * @param: null
 * @return:
 */
public interface OracleService {
    TaotaoResult generatorFileByOracleJDBC(JDBCEntity entity);
}
