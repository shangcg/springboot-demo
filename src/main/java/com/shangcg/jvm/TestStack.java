package com.shangcg.jvm;

import org.junit.Test;


/**
 * 栈溢出demo
 */
public class TestStack {

    private  int count = 0;

    public void recursion() throws InterruptedException {
        count ++;
        Thread.sleep(50000);
        recursion();
    }

    @Test
    public void testStack(){
        try {
            recursion();
        }catch (Throwable e){
            System.out.println("deep of stack is " + count);
            e.printStackTrace();
        }
    }

    public static void test(){
        System.out.println("sss");
    }


    public static void main(String[] args) {
        ((TestStack)(null)).test();
    }
}
