package com.guocai.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guocai.common.bean.EasyUIDataGridResult;
import com.guocai.user.entity.User;
import com.guocai.user.entity.UserExample;
import com.guocai.user.mapper.UserMapper;
import com.guocai.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @ClassName: UserServiceImpl
 * @Package: com.guocai.user.service.impl
 * @Description: 用户信息服务层
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2018-10-22-14:16
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectListUser() {
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows, String username) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (username!=null && ! username.equals("")) {
            criteria.andUserNameLike("%"+username+"%");
        }
        PageHelper.startPage(page, rows);
        List<User> list = userMapper.selectByExample(example);
        PageInfo<User> info = new PageInfo<>(list);
        long total = info.getTotal();
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(total);
        result.setRows(list);
        return result;
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
