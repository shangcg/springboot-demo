package com.shangcg.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * G1收集器  日志测试
 *
 * G1收集器和日志  下边配置会生成g1.log， g1.log详解在/src/main/test/gclog/g1.log 注释中
 * vm_options: -Xmx10M -Xms10M -XX:+PrintGCDetails -XX:+UseG1GC -Xloggc:./src/main/test/gclog/g1.log
 *
 *
 */
public class G1Test {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        try {
            list.add(new Byte[1024 * 1024]);
            list.add(new Byte[1024 * 1024]);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}


/**
 * #JVM版本
 * Java HotSpot(TM) 64-Bit Server VM (25.171-b11) for bsd-amd64 JRE (1.8.0_171-b11), built on Mar 28 2018 12:50:57 by "java_re" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
 *
 * #内存信息 每页大小
 * Memory: 4k page, physical 16777216k(333076k free)
 *
 * /proc/meminfo:
 *
 * CommandLine flags: -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=20971520 -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC
 *
 *
 * 0.238: [GC pause (G1 Humongous Allocation) (young) (initial-mark), 0.0110263 secs]
 *    [Parallel Time: 10.0 ms, GC Workers: 4]
 *       [GC Worker Start (ms): Min: 238.3, Avg: 238.4, Max: 238.8, Diff: 0.5]
 *       [Ext Root Scanning (ms): Min: 0.6, Avg: 0.9, Max: 1.7, Diff: 1.1, Sum: 3.6]
 *       [Update RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
 *          [Processed Buffers: Min: 0, Avg: 0.0, Max: 0, Diff: 0, Sum: 0]
 *       [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
 *       [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
 *       [Object Copy (ms): Min: 0.0, Avg: 0.8, Max: 1.1, Diff: 1.1, Sum: 3.2]
 *       [Termination (ms): Min: 0.0, Avg: 6.2, Max: 8.3, Diff: 8.3, Sum: 24.7]
 *          [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 4]
 *       [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
 *       [GC Worker Total (ms): Min: 1.7, Avg: 7.9, Max: 10.0, Diff: 8.3, Sum: 31.6]
 *       [GC Worker End (ms): Min: 240.6, Avg: 246.3, Max: 248.3, Diff: 7.7]
 *    [Code Root Fixup: 0.0 ms]
 *    [Code Root Purge: 0.0 ms]
 *    [Clear CT: 0.1 ms]
 *    [Other: 1.0 ms]
 *       [Choose CSet: 0.0 ms]
 *       [Ref Proc: 0.8 ms]
 *       [Ref Enq: 0.0 ms]
 *       [Redirty Cards: 0.0 ms]
 *       [Humongous Register: 0.0 ms]
 *       [Humongous Reclaim: 0.0 ms]
 *       [Free CSet: 0.0 ms]
 *    [Eden: 4096.0K(6144.0K)->0.0B(2048.0K) Survivors: 0.0B->1024.0K Heap: 7579.2K(10.0M)->5017.6K(10.0M)]
 *  [Times: user=0.00 sys=0.00, real=0.01 secs]
 * 0.249: [GC concurrent-root-region-scan-start]
 * 0.250: [GC concurrent-root-region-scan-end, 0.0009063 secs]
 * 0.250: [GC concurrent-mark-start]
 * 0.253: [GC concurrent-mark-end, 0.0026407 secs]
 * 0.253: [GC remark 0.253: [Finalize Marking, 0.0005880 secs] 0.254: [GC ref-proc, 0.0000261 secs] 0.254: [Unloading, 0.0007367 secs], 0.0014780 secs]
 *  [Times: user=0.00 sys=0.00, real=0.00 secs]
 * 0.255: [GC cleanup 9134K->9134K(15M), 0.0001777 secs]
 *  [Times: user=0.00 sys=0.00, real=0.00 secs]
 * Heap
 *  garbage-first heap   total 15360K, used 9113K [0x00000007bec00000, 0x00000007bed00078, 0x00000007c0000000)
 *   region size 1024K, 2 young (2048K), 1 survivors (1024K)
 *  Metaspace       used 3394K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 372K, capacity 388K, committed 512K, reserved 1048576K
 */
