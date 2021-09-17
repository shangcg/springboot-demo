package com.shangcg.util.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *  hash算法具体实现
 *  MurMur hash算法，暂且不用管， 当作黑盒
 */
public class HashServiceImpl implements IHashService{
    @Override
    public Long hash(String key) {
        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8){
            k = buf.getLong();
            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }


    public static void main(String[] args) {
        final String url = "https://www.howardliu.cn/how-to-use-branch-efficiently-in-git/index.html?spm=5176.12825654.gzwmvexct.d118.e9392c4aP1UUdv&scm=20140722.2007.2.1989";
        final HashFunction hf = Hashing.murmur3_128();
        final HashCode hashCode = hf.newHasher().putString(url, Charsets.UTF_8).hash();
        final int hashCodeAsInt = hashCode.asInt();// 这里选择返回 int 值，也可以选择返回 long 值
        System.out.println(hashCodeAsInt);// 输出的结果是：1810437348，转换成 62 进制是：1Ywpso

    }
}
