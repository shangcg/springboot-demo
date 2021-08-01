package com.shangcg.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 * 输入输出流测试
 *
 * @author shangchenguang
 * @date 2021/7/24 10:46 上午
 */
public class IOTest {

    /**
     * 写一段代码，将字符传输出到文件
     */
    //说明：因为是输出到文件，所以对于内存来说，是从内存往文件写数据，需要输出流，字符串输出用outputStream
    public void outFile() throws Exception {
        File file = new File("/Users/shangchenguang/Documents/test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/shangchenguang/Documents/test.txt");
        fileOutputStream.write("我是字符串".getBytes());
        fileOutputStream.close();
    }

    public static void main(String[] args) throws Exception {
//        IOTest ioTest = new IOTest();
//        ioTest.outFile();

        keyInput();
    }

    /**
     * 从键盘输入数据计算平方和立方
     */
    public static void keyInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("输入数字：");
        String s = input.next();

        System.out.println("pingfang "+ Integer.valueOf(s) * Integer.valueOf(s));
    }
}
