package com.shangcg.util.hash;

/**
 * 机器节点
 */
public class Node<T> {

    private String ip;
    private String name;

    public Node(String ip, String name){
        this.ip = ip;
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //使用谁做hash的key值
    @Override
    public String toString() {
        return "Node{" +
                "ip='" + ip + '\'' +
                '}';
    }
}
