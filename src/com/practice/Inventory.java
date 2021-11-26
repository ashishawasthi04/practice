package com.practice;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    Map<String, Integer> itemCounts = new HashMap<>();
    Map<Integer, Integer> countItems = new HashMap<>();

    public Inventory(){ }

    public void add(String item){
        int count = itemCounts.getOrDefault(item, 0);
        itemCounts.put(item, count+1);

        int countOfItems = countItems.getOrDefault(count, 0);
        if(countOfItems == 1){
            countItems.remove(count);
        }else if(countOfItems > 1){
            countItems.put(count, countOfItems-1);
        }

        countItems.put(count+1, countItems.getOrDefault(count+1, 0)+1);
        System.out.println("After Add");
        System.out.println(itemCounts);
        System.out.println(countItems);
    }

    public Integer getItemCount(String item){
        System.out.println("Item Count: " + itemCounts.get(item));
        return itemCounts.get(item);
    }

    public boolean remove(String item){
        if(!itemCounts.containsKey(item)) return false;

        int count = itemCounts.get(item);
        if(count == 1){
            itemCounts.remove(item);
        }else{
            itemCounts.put(item, count-1);
        }

        int countOfItems = countItems.getOrDefault(count, 0);
        if(countOfItems == 1){
            countItems.remove(count);
        }else if(countOfItems > 1){
            countItems.put(count, countOfItems-1);
        }
        if(count > 1){
            countItems.put(count-1, countItems.getOrDefault(count-1, 0)+1);
        }

        System.out.println("After Remove");
        System.out.println(itemCounts);
        System.out.println(countItems);
        return true;
    }

    public boolean lookup(int count){
        System.out.println("Lookup: " + countItems.containsKey(count));
        return countItems.containsKey(count);
    }

    public static void main(String[] args) {
        Inventory inv = new Inventory();
        inv.add("Soap");
        inv.add("Brush");
        inv.add("Soap");
        inv.getItemCount("Soap");
        inv.remove("Soap");
        inv.remove("Brush");

        inv.lookup(1);
        inv.lookup(2);

    }
}
