package com.guocai.jdbc.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**

 * [table="db_resource" | type="]"
 * @ClassName:   DBRsource
 * @Description: 
 * @author:      Sun GuoCai
 * @version:     v1.0.0
 * @date:        2018-11-27 20:59:04
 */
public class DBRsource {
	private static Log logger = LogFactory.getLog(DBRsource .class);
	
	/**
	 * 
	 */
	private String name;
	
	/**
	 * 主键ID
	 */
	private Integer id;
	
	/**
	 * 父ID
	 */
	private Integer parentId;
	
	/**
	 * 获取
	 * @return String
	*/
	public String getName() {
		return name;
	}
	
	/**
	 * 设置
	 * @param name
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取主键ID
	 * @return Integer
	*/
	public Integer getId() {
		return id;
	}
	
	/**
	 * 设置主键ID
	 * @param id
	*/
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取父ID
	 * @return Integer
	*/
	public Integer getParentId() {
		return parentId;
	}
	
	/**
	 * 设置父ID
	 * @param parentId
	*/
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	@Override
	public String toString() {
		StringBuffer sb =new StringBuffer();
		sb.append("DBRsource对象详细信息:" + "\n");
		sb.append("name:" + this.getName()+ "\n");
		sb.append("id:" + this.getId()+ "\n");
		sb.append("parentId:" + this.getParentId()+ "\n");
		return sb.toString();
	}
}
