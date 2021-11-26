package com.practice;

import java.util.*;
import java.util.stream.Collectors;

class WordLadder {
    public static void main(String[] args) {
        WordLadder wl = new WordLadder();
        wl.findLadders("cet", "ism",
                Arrays.asList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"));
    }
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // convert wordList to set for fast checking
        // there is no need to revisit beginWord
        Set<String> unvisited = wordList.stream().filter(a -> !a.equals(beginWord)).collect(Collectors.toSet());

        // BFS to construct graph from beginWord to endWord
        // a node's valid children (in word list && previously unvisited)
        Map<String, List<String>> children = new HashMap<>();
        // Queue to store nodes to be visited in the next level
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        boolean found = false;
        // stop BFS either when queue is empty or endWord is found
        while (!queue.isEmpty() && !found) {
            // current visited nodes in this level
            // if same node is visited from multiple parents in one level
            // we need to record them all
            Set<String> curVisited = new HashSet<>();
            for (int i = queue.size(); i > 0; i--) {
                String word = queue.poll();
                // check curWord's every neighbor
                for (int j = 0; j < word.length(); j++) {
                    StringBuilder sb = new StringBuilder(word);
                    for (char k = 'a'; k <= 'z'; k++) {
                        sb.setCharAt(j, k);
                        String neighbor = sb.toString();
                        // in word list and hasn't been visited
                        if (unvisited.contains(neighbor)) {
                            List<String> list = children.getOrDefault(word, new ArrayList<>());
                            list.add(neighbor);
                            children.put(word, list);
                            // avoid putting same node twice in the queue
                            if (!curVisited.contains(neighbor)) {
                                queue.offer(neighbor);
                            }
                            curVisited.add(neighbor);
                            // mark found if the endWord is found
                            if (neighbor.equals(endWord)) {
                                found = true;
                            }
                        }
                    }
                }
            }
            // remove words visited in this level
            unvisited.removeAll(curVisited);
        }
        List<List<String>> ans = new ArrayList<>();
        // not found, return empty list
        if (!found)
            return ans;
        List<String> curPath = new ArrayList<>();
        curPath.add(beginWord);
        getPaths(beginWord, endWord, children, ans, curPath);

        ans.forEach(list -> {
            System.out.println("*******");
            list.forEach(System.out::println);

        });

        return ans;
    }

    // DFS (backtracking) to get all possible paths
    void getPaths(String word, String endWord, Map<String, List<String>> children, List<List<String>> ans,
                  List<String> curPath) {
        // base case, word is endWord
        if (word.equals(endWord)) {
            // must make a copy
            ans.add(new ArrayList(curPath));
            return;
        }

        List<String> childList = children.get(word);
        // childList should be null
        if (childList == null)
            return;
        for (String child : childList) {
            curPath.add(child);
            getPaths(child, endWord, children, ans, curPath);
            // remove last element
            curPath.remove(curPath.size() - 1);
        }
    }
}
