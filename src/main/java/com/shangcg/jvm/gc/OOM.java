package com.shangcg.jvm.gc;


import com.shangcg.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * 内存溢出测试
 *
 * 内存溢出场景
 * 1内存泄露
 * 2非内存泄漏 (内存溢出 空间就是不够用)
 *
 *
 * 能产生溢出的空间：
 * 1 堆内存溢出
 * 2 栈内存溢出
 * 3 方法区溢出  java8还有方法区？ 不是 原空间+直接内存？
 * 4 直接内存溢出
 */
public class OOM {
    public static void main(String[] args) {

        Vector v = new Vector(1000000);
        for (int i = 1;i < 10000000; i++)
        {
            User o = new User();
            v.add(o);
            o = null;
        }
    }
}