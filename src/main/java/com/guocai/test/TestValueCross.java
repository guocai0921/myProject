package com.guocai.test;

import com.guocai.person.entity.Person;

/**
 * java类简单作用描述
 *
 * @ClassName: TestValueCross
 * @Package: com.guocai.test
 * @Description: 值传递测试
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2018-11-14-14:15
 */
public class TestValueCross {

    public static void test1(int age,int weight) {
        age = 30;
        weight = 40;
        System.out.println("age = " + age);
        System.out.println("weight = " + weight);
    }

    public static void main(String[] args){
        // int a = 3;
        // int w = 4;
        // System.out.println("a->before = " + a);
        // System.out.println("w->before = " + w);
        // test1(a,w);
        // System.out.println("a->after = " + a);
        // System.out.println("w->after = " + w);

        // Person p = new Person("张三", 23);
        // System.out.println("p = " + p.getName());
        // test2(p);
        // System.out.println("p = " + p.getName());

        // Person p = new Person("张三", 23);
        // System.out.println("p = " + p.getName());
        // test3(p);
        // System.out.println("p = " + p.getName());


    }

    public static void test3(Person person) {
        person.setName("李四");
        System.out.println("person.getName() = " + person.getName());

    }
    public static void test2(Person person) {
        person.setName("李四");
        System.out.println("person.getName() = " + person.getName());

    }

}
