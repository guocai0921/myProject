package com.guocai.jdbc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guocai.jdbc.entity.DBRsource;
import com.guocai.jdbc.service.DBRsourceService;

/**
 * DBRsourceController 
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-27 20:59:04
 */
@RestController
@Scope("prototype")
@RequestMapping("/re/resource")
public class DBRsourceController {
	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(DBRsourceController.class);
	@Autowired
    private DBRsourceService resourceService;
}
