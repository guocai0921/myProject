package com.guocai.jdbc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guocai.jdbc.entity.DBEntity;
import com.guocai.jdbc.service.DBEntityService;

/**
 * DBEntityController 数据库数据源
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-27 18:57:45
 */
@RestController
@Scope("prototype")
@RequestMapping("/")
public class DBEntityController {
	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(DBEntityController.class);
	@Autowired
    private DBEntityService entityService;
}
