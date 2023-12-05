package aoc.day1;

import aoc.Data;

import java.util.ArrayList;


public class day1 {
    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }
    static int part1(){
        ArrayList<String> data = new Data().getData("day1");
        int total = 0;
        for(String s : data) {
            String stripped = s.replaceAll("[^0-9]", "");
            total += Integer.parseInt(String.valueOf(stripped.charAt(0)) + stripped.charAt(stripped.length() - 1));
        }
        return total;
    }

    static int part2(){
        ArrayList<String> data = new Data().getData("day1");
        int total = 0;
        for(String str : data) {
            String stripped = str.replaceAll("one", "o1ne").replaceAll("two", "t2wo")
                    .replaceAll("three", "t3hree").replaceAll("four", "f4our").replaceAll("five", "f5ive")
                    .replaceAll("six", "s6ix").replaceAll("seven", "s7even").replaceAll("eight", "e8ight")
                    .replaceAll("nine", "n9ine").replaceAll("[^0-9]", "");
            total += Integer.parseInt(String.valueOf(stripped.charAt(0)) + stripped.charAt(stripped.length() - 1));
        }

        return total;
    }


}
