package com.guocai.user.controller;

import com.guocai.common.bean.EasyUIDataGridResult;
import com.guocai.common.bean.Response;
import com.guocai.common.myUtils.TaotaoResult;
import com.guocai.user.entity.User;
import com.guocai.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * java类简单作用描述
 *
 * @ClassName: UserController
 * @Package: com.guocai.user.controller
 * @Description: 用户控制器
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2018-10-19-9:43
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows, String username) {
        return userService.getItemList(page, rows, username);
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult addUser(@RequestBody User user) {
        userService.insert(user);
        return TaotaoResult.build(200,"添加成功!");
    }
    @RequestMapping("/edit")
    @ResponseBody
    public TaotaoResult editUser(@RequestBody User user) {
        userService.updateByPrimaryKey(user);
        return TaotaoResult.build(200,"添加成功!");
    }
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteUser(String ids) {
        int index = ids.indexOf(",");
        if(index==-1) {
            userService.deleteByPrimaryKey(Integer.valueOf(ids));
        } else {
            String[] array = ids.split(",");
            for (int i = 0; i < array.length; i++) {
                userService.deleteByPrimaryKey(Integer.valueOf(array[i]));
            }
        }
        return TaotaoResult.build(200,"添加成功!");
    }
}
