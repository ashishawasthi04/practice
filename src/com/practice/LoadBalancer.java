package com.practice;

import java.util.*;

class Server {
    String id;
    public Server(String id){
       this.id = id;
    }
}
public class LoadBalancer {
    Map<String, Integer> map = new HashMap<>();
    List<Server> list = new ArrayList<>();

    public static void main(String[] args) {
        LoadBalancer lb = new LoadBalancer();
        lb.random();
        lb.add(new Server("s1"));
        lb.add(new Server("s2"));
        lb.remove("s3");
        lb.remove("s1");
        lb.add(new Server("s4"));
    }

    public boolean add(Server server){
        if(map.containsKey(server.id)){
            System.out.printf("Add: Server with key %s already exists. %n", server.id);
            return false;
        }
        int lastIndex = list.size();
        map.put(server.id, lastIndex);
        list.add(server);
        System.out.printf("Add: Server with key %s added. %n", server.id);
        return true;
    }

    public Server remove(String id){
        if(!map.containsKey(id)){
            System.out.printf("Remove: Server with key %s doesn't exists. %n", id);
            return null;
        }
        int index = map.get(id);
        int lastIndex = list.size()-1;
        Server rmServer = list.get(index);
        map.remove(rmServer.id);
        if(index < lastIndex){
            list.set(index, list.get(lastIndex));
            list.remove(lastIndex);
        }else {
            list.remove(index);
        }
        System.out.printf("Remove: Server with key %s removed. %n", id);
        return rmServer;
    }

    public Server random(){
        int poolSize = this.list.size();
        if(poolSize == 0){
            System.out.println("Random: Pool is empty.");
            return null;
        }

        Random random = new Random();
        int index = random.nextInt(poolSize);
        System.out.printf("Random: Server with key %s returned %n", list.get(index).id);
        return list.get(index);
    }
}
