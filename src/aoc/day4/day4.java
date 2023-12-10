package aoc.day4;

import aoc.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.lang.Math;

public class day4 {
    public static void main(String[] args) {
        ArrayList<String> data = new Data().getData("day4");
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    static int part1(ArrayList<String> data){
        int sum = 0;
        for(String s : data){
            sum += (valueOfCard(s) == 0) ? 0 : (int) Math.pow(2, valueOfCard(s) - 1);
        }


        return sum;
    }
    static int valueOfCard(String s){
        Boolean addKeys = true;
        HashMap<Integer, Integer> tempMap = new HashMap<>();
        for(String str : s.split(":")[1].split(" ")) {
            if (str.length() > 0) {
                if (str.equals("|")) {
                    addKeys = false;
                }
                else if (addKeys) {
                    tempMap.put(Integer.parseInt(str), 0);
                }
                else if (tempMap.containsKey(Integer.parseInt(str))) {
                    tempMap.put(Integer.parseInt(str), tempMap.get(Integer.parseInt(str)) + 1);
                }
            }

        }
        int cardValue = 0;
        for(Integer val : tempMap.values()){
            if(val>0)
                cardValue++;
        }

        return cardValue;
    }



    static int part2(ArrayList<String> data){
        int[] amountSeen = new int[data.size()];
        Arrays.fill(amountSeen, 1);
        //this is probably a terrible way to do this considering its terrible O-notation
        for(int i = 0; i < data.size(); i++)
            for(int j = 0; j < amountSeen[i]; j++)
                for (int val = i+1; val < i+valueOfCard(data.get(i))+1; val++)
                    if (val < amountSeen.length)
                        amountSeen[val]++;

        return Arrays.stream(amountSeen).sum();
    }


}
