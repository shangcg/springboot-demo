package com.shangcg.base;

/**
 * 测试String 拼接 equals和 == 的区别
 *
 * == 比较的是地址
 * == 比较基本类型时：int a = 10; int b = 10; a == b为true； 基本类型
 * == 比较引用类型时：

 *
 * 用 == 比较String， 则是比较引用类型，比较的是地址
 * 用 == 比较int， 则比较的是基本类型，比较的是值
 * == 比较引用类型，如果比较的类没有冲泻equals方法，则== 和equals是一样的效果
 * == 比较引用类型，如果比较的类重写了equals方法，则==
 *
 */
public class StringTest {

    public void test1(){
        String a = "a";
        String b = "b";
        String c = a + b;
        String d = new String("ab");


        if ((a+b).equals(c)){
            System.out.println("1");
        }

        if (a+b == c){
            System.out.println("2");
        }

        if (c == d){
            System.out.println("3");
        }

        if (c.equals(d)){
            System.out.println("4");
        }

        int n1 = 2;
        int n2 = 2;
        if (n1 == n2){
            System.out.println("5");
        }
        Integer ni1 = 2;
        Integer ni2 = 2;
        if (ni1 == ni2){
            System.out.println("6");
        }
        if (ni1.equals(ni2)){
            System.out.println("7");
        }

        final int bd;
        bd = 1;
    }

    public static void main(String[] args) {
        StringTest test = new StringTest();
        test.test1();
    }
}
