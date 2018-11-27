package com.guocai.person.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**

 * [table="t_person" | type="]"
 * @ClassName:   Person
 * @Description: 用户信息表
 * @author:      Sun GuoCai
 * @version:     v1.0.0
 * @date:        2018-11-19 19:46:54
 */
public class Person {
	private static Log logger = LogFactory.getLog(Person .class);
	
	/**
	 * 主键ID

	 */
	private Integer id;
	
	/**
	 * 住址
	 */
	private String address;
	
	/**
	 * 姓名

	 */
	private String name;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 生日
	 */
	private Date birthday;
	
	/**
	 * 薪资
	 */
	private Double salary;
	
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
	 * 获取姓名

	 * @return String
	*/
	public String getName() {
		return name;
	}
	
	/**
	 * 设置姓名

	 * @param name
	*/
	public void setName(String name) {
		this.name = name;
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
	 * 获取生日
	 * @return Date
	*/
	public Date getBirthday() {
		return birthday;
	}
	
	/**
	 * 设置生日
	 * @param birthday
	*/
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	
	@Override
	public String toString() {
		StringBuffer sb =new StringBuffer();
		sb.append("Person对象详细信息:" + "\n");
		sb.append("id:" + this.getId()+ "\n");
		sb.append("address:" + this.getAddress()+ "\n");
		sb.append("name:" + this.getName()+ "\n");
		sb.append("age:" + this.getAge()+ "\n");
		sb.append("birthday:" + this.getBirthday()+ "\n");
		sb.append("salary:" + this.getSalary()+ "\n");
		return sb.toString();
	}
}
