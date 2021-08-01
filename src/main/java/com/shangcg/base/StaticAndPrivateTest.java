package com.shangcg.base;

/**
 * 静态变量和作用域测试
 *
 * @author shangchenguang
 * @date 2021/7/24 10:36 上午
 */
public class StaticAndPrivateTest {


    //说明：基本类型变量都有默认值，只有定义没有初始化会有默认值
    static boolean a;
    static int b;
    static long c;
    static double d;
    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

//        System.out.println(args[1]);
    }


}
