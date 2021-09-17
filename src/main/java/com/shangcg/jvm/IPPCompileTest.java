package com.shangcg.jvm;

/**
 * 测试i++ 编译后重排序
 * javap -v
 *
 * @author shangchenguang
 * @date 2021/8/4 9:01 下午
 */
public class IPPCompileTest {


    public static void main(String[] args) {
        int i = 0;
        i ++ ;
        System.out.println(i);
    }
}
