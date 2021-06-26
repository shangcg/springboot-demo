package com.shangcg.util.hash;

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
}
