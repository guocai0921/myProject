package com.guocai.person.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guocai.person.entity.Person;
import com.guocai.person.service.PersonService;

/**
 * PersonController 用户信息表
 * @author Sun GuoCai
 * @version v1.0.0
 * @date 2018-11-19 19:46:54
 */
@RestController
@Scope("prototype")
@RequestMapping("/p/person")
public class PersonController {
	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(PersonController.class);
	@Autowired
    private PersonService personService;
}
