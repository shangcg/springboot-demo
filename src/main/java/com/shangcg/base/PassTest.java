package com.shangcg.base;

import com.shangcg.symbol.Test;

/**
 * 测试传递与引用
 * java中不管参数类型是什么，一律传递参数的副本： 如果java传值，则传递的是值的副本、如果是传引用，那么传递的是引用的副本
 * java参数只有两种形式： 1基本类型、2 object类型，不管哪种类型，传递的都是副本，值的副本或者引用的副本
 * @author shangchenguang
 * @date 2021/7/24 10:13 上午
 */
public class PassTest {
    public static void main(String[] args) {
        PassTest t = new PassTest();
        t.first();
    }

    public void first(){
        int i = 5;
        Value v = new Value();
        v.i = 25;
        second(v, i);
        System.out.println(v.i); //20

    }

    public void second(Value v, int i){
        i = 0;
        v.i = 20;
        Value val = new Value();
        v = val;
        System.out.println(v.i + "" + i); //15 、 0
    }


}

class Value{
    public int i = 15;
}

