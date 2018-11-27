package com.guocai.jdbc.service.impl;

import com.guocai.common.myUtils.*;
import com.guocai.generator.entity.Column;
import com.guocai.generator.entity.Table;
import com.guocai.jdbc.entity.JDBCEntity;
import com.guocai.jdbc.service.OracleService;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * java类简单作用描述
 *
 * @ClassName: OracleServiceImpl
 * @Package: com.guocai.jdbc.service.impl
 * @Description: Oracle数据库逻辑实现层
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2018-11-08-9:15
 */
@Service
public class OracleServiceImpl implements OracleService {

    @Override
    public TaotaoResult generatorFileByOracleJDBC(JDBCEntity entity) {
        Instant inst1 = Instant.now();
        Table table = getTable(entity);
        if (table.getTableName() == null) {
            return TaotaoResult.build(400,"所查询的表不存在");
        }
        List<Column> columns = getColumn(entity);
        for (Column column : columns) {
            if (column.getColumnName().equals("SID") || column.getColumnName().equals("sid")) {
                column.setColumnDesc("序列号");
            }
            if (column.getColumnName().equals("CREATED_BY") || column.getColumnName().equals("created_By")) {
                column.setColumnDesc("创建人");
            }
            if (column.getColumnName().equals("CREATED_DT") || column.getColumnName().equals("created_Dt")) {
                column.setColumnDesc("创建时间");
            }
            if (column.getColumnName().equals("UPDATED_BY") || column.getColumnName().equals("updated_By")) {
                column.setColumnDesc("更新人");
            }
            if (column.getColumnName().equals("UPDATED_DT") || column.getColumnName().equals("updated_Dt")) {
                column.setColumnDesc("更新时间");
            }
            if (column.getColumnName().equals("VERSION") || column.getColumnName().equals("version")) {
                column.setColumnDesc("更新时间");
            }
        }
        table.setColumns(columns);
        System.out.println("columns = " + columns);


        table.setTablePrefix(entity.getTablePrefixName());
        table.setTableName(table.getTableName());
        System.out.println("table--->" + table);
        if (StringUtil.isNotEmpty(table.getTableDesc())) {
            table.setTableDesc(table.getTableDesc());
        }
        table.setEntityName(entity.getEntityName());
        table.setEntityPackage(entity.getPackageName()+".entity");
        table.setControllerPackage(entity.getPackageName()+".controller");
        table.setJavaMapperPackage(entity.getPackageName()+".mapper");
        table.setServicePackage(entity.getPackageName()+".service");
        table.setServiceImplPackage(entity.getPackageName()+".service.impl");
        table.setXmlMapperPackage(entity.getPackageName()+".mapper");

        List<Table> tables = new ArrayList<>();
        tables.add(table);

        try {
            this.generateEntity(tables, "EntityTemplate.vm", ".java", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateController(tables, "ControllerTemplate.vm", "Controller.java", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateJavaMapper(tables, "MapperTemplate.vm", "Mapper.java", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateService(tables, "ServiceTemplate.vm", "Service.java", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateServiceImpl(tables, "ServiceImplTemplate.vm", "ServiceImpl.java", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateXMLMapper(tables, "XmlMapperTemplate.vm", "Mapper.xml", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateI18nTranslations(tables,entity.getPackageName(), entity.getEntityName(),entity.getAuthor());


            this.generateExtJsStore(tables, "ExtJsStoreTemplate.vm", "Store.js", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateExtJsModel(tables, "ExtJsModelTemplate.vm", "Model.js", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateExtJsView(tables, "ExtJsViewTemplate.vm", "View.js", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateExtJsQueryForm(tables, "ExtJsQueryFormTemplate.vm", "QueryForm.js", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateExtJsListGrid(tables, "ExtJsListGridTemplate.vm", "ListGrid.js", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateExtJsViewController(tables, "ExtJsViewControllerTemplate.vm", "Controller.js", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateExtJsWindow(tables, "ExtJsWindowTemplate.vm", "Win.js", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());
            this.generateExtJsWinController(tables, "ExtJsWinViewControllerTemplate.vm", "WinController.js", entity.getPackageName(), entity.getEntityName(),entity.getAuthor());

        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(200,ExceptionUtil.getStackTrace(e));
        }

        Instant inst2 = Instant.now();
        System.out.println("Difference in milliseconds : " + Duration.between(inst1, inst2).toMillis());
        System.out.println("Difference in seconds : " + Duration.between(inst1, inst2).getSeconds());

        return TaotaoResult.build(200,"生成完成！");
    }

    /**
     * 生成Java实体
     *
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateEntity(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"oracle");
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
    private void generateController(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"oracle");
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
    private void generateJavaMapper(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"oracle");
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
    private void generateService(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"oracle");
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
    private void generateServiceImpl(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.JAVA_BASE_PATH + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"oracle");
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
    private void generateXMLMapper(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.XML_MAPPER_BASE + Constants.FILE_SEPERATOR;
        for (Table t : tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            velocityCtx.put("methodSwitch", this.getMethodSwitch());
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"oracle");
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


    /**
     * 生成I18n国际化配置
     * @param tables
     */
    private void generateI18nTranslations(List<Table> tables, String packageName, String entityName,String author) {
        VelocityContext velocityCtx = new VelocityContext();
        velocityCtx.put("tables", tables);
        velocityCtx.put("Author", author);
        velocityCtx.put("Version", Constants.VERSION);
        velocityCtx.put("Date", Constants.GENERATE_DATE);
        String path = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.WEB_APP_BASE + Constants.FILE_SEPERATOR + "translations";
        String entityContent = GeneratorUtil.generate(velocityCtx, "TranslationTemplate.vm","oracle");
        String fileName = "business-lan-zh_CN.js";
        GeneratorUtil.writeFile(entityContent, path, fileName, true);
    }


    /**
     * 生成ExtJs store文件
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateExtJsStore(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.WEB_APP_BASE + Constants.FILE_SEPERATOR + "app" + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            //生成ExtJs Store文件
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"oracle");
            String path = basePath +  "store" + Constants.FILE_SEPERATOR +
                    t.getModuleName() + Constants.FILE_SEPERATOR +
                    t.getFirstLetterLowerEntityName() + Constants.FILE_SEPERATOR;
            String fileName = t.getEntityName() + fileSurfixName;
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }

    /**
     * 生成ExtJs model文件
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateExtJsModel(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.WEB_APP_BASE + Constants.FILE_SEPERATOR + "app" + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            //生成ExtJs model文件
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath, "oracle");
            String path = basePath +  "model" + Constants.FILE_SEPERATOR +
                    t.getModuleName() + Constants.FILE_SEPERATOR +
                    t.getFirstLetterLowerEntityName() + Constants.FILE_SEPERATOR;
            String fileName = t.getEntityName() + fileSurfixName;
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }
    /**
     * 生成ExtJs view文件
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateExtJsView(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.WEB_APP_BASE + Constants.FILE_SEPERATOR + "app" + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            //生成ExtJs View文件
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath, "oracle");
            String path = basePath +  "view" + Constants.FILE_SEPERATOR +
                    t.getModuleName() + Constants.FILE_SEPERATOR +
                    t.getFirstLetterLowerEntityName() + Constants.FILE_SEPERATOR;
            String fileName = t.getEntityName() + fileSurfixName;
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }
    /**
     * 生成ExtJs query form文件
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateExtJsQueryForm(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.WEB_APP_BASE + Constants.FILE_SEPERATOR + "app" + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);

            //生成ExtJs query form文件
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath, "oracle");
            String path = basePath +  "view" + Constants.FILE_SEPERATOR +
                    t.getModuleName() + Constants.FILE_SEPERATOR +
                    t.getFirstLetterLowerEntityName() + Constants.FILE_SEPERATOR;
            String fileName = t.getEntityName() + fileSurfixName;
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }
    /**
     * 生成ExtJs list grid文件
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateExtJsListGrid(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.WEB_APP_BASE + Constants.FILE_SEPERATOR + "app" + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            //生成ExtJs list grid文件
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath, "oracle");
            String path = basePath +  "view" + Constants.FILE_SEPERATOR +
                    t.getModuleName() + Constants.FILE_SEPERATOR +
                    t.getFirstLetterLowerEntityName() + Constants.FILE_SEPERATOR;
            String fileName = t.getEntityName() + fileSurfixName;
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }
    /**
     * 生成ExtJs view controller文件
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateExtJsViewController(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.WEB_APP_BASE + Constants.FILE_SEPERATOR + "app" + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            //生成ExtJs view controller文件
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath, "oracle");
            String path = basePath +  "view" + Constants.FILE_SEPERATOR +
                    t.getModuleName() + Constants.FILE_SEPERATOR +
                    t.getFirstLetterLowerEntityName() + Constants.FILE_SEPERATOR;
            String fileName = t.getEntityName() + fileSurfixName;
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }
    /**
     * 生成ExtJs文件
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateExtJsWindow(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.WEB_APP_BASE + Constants.FILE_SEPERATOR + "app" + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            //生成ExtJs Window文件
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"oracle");
            String path = basePath +  "view" + Constants.FILE_SEPERATOR +
                    t.getModuleName() + Constants.FILE_SEPERATOR +
                    t.getFirstLetterLowerEntityName() + Constants.FILE_SEPERATOR;
            String fileName = t.getEntityName() + fileSurfixName;
            GeneratorUtil.writeFile(entityContent, path, fileName, true);
        }
    }
    /**
     * 生成ExtJs文件
     * @param tables
     * @param templateRelativePath
     * @param fileSurfixName
     */
    private void generateExtJsWinController(List<Table> tables, String templateRelativePath, String fileSurfixName, String packageName, String entityName,String author) {
        String basePath = Constants.TARGET_BASE_PARTH + Constants.FILE_SEPERATOR +
                Constants.WEB_APP_BASE + Constants.FILE_SEPERATOR + "app" + Constants.FILE_SEPERATOR;
        for (Table t:tables) {
            VelocityContext velocityCtx = new VelocityContext();
            velocityCtx.put("table", t);
            velocityCtx.put("Author", author);
            velocityCtx.put("Version", Constants.VERSION);
            velocityCtx.put("Date", Constants.GENERATE_DATE);
            //生成ExtJs window view controller文件
            String entityContent = GeneratorUtil.generate(velocityCtx, templateRelativePath,"oracle");
            String path = basePath +  "view" + Constants.FILE_SEPERATOR +
                    t.getModuleName() + Constants.FILE_SEPERATOR +
                    t.getFirstLetterLowerEntityName() + Constants.FILE_SEPERATOR;
            String fileName = t.getEntityName() + fileSurfixName;
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

    public List<Column> getColumn(JDBCEntity entity) {
        List<Column> columns = new ArrayList<>();
        String sql = "select col.COLUMN_NAME, com.COMMENTS as COLUMN_DESC, col.DATA_TYPE, col.DATA_PRECISION, col.DATA_SCALE, col.NULLABLE as NULL_FLAG\n" +
                "        from user_tab_columns col, user_col_comments com\n" +
                "        WHERE col.TABLE_NAME= com.table_name\n" +
                "        and col.COLUMN_NAME = com.column_name\n" +
                "        and col.TABLE_NAME= ?\n" +
                "        order by col.COLUMN_ID";
        System.out.println("sql = " + sql);
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;//定义存放查询结果的结果集
        try{
            conn= DbUtil.getConnection(entity.getDriver(),entity.getHost(),entity.getPort()
                    ,entity.getSid(),entity.getUsername(),entity.getPassword());
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,entity.getTableName());
            rs=pstmt.executeQuery();//运行查询操作
            while (rs.next()){
                Column column = new Column();
                column.setColumnName(rs.getString("COLUMN_NAME"));
                column.setColumnDesc(rs.getString("COLUMN_DESC"));
                column.setDataType(rs.getString("DATA_TYPE"));
                column.setDataPrecision(rs.getInt("DATA_PRECISION"));
                column.setDataScale(rs.getInt("DATA_SCALE"));
                column.setNullFlag(rs.getString("NULL_FLAG"));
                columns.add(column);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //按顺序进行关闭
            DbUtil.close(rs);
            DbUtil.close(pstmt);
            DbUtil.close(conn);

        }
        return columns;
    }

    public Table getTable(JDBCEntity entity) {
        Table table=new Table();
        String sql = "select * from\n" +
                "        (select t.table_name, com.comments as table_desc, com.table_type\n" +
                "        from user_tables t, user_tab_comments com\n" +
                "        where t.TABLE_NAME= com.table_name\n" +
                "        union all\n" +
                "        SELECT v.VIEW_NAME as table_name, com.comments, com.table_type\n" +
                "        FROM USER_VIEWS v,  user_tab_comments com\n" +
                "        where v.VIEW_NAME= com.TABLE_NAME )\n" +
                "        where 1=1 and  TABLE_NAME like '' || ? || '%' " +
                " and TABLE_NAME = ?";
        System.out.println("sql = " + sql);
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;//定义存放查询结果的结果集
        try{
            conn= DbUtil.getConnection(entity.getDriver(),entity.getHost(),entity.getPort()
                ,entity.getSid(),entity.getUsername(),entity.getPassword());
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,entity.getTablePrefixName());
            pstmt.setString(2,entity.getTableName());
            rs=pstmt.executeQuery();//运行查询操作
            if(rs.next()){

                table.setTableName(rs.getString("table_name"));
                table.setTableDesc(rs.getString("table_desc"));
                table.setTableType(rs.getString("table_type"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //按顺序进行关闭
            DbUtil.close(rs);
            DbUtil.close(pstmt);
            DbUtil.close(conn);

        }
        return table;
    }
}
