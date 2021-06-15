package com.shangcg.symbol;

import org.springframework.stereotype.Component;

@Component
public class Test {

    public static void main(String[] args) {


//        System.out.println("Test.main");
//        Map map = new HashMap();
//        map.put("b", 123);
//        map.put("b",456);
//
//        HashMap map1 = new HashMap();
//        TestObject t = new TestObject();
//        int a = 5; int b =3;
//        Integer cc = a ^ b;
//
//
//        String aa = "abc";
////        Object a = map.computeIfAbsent("a", k -> new HashSet<>(5));
////
////        Object b = map.computeIfAbsent("b", k -> new HashSet<>(5));
//
//        System.out.println(cc);
//        cacl();

        add();
    }

    static void cacl(){

        //下一年度自由现金流基准
        double t = 100;

        //折现
        Double R = 0.08;

        //增长
        Double increase = 0.20;

        //永续年金增长率
        double g = 0.05;

        float total = 0;

        float total_zx = 0;


        double base= 1 + increase;

        double base_zx = 1 + R;

        float a = 0;

        //总股本
        double gb = 54.22 * Math.pow(10,8);

        for (int i = 1 ; i <= 10; i++){

            a = (float) (t * Math.pow(base, i));
            System.out.println(i +"年" + a);

            total +=  a;

            float b = (float) (a / Math.pow(base_zx, i));
            total_zx += b;
        }

        System.out.println("总和：" + total);
        System.out.println("折现：" + total_zx);

        //永续年金
        float yx = (float) (a * (1+ g) / (R - g));

        float yx_zx = (float) (yx / Math.pow(base_zx, 10));

        System.out.println("永续年金： " + yx);
        System.out.println("永续年金折现" + yx_zx);

        //所有者权益
        float syz = total_zx + yx_zx;

        System.out.println("折现现金流 + 永续折现：" + syz);

        //每股价值
        float price = (float) (syz * Math.pow(10, 8)/ gb);

        System.out.println("每股价格：" + price);
    }




//    市盈率(静)：60.91  股价/上年度年报基本每股收益
//    市盈率(动)：55.65  股价/本年度年末预测的基本每股收益 含有预测成分  茅台来说：1998 / 55.65 = 35.9 需要每股基本收益达到35.9,预测才准确  预测准则：同期对比，算出年末
//    市盈率(TTM)：56.30 滚动市盈率 股价/向前推4个季度的基本每股收益
//
//    都可以按照  归属股东净利润/总股份
//
//    归属股东净利润：上年、 本年预测的、 向前推4个季度的
//
//    雪球每股收益即 每股收益（TTM） 向前推4个季度的归属股东的净利润之和/股本   向前推4个季度的归属股东的净利润之和 = 本年度的3个季度 + (上年4季度-3季度)
//
//    动态市盈率= 股票现价÷未来每股收益的预测值

//    市价用的都是滚动市盈率 * 滚动每股收益



    public static void add(){
        Double a = 50.00;
        Double crease = 0.10;
        Double total = 0.0;
        for (int i = 1; i < 50; i++){
            double pow = a * Math.pow((1 + crease), i);
            total += pow;
            System.out.println("第"+ i +"年回收金额：" + pow);
            System.out.println("回收总金额：" + total);
        }
    }
}
