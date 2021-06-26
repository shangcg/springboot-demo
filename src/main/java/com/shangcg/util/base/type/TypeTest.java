package com.shangcg.util.base.type;

/**
 * java基本类型测试
 *
 * float 单精度类型，在内存中占用4个字节，32位，表示范围为：
 * double 双精度类型，在内存中占用8个字节，64位，表示范围为：
 *
 */
public class TypeTest {


    public static void main(String[] args) {
//        float a = 0.5f;
//        float b = 0.1f;
//
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(a + b);
//
//        double ret1 = 0.1 * 2;
//        System.out.println(ret1);
//
//        double ret2 = 0.2 * 2;
//        System.out.println(ret2);
//
//        double ret3 = 0.4 * 2;
//        System.out.println(ret3);
//
//
//        //特殊情况
//        System.out.println(0.5*3);
//        System.out.println(0.1*3);

        TypeTest typeTest = new TypeTest();
        typeTest.testType();
    }


    /**
     * 验证字符在计算处理中的逻辑
     * 注意字符和字符串
     * 'a'为字符  "hello"为字符串
     * 字符在计算中的存储方式为16位无符号整型 a的ascll码为97 97+3 = 100
     */
    public void testType(){
        String str1 = 'a' + 3 + "Hello";
        String str2 = "Hello" + 'a' + 3;

        System.out.println(str1);
        System.out.println(str2);
    }

}
