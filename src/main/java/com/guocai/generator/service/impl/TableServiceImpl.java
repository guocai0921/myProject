package com.guocai.generator.service.impl;

import com.guocai.generator.entity.Table;
import com.guocai.generator.mapper.TableMapper;
import com.guocai.generator.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * java类简单作用描述
 *
 * @ClassName: TableServiceImpl
 * @Package: com.guocai.generator.service.impl
 * @Description: 表生成业务逻辑处理
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2018-11-07-14:54
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableMapper tableMapper;


    @Override
    public List<Table> getTablesByPrefix(Map<String, Object> map) {
        return tableMapper.getTablesByPrefix(map);
    }
}
