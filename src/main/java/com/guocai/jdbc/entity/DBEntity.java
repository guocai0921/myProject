package com.guocai.jdbc.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**

 * [table="db_entity" | type="]"
 * @ClassName:   DBEntity
 * @Description: 数据库数据源
 * @author:      Sun GuoCai
 * @version:     v1.0.0
 * @date:        2018-11-27 18:57:45
 */
public class DBEntity {
	private static Log logger = LogFactory.getLog(DBEntity .class);
	
	/**
	 * 
	 */
	private Integer id;
	
	/**
	 * 密码
	 */
	private String dbPassword;
	
	/**
	 * 数据库名
	 */
	private String dbSid;
	
	/**
	 * 数据库类别
	 */
	private String dbName;
	
	/**
	 * 数据源名称
	 */
	private String dbDesc;
	
	/**
	 * 服务器IP
	 */
	private String dbHost;
	
	/**
	 * 用户名
	 */
	private String dbUsername;
	
	/**
	 * 端口号
	 */
	private String dbPort;
	
	/**
	 * 驱动
	 */
	private String dbDriverName;
	
	/**
	 * 驱动模式
	 */
	private String dbDriver;
	
	/**
	 * 获取
	 * @return Integer
	*/
	public Integer getId() {
		return id;
	}
	
	/**
	 * 设置
	 * @param id
	*/
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取密码
	 * @return String
	*/
	public String getDbPassword() {
		return dbPassword;
	}
	
	/**
	 * 设置密码
	 * @param dbPassword
	*/
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	
	/**
	 * 获取数据库名
	 * @return String
	*/
	public String getDbSid() {
		return dbSid;
	}
	
	/**
	 * 设置数据库名
	 * @param dbSid
	*/
	public void setDbSid(String dbSid) {
		this.dbSid = dbSid;
	}
	
	/**
	 * 获取数据库类别


	 * @return String
	*/
	public String getDbName() {
		return dbName;
	}
	
	/**
	 * 设置数据库类别


	 * @param dbName
	*/
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	/**
	 * 获取数据源名称
	 * @return String
	*/
	public String getDbDesc() {
		return dbDesc;
	}
	
	/**
	 * 设置数据源名称
	 * @param dbDesc
	*/
	public void setDbDesc(String dbDesc) {
		this.dbDesc = dbDesc;
	}
	
	/**
	 * 获取服务器IP
	 * @return String
	*/
	public String getDbHost() {
		return dbHost;
	}
	
	/**
	 * 设置服务器IP
	 * @param dbHost
	*/
	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}
	
	/**
	 * 获取用户名
	 * @return String
	*/
	public String getDbUsername() {
		return dbUsername;
	}
	
	/**
	 * 设置用户名
	 * @param dbUsername
	*/
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	
	/**
	 * 获取端口号
	 * @return String
	*/
	public String getDbPort() {
		return dbPort;
	}
	
	/**
	 * 设置端口号
	 * @param dbPort
	*/
	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}
	
	/**
	 * 获取驱动
	 * @return String
	*/
	public String getDbDriverName() {
		return dbDriverName;
	}
	
	/**
	 * 设置驱动
	 * @param dbDriverName
	*/
	public void setDbDriverName(String dbDriverName) {
		this.dbDriverName = dbDriverName;
	}
	
	/**
	 * 获取驱动模式
	 * @return String
	*/
	public String getDbDriver() {
		return dbDriver;
	}
	
	/**
	 * 设置驱动模式
	 * @param dbDriver
	*/
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}
	
	@Override
	public String toString() {
		StringBuffer sb =new StringBuffer();
		sb.append("DBEntity对象详细信息:" + "\n");
		sb.append("id:" + this.getId()+ "\n");
		sb.append("dbPassword:" + this.getDbPassword()+ "\n");
		sb.append("dbSid:" + this.getDbSid()+ "\n");
		sb.append("dbName:" + this.getDbName()+ "\n");
		sb.append("dbDesc:" + this.getDbDesc()+ "\n");
		sb.append("dbHost:" + this.getDbHost()+ "\n");
		sb.append("dbUsername:" + this.getDbUsername()+ "\n");
		sb.append("dbPort:" + this.getDbPort()+ "\n");
		sb.append("dbDriverName:" + this.getDbDriverName()+ "\n");
		sb.append("dbDriver:" + this.getDbDriver()+ "\n");
		return sb.toString();
	}
}
