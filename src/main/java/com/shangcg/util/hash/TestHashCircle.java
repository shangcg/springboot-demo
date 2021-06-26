package com.shangcg.util.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// 未完成
public class TestHashCircle {

    private static final String IP_PREFIX = "192.168.0.1";

    public static void main(String[] args) {
        //每台机器节点上保存的记录条数
        Map<String, Integer> map = new HashMap<>();

        //真是机器节点
        List<Node<String>> nodes = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            map.put(IP_PREFIX + i, 0); // 初始化记录
            Node<String> node = new Node<String>(IP_PREFIX + i, "node" + i);
            nodes.add(node);
        }

    }

}
