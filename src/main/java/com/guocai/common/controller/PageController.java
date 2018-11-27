package com.guocai.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * java类简单作用描述
 *
 * @ClassName: PageController
 * @Package: com.guocai.common
 * @Description: 控制页面跳转
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2018-11-06-19:53
 */
@Controller
public class PageController {
    @RequestMapping("/page/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    /**
     * 实现页面跳转打开首页
     */
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }
}
