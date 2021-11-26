package com.practice;


public class ReverseKLinkedList {
    private static class ListNode<T> {
        public ListNode(T value) {
            this.value = value;
        }
        T value;
        ListNode<T> next;
    }

    public static void main(String[] args) {
        ListNode<Integer> one = new ListNode<>(1);
        ListNode<Integer> two = new ListNode<>(2);
        ListNode<Integer> three = new ListNode<>(3);
        ListNode<Integer> four = new ListNode<>(4);
        ListNode<Integer> five = new ListNode<>(5);
        ListNode<Integer> six = new ListNode<>(6);
        ListNode<Integer> seven = new ListNode<>(7);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        ListNode<Integer> res = reverseKList(one, 2);
        while (res != null) {
            System.out.print(" ==> " + res.value);
            res = res.next;
        }
    }

    public static ListNode<Integer> reverseKList(ListNode<Integer> list, int k) {
        ListNode<Integer> cur = list, prev = null, first = null,
                before = null, head = null;
        int counter = 1;

        while (cur != null) {
            if (counter == 1) {
                before = prev;
                first = cur;
            }
            if (counter == k) {
                head = head == null ? cur : head;
                reverse(before, first, cur, cur.next);
                cur = first;
            }
            counter = counter == k ? 1 : counter + 1;
            prev = cur;
            cur = cur.next;
        }
        return head;
    }

    public static void reverse(ListNode<Integer> before, ListNode<Integer> first,
                               ListNode<Integer> last, ListNode<Integer> after) {
        ListNode<Integer> cur = first, prev = null, temp = null;
        while (cur != after) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        if (before != null) before.next = last;
        first.next = after;
    }

}
