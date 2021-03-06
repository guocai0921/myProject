package com.guocai.generator.entity;

import com.guocai.common.myUtils.Util;

import java.util.List;
import java.util.Random;

/**
 * java类简单作用描述
 *
 * @ClassName: Table
 * @Package: com.guocai.mp.mybatis.entity
 * @Description: <  >
 * @Author: Sun GuoCai
 * @CreateDate: 2018/7/27 21:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Table {
	/**
	 * 数据库表名称
	 */
	private String tableName;
	/**
	 * 数据库表描述
	 */
	private String tableDesc="";
	/**
	 * 数据库表类型
	 */
	private String tableType;
	/**
	 * 表字段集
	 */
	private List<Column> columns;
	/*
	 * 表前缀
	 */
	private String tablePrefix;
	/**
	 * 由表名生成的Java Entity名称
	 */
	private String entityName;
	/**
	 * 生成的Entity对象的类包路径
	 */
	private String entityPackage;
	/**
	 * 生成的Java Mapper类包路径
	 */
	private String javaMapperPackage;
	/**
	 * 生成的XML Mapper文件的包路径
	 */
	private String xmlMapperPackage;
	/**
	 * 生成的Controller类包路径
	 */
	private String controllerPackage;
	/**
	 * 生成的Service类包路径
	 */
	private String servicePackage;
	/**
	 * 生成的Service Implement类包路径
	 */
	private String serviceImplPackage;

	private String content;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		if (tablePrefix.indexOf("_")<=0) tablePrefix = tablePrefix + "_";
		this.tablePrefix = tablePrefix;
	}


	/**
	 * 返回对应模块简写名称
	 * @return
	 */
	public String getModuleName() {
		return Util.getModuleName(this.tableName,this.tablePrefix).toLowerCase();
	}
	/**
	 * 判断表中是否有大字段类型
	 * @return
	 */
	public boolean hasBigColumn() {
		boolean hasBigColumn = false;
		for(Column col:this.getColumns()) {
			if (col.isBigColumn()) {
				hasBigColumn = true;
				break;
			}
		}
		return hasBigColumn;
	}
	/**
	 * 返回表中大字段的数量
	 * @return
	 */
	public int getBigColumnCount() {
		int count =0;
		for(Column col:this.getColumns()) {
			if (col.isBigColumn()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 返回首字母小写的带Module名称的Entity名称
	 * @return
	 */
	public String getFirstLetterLowerModuleEntityName() {
		return Util.underlineToCamel(this.tableName.replace(this.tablePrefix, ""));
	}

	/**
	 * 返回首字母小写的Entity名称
	 * @return
	 */
	public String getFirstLetterLowerEntityName() {
		return this.getFirstLetterLowerModuleEntityName();
	}
	/**
	 * 获取store中action url的别名
	 * @return
	 */
	public String getStrutsActionAlias() {
		return this.tableName.replace(this.tablePrefix, "")
				.replace("_", "-").toLowerCase();
	}
	/**
	 * 返回基于表名的Sequence名称
	 * @return
	 */
	public String getSequenceName() {
		String sequenceName = null;
		sequenceName = "SEQ_" + this.tableName.replace(this.tablePrefix, "");
		return sequenceName;
	}
	/**
	 * 返回随机数
	 * @return
	 */
	public int getRandomNumber() {
		Random rand = new Random();
		int randNum = rand.nextInt(999);
		return randNum;
	}

	// <========================================================================================================>>


    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getJavaMapperPackage() {
        return javaMapperPackage;
    }

    public void setJavaMapperPackage(String javaMapperPackage) {
        this.javaMapperPackage = javaMapperPackage;
    }

    public String getXmlMapperPackage() {
        return xmlMapperPackage;
    }

    public void setXmlMapperPackage(String xmlMapperPackage) {
        this.xmlMapperPackage = xmlMapperPackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    @Override
	public String toString() {
		StringBuffer sb =new StringBuffer();
		sb.append("TableName:" + this.tableName +", TableDesc:"+ this.tableDesc + ", TableType:" + this.tableType + "\n");
		sb.append("entityName:" + this.getEntityName()+ "\n");
		sb.append("entityPackage:" + this.getEntityPackage()+ "\n");
		sb.append("javaMapperPackage:" + this.getJavaMapperPackage()+ "\n");
		sb.append("xmlMapperPackage:" + this.getXmlMapperPackage()+ "\n");
		sb.append("controllerPackage:" + this.getControllerPackage()+ "\n");
		sb.append("servicePackage:" + this.getServicePackage()+ "\n");
		sb.append("serviceImplPackage:" + this.getServiceImplPackage());
		String str = "Table{" +
				"tableName='" + tableName + '\'' +
				", tableDesc='" + tableDesc + '\'' +
				", tableType='" + tableType + '\'' +
				", columns=" + columns +
				", tablePrefix='" + tablePrefix + '\'' +
				", entityName='" + entityName + '\'' +
				", entityPackage='" + entityPackage + '\'' +
				", javaMapperPackage='" + javaMapperPackage + '\'' +
				", xmlMapperPackage='" + xmlMapperPackage + '\'' +
				", controllerPackage='" + controllerPackage + '\'' +
				", servicePackage='" + servicePackage + '\'' +
				", serviceImplPackage='" + serviceImplPackage + '\'' +
				'}';
		sb.append(str);
		return sb.toString();
	}


	//<<<======================================================================================================>>>

	// public String getContent() {
	// 	return content;
	// }
	//
	// public void setContent(String content) {
	// 	this.content = content;
	// }
	//
	// public void setEntityName(String entityName) {
	// 	this.entityName = entityName;
	// }
	//
	// public String getEntityPackage() {
	// 	return entityPackage;
	// }
	//
	// public void setEntityPackage(String entityPackage) {
	// 	this.entityPackage = entityPackage;
	// }
	//
	// public String getJavaMapperPackage() {
	// 	return javaMapperPackage;
	// }
	//
	// public void setJavaMapperPackage(String javaMapperPackage) {
	// 	this.javaMapperPackage = javaMapperPackage;
	// }
	//
	// public String getXmlMapperPackage() {
	// 	return xmlMapperPackage;
	// }
	//
	// public void setXmlMapperPackage(String xmlMapperPackage) {
	// 	this.xmlMapperPackage = xmlMapperPackage;
	// }
	//
	// public String getControllerPackage() {
	// 	return controllerPackage;
	// }
	//
	// public void setControllerPackage(String controllerPackage) {
	// 	this.controllerPackage = controllerPackage;
	// }
	//
	// public String getServicePackage() {
	// 	return servicePackage;
	// }
	//
	// public void setServicePackage(String servicePackage) {
	// 	this.servicePackage = servicePackage;
	// }
	//
	// public String getServiceImplPackage() {
	// 	return serviceImplPackage;
	// }
	//
	// public void setServiceImplPackage(String serviceImplPackage) {
	// 	this.serviceImplPackage = serviceImplPackage;
	// }
	//
	// public String getEntityName() {
	// 	return entityName;
	// }


	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityName() {
		return entityName;
	}
}
