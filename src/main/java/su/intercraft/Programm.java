package su.intercraft;

public class Programm implements Comparable<Programm>{
    public String chanel;
    public String name;
    public BroadcastsTime time;

    Programm(String chanel, String name, BroadcastsTime time){
        this.chanel = chanel;
        this.name = name;
        this.time = time;
    }

    @Override
    public String toString(){
        return "(chanel: \"" + chanel + "\"; " + "name: \"" + name + "\"; " + "time: \"" + time + "\")";
    }

    @Override
    public int compareTo(Programm programm) {
        return time.compareTo(programm.time);
    }
}
