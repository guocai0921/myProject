package com.guocai.person.mapper;

import com.guocai.person.entity.Person;

import java.util.List;
import java.util.Map;

/**
 * PersonMapper 用户信息表
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-19 19:46:54
 */

public interface PersonMapper {
    List<Person> find(Map<String, Object> map);
    int delete(Person record);
    int deleteByPrimaryKey(Integer record);
    int deleteAllByPrimaryKey(List<Integer> records);
    int insert(Person record);
    int insertAll(List<Person> records);
    int update(Person record);
    int updateAll(List<Person> records);
}
