package com.guocai.generator.controller;

import com.guocai.common.myUtils.*;
import com.guocai.generator.entity.Table;
import com.guocai.generator.service.TableService;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * java类简单作用描述
 *
 * @ClassName: TableController
 * @Package: com.guocai.generator.controller
 * @Description: 生成表结构
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2018-11-07-14:51
 */
@Controller
@RequestMapping("/generator")
public class TableController {

    @Autowired
    private TableService tableService;

    @RequestMapping("/submit")
    @ResponseBody
    public TaotaoResult getTables(HttpServletRequest request) {
        String tablePrefix = request.getParameter("tablePrefixName");
        String tableName = request.getParameter("tableName");
        String packageName = request.getParameter("packageName");
        String entityName = request.getParameter("entityName");
        if (tablePrefix != null && tableName != null && StringUtil.isNotEmpty(tablePrefix) && StringUtil.isNotEmpty(tableName)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tablePrefix", tablePrefix);
            map.put("tableName", tableName);
            List<Table> tables = tableService.getTablesByPrefix(map);
            for (Table table : tables) {
                table.setTablePrefix(tablePrefix);
                table.setTableName(table.getTableName());
                System.out.println("table--->" + table);
                if (StringUtil.isNotEmpty(table.getTableDesc())) {
                    table.setTableDesc(table.getTableDesc());
                }
                table.setEntityName(entityName);
                table.setEntityPackage(packageName+".entity");
                table.setControllerPackage(packageName+".controller");
                table.setJavaMapperPackage(packageName+".mapper");
                table.setServicePackage(packageName+".service");
                table.setServiceImplPackage(packageName+".service.impl");
                table.setXmlMapperPackage(packageName+".mapper");
            }
            this.generateEntity(tables, "EntityTemplate.vm", ".java", packageName, entityName);
            this.generateController(tables, "ControllerTemplate.vm", "Controller.java", packageName, entityName);
            this.generateJavaMapper(tables, "MapperTemplate.vm", "Mapper.java", packageName, entityName);
            this.generateService(tables, "ServiceTemplate.vm", "Service.java", packageName, entityName);
            this.generateServiceImpl(tables, "ServiceImplTemplate.vm", "ServiceImpl.java", packageName, entityName);
            this.generateXMLMapper(tables, "XmlMapperTemplate.vm", "Mapper.xml", packageName, entityName);

            return TaotaoResult.build(200, "操作成功！");
        } else {
            return TaotaoResult.build(400, "操作失败！");
        }
    }


    /**
     * 生成Java实体
     *
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateEntity(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"mysql");
            String path = "";
            if (packageName != null && StringUtil.isNotEmpty(packageName)) {
                path = basePath + (packageName + ".entity").replace(".", Constants.FILE_SEPERATOR);
            } else {
                path = basePath + t.getEntityPackage().replace(".", Constants.FILE_SEPERATOR);
            }
            String fileName = "";
            if (StringUtil.isNotEmpty(entityName)) {
                fileName = entityName + fileSurfixName;
            } else {
                fileName = t.getEntityName() + fileSurfixName;
            }
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成Controller
     *
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateController(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"mysql");
            String path = "";
            if (packageName != null && StringUtil.isNotEmpty(packageName)) {
                path = basePath + (packageName + ".controller").replace(".", Constants.FILE_SEPERATOR);
            } else {
                path = basePath + t.getControllerPackage().replace(".", Constants.FILE_SEPERATOR);
            }
            String fileName = "";
            if (StringUtil.isNotEmpty(entityName)) {
                fileName = entityName + fileSurfixName;
            } else {
                fileName = t.getEntityName() + fileSurfixName;
            }
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成Java Mapper
     *
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateJavaMapper(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"mysql");
            String path = "";
            if (packageName != null && StringUtil.isNotEmpty(packageName)) {
                path = basePath + (packageName + ".mapper").replace(".", Constants.FILE_SEPERATOR);
            } else {
                path = basePath + t.getJavaMapperPackage().replace(".", Constants.FILE_SEPERATOR);
            }
            String fileName = "";
            if (StringUtil.isNotEmpty(entityName)) {
                fileName = entityName + fileSurfixName;
            } else {
                fileName = t.getEntityName() + fileSurfixName;
            }
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成Service
     *
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateService(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"mysql");
            String path = "";
            if (packageName != null && StringUtil.isNotEmpty(packageName)) {
                path = basePath + (packageName + ".service").replace(".", Constants.FILE_SEPERATOR);
            } else {
                path = basePath + t.getServicePackage().replace(".", Constants.FILE_SEPERATOR);
            }
            String fileName = "";
            if (StringUtil.isNotEmpty(entityName)) {
                fileName = entityName + fileSurfixName;
            } else {
                fileName = t.getEntityName() + fileSurfixName;
            }
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成Service Implements
     *
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateServiceImpl(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"mysql");
            String path = "";
            if (packageName != null && StringUtil.isNotEmpty(packageName)) {
                path = basePath + (packageName + ".service.impl").replace(".", Constants.FILE_SEPERATOR);
            } else {
                path = basePath + t.getServiceImplPackage().replace(".", Constants.FILE_SEPERATOR);
            }
            String fileName = "";
            if (StringUtil.isNotEmpty(entityName)) {
                fileName = entityName + fileSurfixName;
            } else {
                fileName = t.getEntityName() + fileSurfixName;
            }
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成Mybatis XML Mapper
     *
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateXMLMapper(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.XML_MAPPER_BASE + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", Constants.AUTHOR);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            velocityCtx.put("methodSwitch", this.getMethodSwitch());
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"mysql");
            String path = "";
            if (packageName != null && StringUtil.isNotEmpty(packageName)) {
                path = basePath + (packageName + ".mapper").replace(".", Constants.FILE_SEPERATOR);
            } else {
                path = basePath + t.getXmlMapperPackage().replace(".", Constants.FILE_SEPERATOR);
            }
            String fileName = "";
            if (StringUtil.isNotEmpty(entityName)) {
                fileName = entityName + fileSurfixName;
            } else {
                fileName = t.getEntityName() + fileSurfixName;
            }
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    private MapperMethodSwitch methodSwitch = new MapperMethodSwitch();

    public MapperMethodSwitch getMethodSwitch() {
        return methodSwitch;
    }

    public void setMethodSwitch(MapperMethodSwitch methodSwitch) {
        this.methodSwitch = methodSwitch;
    }
}
