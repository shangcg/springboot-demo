package com.shangcg.jvm;

/**
 * 初始化顺序问题
 */
public class InitLevel {

    private static int a = 10;

    InitLevel(){
        a++;
        b++;

        System.out.println(initLevel.a);
        System.out.println(initLevel.b);
    }

    private static InitLevel initLevel = new InitLevel();

    private static int b = 10;



    public static void main(String[] args) {

//        System.out.println(initLevel.a);
//        System.out.println(initLevel.b);
    }
}
