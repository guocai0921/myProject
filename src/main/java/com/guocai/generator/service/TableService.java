package com.guocai.generator.service;

import com.guocai.generator.entity.Table;

import java.util.List;
import java.util.Map;

public interface TableService {
    List<Table> getTablesByPrefix(Map<String,Object> map);
}
