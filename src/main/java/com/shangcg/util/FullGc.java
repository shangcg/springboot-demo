package com.shangcg.util;


import java.util.ArrayList;
import java.util.List;

public class FullGc {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        int i = 0;
        try {
            while (true) {
//                Object a = new Object();
                list.add(new Byte[1024 * 1024*8]);
                i++;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("次数: " + i);
        }
    }
}