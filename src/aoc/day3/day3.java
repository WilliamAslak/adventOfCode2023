package aoc.day3;

import aoc.Data;

import java.util.ArrayList;

public class day3 {
    public static void main(String[] args) {
        ArrayList<String> data = new Data().getData("day3");
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    static int part1(ArrayList<String> data){
        int sum = 0;
        for(int i = 0; i < data.size(); i++){

            //Find all the indices of the numbers with a neighbouring special character.
            ArrayList<Integer> numberIndices = new ArrayList<>();
            for(int j : findIndexes(data.get(i),false))
                if(checkSurrounding(data,j,i,false)) numberIndices.add(j);

            removeDupes(numberIndices,data.get(i));

            //now converts all indices into real numbers.
            for(int j : numberIndices) {
                sum += convertToInteger(data.get(i), j);
            }

        }
        return sum;
    }
    static ArrayList<Integer> findIndexes(String s,boolean checkStar){
        //Finds the numbers in the string.
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < s.length(); i++)
            if(String.valueOf(s.charAt(i)).matches("[0-9]") && !checkStar)
                indexes.add(i);
            else if(String.valueOf(s.charAt(i)).matches("[*]") && checkStar)
                indexes.add(i);

        return indexes;
    }
    static boolean checkSurrounding(ArrayList<String> data, int x, int y, boolean checkStar){
        //A never-nesters worst nightmare
        for (int i = y-1; i < y + 2; i++)
            for (int j = x-1; j < x + 2; j++) {
                if (i >= 0 && i < data.size() && j >= 0 && j < data.get(i).length())
                    if(!checkStar){
                        if(String.valueOf(data.get(i).charAt(j)).matches("[^0-9.]"))
                            return true;
                    } else if(String.valueOf(data.get(i).charAt(j)).matches("[*]")){
                        return true;

                    }

                }
        return false;
    }
    static void removeDupes(ArrayList<Integer> numberIndices, String s){
        for(int j = 0; j < numberIndices.size(); j++){
            //If connected to the same number removes current index, and only keeps the last one.
            if(numberIndices.size()>j+1 && s.substring(numberIndices.get(j), numberIndices.get(j + 1)).matches("[0-9]")) {
                numberIndices.remove(j);
                j--;
            }
        }
    }

    static int convertToInteger(String s, int index){
        StringBuilder val = new StringBuilder();

        for(int i = index; i >= 0; i--)
            if(Character.isDigit(s.charAt(i))) {
                val.insert(0, s.charAt(i));
            } else break;

        for(int i = index+1; i < s.length(); i++)
            if(Character.isDigit(s.charAt(i))) {
                val.append(s.charAt(i));
            } else break;
        return Integer.parseInt(val.toString());


    }

    static int part2(ArrayList<String> data){
        //this solution is not exactly the best solution, but it is a solution nonetheless.
        //TODO: revisit and optimize
        int sum = 0;
        ArrayList<int[]> starNeighbours = new ArrayList<>();
        ArrayList<int[]> starCoords = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            for(int j : findIndexes(data.get(i),false)) {
                if(checkSurrounding(data,j,i,true)){
                    if(starNeighbours.size()==0 || starNeighbours.get(starNeighbours.size()-1)[0] != j-1)
                        starNeighbours.add(new int[]{j,i});
                    else
                        if(starNeighbours.get(starNeighbours.size()-1)[1] == i)
                            starNeighbours.set(starNeighbours.size() - 1,new int[]{j,i});
                }
            }
            for(int j : findIndexes(data.get(i),true))
                starCoords.add(new int[]{j,i});

        }

        for(int[] stars : starCoords){
            int val = 1, counter = 0;
            for(int[] neighbours : starNeighbours){
                if(stars[0] >= neighbours[0] - 1 && stars[0] <= neighbours[0] + 1
                && stars[1] >= neighbours[1] - 1 && stars[1] <= neighbours[1] + 1) {
                    val *= convertToInteger(data.get(neighbours[1]), neighbours[0]);
                    counter++;
                }
            }
            if(counter == 2)
                sum+=val;

        }


        return sum;
    }

}
