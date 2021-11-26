package com.practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
This problem was asked by Dropbox.

Create a data structure that performs all the following operations in O(1) time:

plus: Add a key with value 1. If the key already exists, increment its value by one.
minus: Decrement the value of a key. If the key's value is currently 1, remove it.
get_max: Return a key with the highest value.
get_min: Return a key with the lowest value.

https://www.dailycodingproblem.com/solution/829?token=33f7581b7372fb64987283749ae5e57e55ead2e81d9cbcb71842b61b61568a3328ad3359
 */
public class InventoryLookup {
    public static void main(String[] args) {
        Inventory inv = new Inventory();
        inv.plus("Ashu");
        inv.plus("Garima");
        inv.plus("Rashu");
        inv.plus("Ashu");
        inv.plus("Ashu");
        inv.plus("Garima");
        inv.plus("Ashu");
        inv.plus("Garima");
        inv.plus("Rashu");

        System.out.println(inv.getMax());
        System.out.println(inv.getMin());

        inv.minus("Ashu");
        inv.minus("Ashu");
        inv.minus("Ashu");
        inv.minus("Ashu");
        inv.minus("Garima");
        inv.minus("Garima");

        System.out.println(inv.getMax());
        System.out.println(inv.getMin());

    }
    static class Node {
        int val;
        Set<String> items = new HashSet<>();
        Node prev;
        Node next;
        public Node(int val, String item){
            this.val = val;
            items.add(item);
        }
    }
    static class Inventory {
        Node head = null, tail = null;
        Map<String, Node> store = new HashMap<>();

        private void addOrMoveToNext(Node cur, Node prevOrNext, String item){
            if(cur.val == prevOrNext.val){
                store.put(item, cur);
                cur.items.add(item);
            }else {
                cur.items.remove(item);
                if(cur.items.isEmpty()){
                    if(cur.prev == prevOrNext){
                        prevOrNext.next = cur.next;
                        if(cur.next != null) cur.next.prev = prevOrNext;
                        else tail = prevOrNext;
                    }else {
                        prevOrNext.prev = cur.prev;
                        if(cur.prev != null) cur.prev.next = prevOrNext;
                        else head = prevOrNext;
                    }
                }
                prevOrNext.items.add(item);
                store.put(item, prevOrNext);
            }

        }

        private void insertBetween(Node first, Node second, Node cur, String item){
            store.put(item, cur);
            if(first != null) first.items.remove(item);
            if(second != null) second.items.remove(item);
            if(first == null){
                if(second != null && second.items.isEmpty()){
                    if(tail == second) tail = cur;
                    second = second.next;
                }
                cur.next = second;
                if(second != null){
                    second.prev = cur;
                    cur.next = second;
                }
                head = cur;
                if(tail == null) tail = head;
            } else {
                if(second == null){
                    if(first != null && first.items.isEmpty()) {
                        if (head == first) head = cur;
                        first = first.prev;
                    }
                    cur.prev = first;
                    if(first != null){
                        first.next = cur;
                        cur.prev = first;
                    }
                    tail = cur;
                    if(head == null) head = tail;
                }else {
                    first.next = cur;
                    cur.next = second;
                    cur.prev = first;
                    second.prev = cur;
                }
            }
        }

        public void plus(String item){
            if(!store.containsKey(item)){
                if(head == null || head.val > 1){
                    insertBetween(null, head, new Node(1, item), item);
                }else if(head.val == 1){
                    addOrMoveToNext(head, head, item);
                }
            }else {
                Node node = store.get(item);
                if(node.next != null && node.next.val == node.val + 1){
                    addOrMoveToNext(node, node.next, item);
                }else {
                    insertBetween(node, node.next, new Node(node.val+1, item), item);
                }
            }
        }
        public void minus(String item){
            if(store.containsKey(item)){
                Node node = store.get(item);
                if(node.prev != null && node.prev.val == node.val - 1){
                    addOrMoveToNext(node, node.prev, item);
                }else {
                    if(node.val > 1){
                        insertBetween(node.prev, node, new Node(node.val-1, item), item);
                    }else {
                        node.items.remove(item);
                        if(node.items.isEmpty()){
                            head = node.next;
                            if(node.next != null) node.next.prev = null;
                        }
                        store.remove(item);
                    }

                }
            }
        }

        public int getMax(){
            return tail != null ? tail.val : 0;
        }


        public int getMin(){
            return head != null ? head.val : 0;
        }
    }

}
