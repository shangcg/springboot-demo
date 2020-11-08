package com.shangcg.util;


import com.shangcg.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class OOM {
    public static void main(String[] args) {

        Vector v=new Vector(1000);
        for (int i=1;i<10000000; i++)
        {
            User o=new User();
            v.add(o);
            o=null;
        }
//        List<Byte[]> list = new ArrayList<>();
//        int i = 0;
//        try {
//            while (true) {
//                Object a = new Object();
//                list.add(new Byte[1024 * 1024]);//每次1M
//                i++;
//            }
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            System.out.println("次数: " + i);
//        }
    }
}