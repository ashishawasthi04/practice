package com.practice;
/*
There is a pair of bank accounts and the corresponding balance of each account.
The final balance of each account is required to be no less than 100.
Ask which transactions you want to carry out to meet the requirements.
At the beginning no optimization is required as long as the conditions are met.
For example
{AC1:120 AC2:70 AC3:150 AC4:80}
then one of the qualified outputs:
["AC1 send 20 to AC2"
"AC3 send 10 to AC2"
"AC3 send 20 to AC4"]
*/

import java.util.*;

public class AccountBalance {
    static class Account {
        String name;
        int amount;

        public Account(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }
    }

    public static void main(String[] args) {
        Account[] accounts = new Account[]{
                new Account("AC1", 120), new Account("AC2", 70),
                new Account("AC3", 150), new Account("AC4", 80)};
        accountBalance(accounts);
    }

    public static void accountBalance(Account[] accounts) {
        Comparator<Account> comp = Comparator.comparingInt(account -> account.amount);
        Queue<Account> minQueue = new PriorityQueue<>(comp);
        Queue<Account> maxQueue = new PriorityQueue<>(comp.reversed());
        Arrays.stream(accounts).forEach(acc -> {
            minQueue.offer(acc);
            maxQueue.offer(acc);
        });
        while (!minQueue.isEmpty() && minQueue.peek().amount < 100) {
            Account minAcc = minQueue.poll();
            Account maxAcc = maxQueue.poll();
            int transferAmount = 100 - minAcc.amount;
            maxAcc.amount -= transferAmount;
            minAcc.amount = 100;
            minQueue.offer(minAcc);
            maxQueue.offer(maxAcc);
            System.out.printf("%s sends %d to %s%n", maxAcc.name, transferAmount, minAcc.name);
        }
    }
}
