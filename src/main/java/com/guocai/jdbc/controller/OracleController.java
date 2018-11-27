package com.guocai.jdbc.controller;

import com.guocai.common.myUtils.TaotaoResult;
import com.guocai.jdbc.entity.JDBCEntity;
import com.guocai.jdbc.service.OracleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * java类简单作用描述
 *
 * @ClassName: OracleController
 * @Package: com.guocai.jdbc.controller
 * @Description: 连接Oracle数据库控制器
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2018-11-08-9:11
 */
@Controller
@RequestMapping("/generator/submit")
public class OracleController {

    @Autowired
    private OracleService oracleService;

    @RequestMapping("/oracle")
    @ResponseBody
    public TaotaoResult generatorFileByOracleJDBC(HttpServletRequest request) {
        String tablePrefix = request.getParameter("tablePrefixName");
        String tableName = request.getParameter("tableName");
        String packageName = request.getParameter("packageName");
        String entityName = request.getParameter("entityName");
        String author = request.getParameter("author");
        String host = request.getParameter("host");
        String port = request.getParameter("port");
        String sid = request.getParameter("sid");
        String driver = request.getParameter("driver");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        JDBCEntity entity = new JDBCEntity();
        entity.setTablePrefixName(tablePrefix);
        entity.setTableName(tableName);
        entity.setPackageName(packageName);
        entity.setEntityName(entityName);
        entity.setAuthor(author);
        entity.setHost(host);
        entity.setPort(port);
        entity.setSid(sid);
        entity.setDriver(driver);
        entity.setUsername(username);
        entity.setPassword(password);
        return oracleService.generatorFileByOracleJDBC(entity);
    }

}
