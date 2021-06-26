package com.shangcg.util.hash;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性hash操作
 * @param <T>
 */
public class ConsistentHash<T> {
    //hash函数接口
    private final IHashService iHashService;
    //每个机器节点关联的虚拟节点数量
    private final int numberOfReplicas;
    //环形虚拟节点
    private final SortedMap<Long, T> circle = new TreeMap<>();


    public ConsistentHash(IHashService iHashService, int numberOfReplicas, Collection<T> nodes) {
        this.iHashService = iHashService;
        this.numberOfReplicas = numberOfReplicas;
        for (T node : nodes){
            add(node);
        }
    }

    /**
     * 增加真实机器节点
     * @param node
     */
    public void add(T node){
        for (int i = 0; i < numberOfReplicas; i++){
            circle.put(this.iHashService.hash(node.toString()) + i, node);
        }
    }

    /**
     * 删除真实机器节点
     * @param node
     */
    public void delete(T node){
        for (int i = 0; i< numberOfReplicas; i++){
            circle.remove(this.iHashService.hash(node.toString()) + i);
        }
    }

    //查找节点
    public T get(String key){
        if (circle.isEmpty()){
            return null;
        }

        Long hash = iHashService.hash(key);
        if (!circle.containsKey(hash)){
            SortedMap<Long , T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

}


