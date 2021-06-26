package com.shangcg.data;


/**
 * 移动零
 * 给定一个数组nums, 编写一个函数将所有0移动到数组末尾，同时保持非零元素的相对顺序
 * 不使用额外的空间，尽量减少操作次数
 * 返回移除0元素后的数组长度
 *
 * 解法一： 自然语言描述
 * 1： 定义数组下个待放入元素下标cur
 * 2: 遍历数组nums从i=0到i=nums.length-1 遍历内部 nums[i] = 0是否成立， 成立不做处理，不成立 nunms[cur++] = nums[i]  i++
 * 3: 遍历结束后，原数组后边的元素 nums.length - cur 全部变为0即可
 */
public class MoveZeros {

    public int moveElement(int[] nums){
        int cur = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != 0){
                nums[cur++] = nums[i];
            }
        }
        for (int i = nums.length -1 ; i >= cur; i--){
            nums[i] = 0;
        }
        return cur;
    }

    public static void main(String[] args) {
        int [] a = {0, 1, 2, 3, 4, 4, 0};
        MoveZeros solution = new MoveZeros();
        int ret = solution.moveElement(a);
        System.out.println("数组长度：" +ret);

        for (int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }

        while (true){
            System.out.println(11111);
        }
    }


//    public static void main( String[] args ) {
//        Set<Integer> set = new HashSet<>();
//        set.add(20210504);
//        set.add(20210505);
//        set.add(20210506);
//        System.out.println(set.toString());
//        //这里后续用有序的list处理即可，因为流中进行了倒序处理，收集成set后会重排
//        List<Integer> collect = set.stream().sorted().collect(Collectors.toList());
//        System.out.println(collect);
//    }


}
