package com.practice;
import java.util.*;

/*
Best friend feature:

We want to implement a feature to allow zoom users to know who are their best friends on zoom. Your best zoom friends are defined as
users who spends the most time in meetings with you. Given the map, write a function to rank the top friends.

Note: If 2 users are tied for time spent in meeting, resolve the tie by number of meetings spent, if that is still tied,
output the tied users in any order

input: userId->meetingId->timestamps
List<String> bestFriends(Map<String, Map<String, List<Long>> timestamps, String userId);

example:
user1-> [meeting1]-> [1,3,5,7]
        [meeting2]-> [7,10]
        [meeting3]-> [3,5]
        [meeting4]-> [10,12]

user2-> [meeting1]-> [1,3]

user3-> [meeting1]-> [1,2]
        [meeting2]-> [9,10]

user4-> [meeting2]-> [7,11]

output:
passing: user1 as 2nd parameter in bestFriends()
result: user4 (3 hours), user3 (2 hours, 2 meetings), user2 (2 hours, 1 meeting)
*/

// Main class should be named 'Solution'
class MeetingBestFriend {
    public static void main(String[] args) {
        Map<String, List<Integer>> meetingUser1 = new HashMap<>();
        meetingUser1.put("meeting1", Arrays.asList(1,3,5,7));
        meetingUser1.put("meeting2", Arrays.asList(7, 10));
        meetingUser1.put("meeting3", Arrays.asList(3, 5));
        meetingUser1.put("meeting4", Arrays.asList(10, 12));

        Map<String, List<Integer>> meetingUser2 = new HashMap<>();
        meetingUser2.put("meeting1", Arrays.asList(1,3));

        Map<String, List<Integer>> meetingUser3 = new HashMap<>();
        meetingUser3.put("meeting1", Arrays.asList(1, 2));
        meetingUser3.put("meeting2", Arrays.asList(9, 10));

        Map<String, List<Integer>> meetingUser4 = new HashMap<>();
        meetingUser4.put("meeting2", Arrays.asList(7, 11));

        Map<String, Map<String, List<Integer>>> timestamps = new HashMap<>();
        timestamps.put("User1", meetingUser1);
        timestamps.put("User2", meetingUser2);
        timestamps.put("User3", meetingUser3);
        timestamps.put("User4", meetingUser4);

        MeetingBestFriend mbf = new MeetingBestFriend();
        mbf.bestFriends(timestamps, "User1");
    }

    public void bestFriends(Map<String, Map<String, List<Integer>>> timestamps, String userId){
        Set<String> meetings = timestamps.get(userId).keySet();
        List<Object[]> friends = new ArrayList<>();
        for(String otherUser : timestamps.keySet()){
            if(userId.equals(otherUser)) continue;

            Set<String> commonMeetings = timestamps.get(otherUser).keySet();
            commonMeetings.retainAll(meetings);

            int totalOverlapTime = 0, totalOverlapMeetings = 0;
            for(String commonMeeting : commonMeetings){
                List<Integer> userTimes  = timestamps.get(userId).get(commonMeeting);
                Collections.sort(userTimes);
                List<Integer> otherUserTimes  = timestamps.get(otherUser).get(commonMeeting);
                Collections.sort(otherUserTimes);
                int[] overlap = overlapTime(userTimes, otherUserTimes);
                totalOverlapTime += overlap[0];
                totalOverlapMeetings += overlap[1];

            }
            Object[] friend = new Object[]{otherUser, totalOverlapTime, totalOverlapMeetings};
            friends.add(friend);
        }

        Comparator<Object[]> sortByTimeAndMeetings = (a, b) -> {
            if(a[1] == b[1]) return (int)b[2] - (int)a[2];
            else return (int)b[1] - (int)a[1];
        };
        friends.sort(sortByTimeAndMeetings);

        System.out.println("Best friend for user : " + userId);
        friends.forEach(friend -> System.out.println(friend[0] + " => " + friend[1] + " , " + friend[2]));

    }

    public int[] overlapTime(List<Integer> userTimes, List<Integer> otherUserTimes){
        int[] timeArr = setTimeArray(userTimes);
        int[] otherTimeArr = setTimeArray(otherUserTimes);
        int totalTime = 0, totalMeetings = 0;
        boolean startFlag = true;
        for(int i=0; i<timeArr.length; i++){
            if(timeArr[i] == 1 && timeArr[i] == otherTimeArr[i]){
                if(startFlag) totalMeetings++;
                else totalTime++;
                startFlag = false;
            }else startFlag = true;
        }
        return new int[]{totalTime, totalMeetings};
    }

    public int[] setTimeArray(List<Integer> userTimes){
        int[] timeArr = new int[24];
        int startTime, endTime;
        for(int i = 0; i < userTimes.size(); i++){
            if(i+1 == userTimes.size()) break;
            startTime = userTimes.get(i);
            endTime = userTimes.get(++i);
            Arrays.fill(timeArr, startTime, endTime+1, 1);
        }
        return timeArr;
    }
}

