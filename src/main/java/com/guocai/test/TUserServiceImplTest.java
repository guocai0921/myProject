package com.guocai.test;


import com.guocai.person.entity.Person;
import com.guocai.person.service.PersonService;
import com.guocai.user.entity.TUser;
import com.guocai.user.service.TUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

public class TUserServiceImplTest {

    TUserService service = null;

    PersonService personService = null;

    @org.junit.Before
    public void setUp() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        System.out.println("ac = " + ac);
        service = ac.getBean(TUserService.class);
        personService = ac.getBean(PersonService.class);
        System.out.println("service = " + service);

    }

    @org.junit.Test
    public void find() {
        Map<String,Object> map = new HashMap<String,Object>();
        List<TUser> tUsers = service.find(map);
        for (TUser tUser : tUsers) {
            System.out.println("tUser = " + tUser);
        }
    }

    @org.junit.Test
    public void delete() {
        TUser user3 = new TUser();
        user3.setId(33);
        user3.setUserName("戈登");
        user3.setPassword("668866");
        user3.setAge(24);
        user3.setAddress("休斯顿火箭队");
        service.delete(user3);

    }

    @org.junit.Test
    public void deleteByPrimaryKey() {
        service.deleteByPrimaryKey(32);
    }

    @org.junit.Test
    public void deleteAllByPrimaryKey() {
        List<Integer> listi = new ArrayList<Integer>();
        listi.add(30);
        listi.add(34);
        int i = service.deleteAllByPrimaryKey(listi);
        System.out.println("i = " + i);

    }

    @org.junit.Test
    public void insert() {
        TUser user = new TUser();
        user.setUserName("刘德华");
        user.setPassword("123456");
        user.setAge(23);
        int i = service.insert(user);
        System.out.println("i = " + i);
        System.out.println(user.getId());

    }

    @org.junit.Test
    public void insertAll() {
        List<Person> listi = new ArrayList<Person>();
        Person p1 = new Person();
        p1.setName("AAAA");
        p1.setAge(23);
        p1.setBirthday(new Date());
        p1.setSalary(12000.00);
        p1.setAddress("aaaa");
        Person p2 = new Person();
        p2.setName("BBBB");
        p2.setAge(23);
        p2.setBirthday(new Date());
        p2.setSalary(12000.00);
        p2.setAddress("bbbb");
        Person p3 = new Person();
        p3.setName("CCCC");
        p3.setAge(23);
        p3.setBirthday(new Date());
        p3.setSalary(12000.00);
        p3.setAddress("cccc");

        listi.add(p1);
        listi.add(p2);
        listi.add(p3);

        personService.insertAll(listi);
        // TUser user1 = new TUser();
        // user1.setUserName("罗斯");
        // user1.setPassword("123456");
        // user1.setAge(22);
        //
        // TUser user2 = new TUser();
        // user2.setUserName("哈登");
        // user2.setPassword("111161");
        // user2.setAge(25);
        //
        // TUser user3 = new TUser();
        // user3.setUserName("戈登");
        // user3.setPassword("668866");
        // user3.setAge(24);
        //
        // List<TUser> listlk = new LinkedList<TUser>();
        // listlk.add(user1);
        // listlk.add(user2);
        // listlk.add(user3);
        //
        // int i = service.insertAll(listlk);
        // System.out.println("i = " + i);

    }

    @org.junit.Test
    public void update() {
        TUser user1 = new TUser();
        user1.setId(31);
        user1.setUserName("罗斯");
        user1.setPassword("123456");
        user1.setAge(22);
        user1.setAddress("明尼苏达森林狼队");
        user1.setEmail("rose@gmail.com");
        int update = service.update(user1);
        System.out.println("update = " + update);

    }

    @org.junit.Test
    public void updateAll() {
        TUser user1 = new TUser();
        user1.setId(31);
        user1.setUserName("罗斯");
        user1.setPassword("123456");
        user1.setAge(22);
        user1.setAddress("明尼苏达森林狼队");

        TUser user2 = new TUser();
        user2.setId(32);
        user2.setUserName("哈登");
        user2.setPassword("111161");
        user2.setAge(25);
        user2.setAddress("休斯顿火箭队");

        TUser user3 = new TUser();
        user3.setId(33);
        user3.setUserName("戈登");
        user3.setPassword("668866");
        user3.setAge(24);
        user3.setAddress("休斯顿火箭队");

        List<TUser> listlk = new LinkedList<TUser>();
        listlk.add(user1);
        listlk.add(user2);
        listlk.add(user3);

        int i = service.updateAll(listlk);
        System.out.println("i = " + i);

    }
}