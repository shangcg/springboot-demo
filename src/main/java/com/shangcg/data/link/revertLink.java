package com.shangcg.data.link;

/**
 * 反转单链表
 *
 * 自然语言描述 1 -> 2 -> 3 ->4
 * 1：将单链表理解为头部为null的链表，因为反转后头节点的next为null
 * 2：定义pre指向头节点的前一个节点、p指向头节点、 q指向头节点的下一个节点
 * 3：保留 q = p.next
 * 4: p.next = null 头节点p的next指向他的前一个节点
 * 5：pre向后移动  pre = p
 * 6:p向后移动  p = q
 * 7:循环往复、直到p节点为空  while（ p != null）
 *
 * @author shangchenguang
 * @date 2021/8/4 6:00 下午
 */
public class revertLink {

    public static void main(String[] args) {
        ListNode p1 = new ListNode();
        p1.value = 1;
        ListNode p2 = new ListNode();
        p2.value = 2;
        ListNode p3 = new ListNode();
        p3.value = 3;
        ListNode p4 = new ListNode();
        p4.value = 4;

        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = null;

//        while (p1 != null){
//            System.out.println(p1.value);
//            p1 = p1.next;
//
//        }

        ListNode solution = solution(p1);

        System.out.println("反转后：");

        while (solution != null){
            System.out.println(solution.value);
            solution = solution.next;

        }


    }

    static ListNode solution(ListNode node){

        //定义3个变量， pre指向头节点前端、 p指向头节点、 q指向头节点的下一个节点
        ListNode pre = null;
        ListNode p = node;
        ListNode q;
        while (p != null){
            //4步走
            //1将头节点的下一个节点保存起来
            q = p.next;
            //2将头节点的next置为null, 即 p.next = pre
            p.next = pre;
            //3将pre向后移动 即 pre = p；
            pre = p;
            //4将p向后移动
            p = q;
        }
        return pre;
    }
}

class ListNode{
    Integer value;
    ListNode next;
}
