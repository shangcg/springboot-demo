package com.shangcg.base.exception;

/**
 * java 异常类测试
 *
 * @author shangchenguang
 * @date 2021/7/17 11:21 上午
 */
public class ExceptionTest {

    public static void main(String[] args) {

        Integer a[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Integer b = 3;
        for (int i = 0; i < a.length; i++){
            if ((a[i] & b) == 3){
                System.out.println(a[i] );
            }
        }

//
//        int aa = 0001;
//        int bb = 0010;
//        int cc = 0100;
//        System.out.println(aa);
//        System.out.println(bb);
//        System.out.println(cc);
    }

    static void test(){
//        throw new Exception("test");
    }

}
