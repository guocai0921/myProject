package com.guocai.user.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**

 * [table="t_user" | type="]"
 * @ClassName:   TUser
 * @Description: 用户表
 * @author:      Sun GuoCai
 * @version:     v1.0.0
 * @date:        2018-11-18 20:35:30
 */
public class TUser {
	private static Log logger = LogFactory.getLog(TUser .class);
	
	/**
	 * 
	 */
	private Integer id;
	
	/**
	 * 住址
	 */
	private String address;
	
	/**
	 * 入职时间
	 */
	private String regtime;
	
	/**
	 * 出生日期
	 */
	private String hiredate;
	
	/**
	 * 学历
	 */
	private String degree;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 性别
	 */
	private Integer sex;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 薪资
	 */
	private Double salary;
	
	/**
	 * 邮箱
	 */
	private String email;
	
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
	 * 获取住址
	 * @return String
	*/
	public String getAddress() {
		return address;
	}
	
	/**
	 * 设置住址
	 * @param address
	*/
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取入职时间
	 * @return String
	*/
	public String getRegtime() {
		return regtime;
	}
	
	/**
	 * 设置入职时间
	 * @param regtime
	*/
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	/**
	 * 获取出生日期
	 * @return String
	*/
	public String getHiredate() {
		return hiredate;
	}
	
	/**
	 * 设置出生日期
	 * @param hiredate
	*/
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	
	/**
	 * 获取学历
	 * @return String
	*/
	public String getDegree() {
		return degree;
	}
	
	/**
	 * 设置学历
	 * @param degree
	*/
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	/**
	 * 获取密码
	 * @return String
	*/
	public String getPassword() {
		return password;
	}
	
	/**
	 * 设置密码
	 * @param password
	*/
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 获取年龄
	 * @return Integer
	*/
	public Integer getAge() {
		return age;
	}
	
	/**
	 * 设置年龄
	 * @param age
	*/
	public void setAge(Integer age) {
		this.age = age;
	}
	
	/**
	 * 获取性别
	 * @return Integer
	*/
	public Integer getSex() {
		return sex;
	}
	
	/**
	 * 设置性别
	 * @param sex
	*/
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	/**
	 * 获取用户名
	 * @return String
	*/
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 设置用户名
	 * @param userName
	*/
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获取电话
	 * @return String
	*/
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 设置电话
	 * @param phone
	*/
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 获取薪资
	 * @return Double
	*/
	public Double getSalary() {
		return salary;
	}
	
	/**
	 * 设置薪资
	 * @param salary
	*/
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	/**
	 * 获取邮箱
	 * @return String
	*/
	public String getEmail() {
		return email;
	}
	
	/**
	 * 设置邮箱
	 * @param email
	*/
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		StringBuffer sb =new StringBuffer();
		sb.append("TUser对象详细信息:" + "\n");
		sb.append("id:" + this.getId()+ "\n");
		sb.append("address:" + this.getAddress()+ "\n");
		sb.append("regtime:" + this.getRegtime()+ "\n");
		sb.append("hiredate:" + this.getHiredate()+ "\n");
		sb.append("degree:" + this.getDegree()+ "\n");
		sb.append("password:" + this.getPassword()+ "\n");
		sb.append("age:" + this.getAge()+ "\n");
		sb.append("sex:" + this.getSex()+ "\n");
		sb.append("userName:" + this.getUserName()+ "\n");
		sb.append("phone:" + this.getPhone()+ "\n");
		sb.append("salary:" + this.getSalary()+ "\n");
		sb.append("email:" + this.getEmail()+ "\n");
		return sb.toString();
	}
}
