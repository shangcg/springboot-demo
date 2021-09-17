package com.shangcg.util.hash;

/**
 * 一致性hash接口
 * 一致性hash存在的原因：
 * 在做集群的时候，我们可以通过hash算法取余来分配数据落在哪个节点，取余的数字通常是机器节点的个数，这样会存在问题：1当某个机器节点挂了，取余的数字需要改变，得到的结果肯定就不是我们想要的了，增加某个节点也是一样的
 * 所以就引入了一致性hash算法：他的模数是2^ 32次方，将整个hash空间组织成一个虚拟的圆环，hash函数的值空间为  X % 2^32 =（0 - 2^32 -1）
 *
 */
public interface IHashService {
    Long hash(String key);
}
