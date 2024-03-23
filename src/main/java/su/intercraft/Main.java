package su.intercraft;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<String, List<Programm>> map = new HashMap<>();
        List<Programm> allProgramm = new LinkedList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/schedule.txt")))
        {
            String line;
            String programmName = null;
            List<Programm> list = new LinkedList<>();
            br.readLine();
            while((line=br.readLine())!=null){
                if (line.charAt(0) == '#') {
                    if (programmName != null){
                        Collections.sort(list);
                        map.put(programmName, list);
                        list = new LinkedList<>();
                    }
                    programmName = line;
                } else {
                    BroadcastsTime time = new BroadcastsTime(line);
                    Programm programm = new Programm(programmName, br.readLine(), time);
                    list.add(programm);
                    allProgramm.add(programm);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        Collections.sort(allProgramm);
        System.out.println(allProgramm);

        java.time.LocalTime currentTime = java.time.LocalTime.now();
        BroadcastsTime currentBroadcastsTime = new BroadcastsTime(currentTime.getHour(), currentTime.getMinute());
        printAllCurrentProgramm(map, currentBroadcastsTime);
        printProgrammWithName(allProgramm, "ПОДКАСТ.ЛАБ.");
    }

    public static void printAllCurrentProgramm(HashMap<String, List<Programm>> map, BroadcastsTime currentTime){
        Iterator<Map.Entry<String, List<Programm>>> iterator = map.entrySet().iterator();
        map:
        while (iterator.hasNext()) {
            Map.Entry<String, List<Programm>> entry = iterator.next();
            String key = entry.getKey();
            List<Programm> list = entry.getValue();
            for (int i = 0; i < list.size()-1; i++){
                if (currentTime.between(list.get(i).time, list.get(i+1).time)){
                    System.out.println(list.get(i));
                    continue map;
                }
            }
        }
    }
    public static void printProgrammWithName(List<Programm> list, String str){
        for (int i = 0; i < list.size()-1; i++){
            if (list.get(i).name.contains(str)){
                System.out.println(list.get(i));
            }
        }
    }

}