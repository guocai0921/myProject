package com.guocai.person.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guocai.person.mapper.PersonMapper;
import com.guocai.person.entity.Person;
import com.guocai.person.service.PersonService;

import java.util.List;
import java.util.Map;

/**
 * PersonServiceImpl 用户信息表
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-19 19:46:54
 */
 
@Service
public class PersonServiceImpl implements PersonService
{
	private static final Log logger = LogFactory.getLog(PersonServiceImpl.class);
    @Autowired
    private PersonMapper personMapper;

    @Override
    public List<Person> find(Map<String, Object> map) {
        return personMapper.find(map);
    }

    @Override
    public int delete(Person record) {
        return personMapper.delete(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer record) {
        return personMapper.deleteByPrimaryKey(record);
    }

    @Override
    public int deleteAllByPrimaryKey(List<Integer> records) {
        return personMapper.deleteAllByPrimaryKey(records);
    }

    @Override
    public int insert(Person record) {
        return personMapper.insert(record);
    }

    @Override
    public int insertAll(List<Person> records) {
        return personMapper.insertAll(records);
    }

    @Override
    public int update(Person record) {
        return personMapper.update(record);
    }

    @Override
    public int updateAll(List<Person> records) {
        return personMapper.updateAll(records);
    }

}
