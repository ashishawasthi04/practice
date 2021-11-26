package com.practice;

public class MergeKSortedList {
    public static void main(String[] args) {

    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public ListNode mergeKLists(ListNode[] lists) {
      if(lists == null || lists.length == 0) return null;
      return merge(lists, 0, lists.length-1);
  }

  private ListNode merge(ListNode[] lists, int start, int end){
      if(start == end) return lists[start];
      int mid = (start+end)/2;
      ListNode left = merge(lists, start, mid);
      ListNode right = merge(lists, mid+1, end);
      return mergeTwoLists(left, right);
  }

  private ListNode mergeTwoLists(ListNode l1, ListNode l2){
      if(l1 == null) return l2;
      if(l2 == null) return l1;
      ListNode dummy = new ListNode();
      ListNode cur = dummy;
      while(l1 != null && l2 != null){
          if(l1.val < l2.val){
              cur.next = l1;
              l1 = l1.next;
          }else{
              cur.next = l2;
              l2 = l2.next;
          }
          cur = cur.next;
      }
      if(l1 != null) cur.next = l1;
      if(l2 != null) cur.next = l2;
      return dummy.next;
  }

}
