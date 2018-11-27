package com.guocai.user.service;

import com.guocai.common.bean.EasyUIDataGridResult;
import com.guocai.user.entity.User;

import java.util.List;

public interface UserService {

    User findUserById(Integer id);

    List<User> selectListUser();

    EasyUIDataGridResult getItemList(int page, int rows, String username);

    int insert(User record);

    int updateByPrimaryKey(User record);

    int deleteByPrimaryKey(Integer id);

}
