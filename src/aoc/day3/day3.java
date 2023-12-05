package aoc.day3;

import aoc.Data;

import java.util.ArrayList;
import java.util.Objects;

public class day3 {
    public static void main(String[] args) {
        ArrayList<String> data = new Data().getData("day3");
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    static int part1(ArrayList<String> data){
        for(int i = 0; i < data.size(); i++){


            System.out.println(data.get(i));
            for(int j : findIndexes(data.get(i)))
                if(checkSurrounding(data,j,i)) System.out.println(j);

        }
        return 0;
    }
    static ArrayList<Integer> findIndexes(String s){
        //Finds the numbers in the string.
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < s.length(); i++)
            if(String.valueOf(s.charAt(i)).matches("[0-9]"))
                indexes.add(i);
        return indexes;
    }
    static boolean checkSurrounding(ArrayList<String> data, int x, int y){

        return false;
    }

    static int part2(ArrayList<String> data){
        return 0;
    }
}
