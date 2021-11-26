package com.practice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetupSchedule {
    //Example: Arrival [1, 2, 3, 3, 3]
    // Departure [2, 2, 3, 4, 4]
    public static int countMeetings(List<Integer> arrival, List<Integer> departure) {
        Map<Integer, Integer> calendar = new HashMap<>();

        for(int i = 0; i < arrival.size(); i++) {
            int startDay = arrival.get(i);
            int endDay = departure.get(i);
            while (startDay <= endDay) {
                //Check if meeting startDay exists in calendar
                if( !calendar.containsKey(startDay) ) {
                    calendar.put(startDay, endDay);
                    break;
                }else {
                    boolean equal = startDay == endDay;
                    int lDay = calendar.get(startDay);
                    // checks if start and end day are equal and exists in calendar
                    if(equal && lDay == endDay ) {
                        break;
                    } else if(endDay < lDay){
                        // Update calendar with smaller endDay and use the
                        //remaining days for other meetings
                        calendar.put(startDay, endDay);
                        endDay = lDay;
                    }
                    if(!equal) startDay++;
                }
            }
        }
        return calendar.size();
    }
}
