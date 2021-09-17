package com.shangcg.jvm;

/**
 * 类型转换知识点总结
 *
 */
public class TypeCast {

    //验证编译是否正常
    void test(){
//        Short myshort = 99S;  编译失败
        float z = 1.0f;
        float t = 1;
//        float m = 1.0; //编译失败

        int m = "abc".length();

//        char c = 17c; 编译错误
    }

    //此种定义形式是采用八进制定义 012 = 1*8^1 + 2* 8^0 = 10
    void test2(){
        int i = 012;
        int j = 034;
        int k = (int)056L;
//        int l = 078;  编译错误，八进制最大为7

        System.out.println(i);
        System.out.println(j);
        System.out.println(k);

//        System.out.println(l);
    }


    void test3(){
//        short s = 1;  s = s + 1; 编译错误， s+1是int类型，不能赋值给s
        short s = 1; s += 1;
    }

    // 10.9是浮点型，会导致后边9直接进行类型转换 10.9是double是类型， float的小数表示必须有f结尾
    void test4(){
        int a = 5;
        System.out.println("value is " + ((a < 5)? 10.9 : 9));
    }

    // java编程规范规定： 三木运算符后两个表达式中有一个是常量表达式，
    // 另外一个类型是T，而常量能用T表示，则输出类型是T类型，
    // 本方法中常量是10 T类型是char，输出结果为char
    void test5(){
        char x = 'x';
        int i = 10;
        System.out.println(false ? i: x);
        System.out.println(false ? 10: x);
    }


    void test6(){
        if (5 == 5 | 6 ==6){

        }
    }

    //重点1  main()方法可以重载，即可以改变参数类型，可以没有参数
    //重点2  main()方法必须是public
    public static void main(String[] args) {
        TypeCast typeCast = new TypeCast();
        typeCast.test2();
        typeCast.test4();
        typeCast.test5();
    }

//    public static void main(String[] args) {
//        main();
//    }

     static void main(int args) {

    }
}
