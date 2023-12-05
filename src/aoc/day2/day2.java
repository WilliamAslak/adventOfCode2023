package aoc.day2;

import aoc.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class day2 {

    public static void main(String[] args) {
        ArrayList<String> data = new Data().getData("day2");
        System.out.println(part1(data));
        System.out.println(part2(data));

    }
    static int part1(ArrayList<String> data) {

        int total = 0;
        for(String s : data){
            HashMap<String,Integer> cubeValues = new HashMap<String,Integer>();

            String[] cubes = s.split(":")[1].split(";");
            //this is some fucked up nesting 💀💀💀
            for(String c : cubes) {
                String[] v = c.split(",");
                for(String d : v) {
                    //System.out.println("amount: " + d.split(" ")[1] + " color: " + d.split(" ")[2]);
                    if(cubeValues.get(d.split(" ")[2]) == null ||
                       cubeValues.get(d.split(" ")[2]) < Integer.parseInt(d.split(" ")[1]))
                        cubeValues.put(d.split(" ")[2],Integer.parseInt(d.split(" ")[1]));
                }
            }

            /* Prints the highest values of each game.
            System.out.println("round:"+s.split(":")[0].split(" ")[1]);
            for (Map.Entry<String, Integer> a : cubeValues.entrySet()){
                System.out.println(a.getKey()+" "+a.getValue());
            }
            */
            if(cubeValues.get("red") <= 12 && cubeValues.get("green") <= 13 && cubeValues.get("blue") <= 14)
                total+=Integer.parseInt(s.split(":")[0].split(" ")[1]);
        }


        return total;
    }

    static int part2(ArrayList<String> data){
        int total = 0;
        for(String s : data){
            HashMap<String,Integer> cubeValues = new HashMap<String,Integer>();

            String[] cubes = s.split(":")[1].split(";");
            //this is some fucked up nesting 💀💀💀
            for(String c : cubes) {
                String[] v = c.split(",");
                for(String d : v) {
                    //System.out.println("amount: " + d.split(" ")[1] + " color: " + d.split(" ")[2]);
                    if(cubeValues.get(d.split(" ")[2]) == null ||
                            cubeValues.get(d.split(" ")[2]) < Integer.parseInt(d.split(" ")[1]))
                        cubeValues.put(d.split(" ")[2],Integer.parseInt(d.split(" ")[1]));
                }
            }
            int powerSet = 1;
            for (Map.Entry<String, Integer> a : cubeValues.entrySet()){
                powerSet*=a.getValue();
            }
            total += powerSet;
        }


        return total;
    }

}
