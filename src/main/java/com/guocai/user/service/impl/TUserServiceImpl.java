package com.guocai.user.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guocai.user.mapper.TUserMapper;
import com.guocai.user.entity.TUser;
import com.guocai.user.service.TUserService;

import java.util.List;
import java.util.Map;

/**
 * TUserServiceImpl 用户表
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-18 20:35:30
 */
 
 @Service
public class TUserServiceImpl implements TUserService
{
	private static final Log logger = LogFactory.getLog(TUserServiceImpl.class);
	 @Autowired
	 private TUserMapper tUserMapper;

	@Override
	public List<TUser> find(Map<String, Object> map) {
		return tUserMapper.find(map);
	}

	@Override
	public int delete(TUser record) {
		return tUserMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer record) {
		return tUserMapper.deleteByPrimaryKey(record);
	}

	@Override
	public int deleteAllByPrimaryKey(List<Integer> records) {
		return tUserMapper.deleteAllByPrimaryKey(records);
	}

	@Override
	public int insert(TUser record) {
		return tUserMapper.insert(record);
	}

	@Override
	public int insertAll(List<TUser> records) {
		return tUserMapper.insertAll(records);
	}

	@Override
	public int update(TUser record) {
		return tUserMapper.update(record);
	}

	@Override
	public int updateAll(List<TUser> records) {
		return tUserMapper.updateAll(records);
	}
}
