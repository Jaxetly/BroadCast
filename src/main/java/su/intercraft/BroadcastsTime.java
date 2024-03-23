package su.intercraft;

import java.util.Collection;

public class BroadcastsTime implements Comparable<BroadcastsTime>{
    private int hours, minutes;

    BroadcastsTime(String s) throws Exception {
        String[] arr = s.split(":");
        if (arr.length != 2 || s.length() != 5) throw new Exception("Invalid time");
        minutes = Integer.parseInt(arr[1]);
        hours = Integer.parseInt(arr[0]) + minutes/60;
        minutes = minutes % 60;
    }

    BroadcastsTime(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }

    public int hours() {
        return hours;
    }
    public int minutes() {
        return minutes;
    }
    public boolean after (BroadcastsTime t) {
        if (t.hours() == hours()){
            return t.minutes() < minutes();
        } else {
            return t.hours() < hours();
        }
    }
    public boolean before (BroadcastsTime t) {
        if (t.hours() == hours()){
            return t.minutes() > minutes();
        } else {
            return t.hours() > hours();
        }
    }
    public boolean between (BroadcastsTime t1, BroadcastsTime t2) {
        return after(t1) && before(t2);
    }

    @Override
    public int compareTo(BroadcastsTime time){
        return -(time.hours()*60 + time.minutes() - hours()*60 - minutes());
    }

    @Override
    public String toString(){
        return hours() + ":" + minutes();
    }
}
