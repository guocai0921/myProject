package com.guocai.user.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guocai.user.service.TUserService;

import java.util.HashMap;
import java.util.Map;

/**
 * TUserController 用户表
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-18 20:35:30
 */
@RestController
@Scope("prototype")
@RequestMapping("/t/t-user")
public class TUserController {
	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(TUserController.class);
	@Autowired
    private TUserService tUserService;

}
