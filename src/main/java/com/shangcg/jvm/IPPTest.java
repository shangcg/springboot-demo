package com.shangcg.jvm;

/**
 * 测试 i++问题
 */
public class IPPTest {


    /**
     * 考点：java中间变换缓存机制
     * j = j++ 可以改写为
     * int temp = j;
     * j = j + 1;
     * j = temp;
     */
    void test3(){
        int j = 0;
        for (int i = 0; i < 100; i++){
            j = j++;
        }
        System.out.println(j);
    }


    void test4(){
        int j = 0;
        for (int i = 0; i < 100; i++){
            j = ++j;
        }
        System.out.println(j);
    }

    void test5(){
        int i = 0;
        i = i++ + ++i;
        System.out.println(i);


        int j = 0;
        j = ++j + j++ + j++ + j++;
        System.out.println(j);

        int k = 0;
        k = k++ + k++ + k++ + ++k;
        System.out.println(k);

        int h = 0;
        h = ++h + ++h;
        System.out.println(h); //


        int p1 = 0, p2 = 0; int q1 = 0, q2 = 0;
        p1 = ++ p1;
        p2 = q2 ++;
        System.out.println(p1);
        System.out.println(p2);
    }

    public static void main(String[] args) {
        IPPTest ippTest = new IPPTest();
        ippTest.test3();
        ippTest.test4();
        ippTest.test5();
    }
}
