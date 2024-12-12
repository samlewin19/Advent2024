import com.sun.jdi.ArrayReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Day.txt");
        //System.out.println(fileData.size());
        int ite = 0;

        while(fileData.get(ite).contains("|")){
            ite++;
        }
        int[][] rules = new int[ite][2];
        for(int i = 0; i < ite; i++){
            int val = fileData.get(i).indexOf("|");
            //System.out.print(val);
            rules[i][0]= Integer.valueOf(fileData.get(i).substring(0,val));
            rules[i][1]= Integer.valueOf(fileData.get(i).substring(val+1));
            //System.out.println(rules[i][0]);System.out.println(rules[i][1]);
        }
        //need to make a 2d array to hold all the prints and would like to use as manu assry lists as possible
        ArrayList<ArrayList<Integer>> prints = new ArrayList<>();
        for (int i = ite; i < fileData.size();i++){
            String[] strlist = fileData.get(i).split(",");
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int j = 0; j < strlist.length; j++){
                temp.add(Integer.valueOf(strlist[j]));
            }
            prints.add(temp);
        }
        //System.out.println(prints);
        int count = 0;

        for(int i = 0; i < prints.size();i++){
            ArrayList<ArrayList<Integer>> validRules = ruleCheck(rules, prints.get(i));
            //System.out.println(validRules);
            if(midNum(validRules,prints.get(i))==0){
                count+=fixNum(validRules,prints.get(i));
            }

        }
        System.out.println(count);

    }
    public static int midNum(ArrayList<ArrayList<Integer>> validRules, ArrayList<Integer> prints){
        int mid = prints.get(prints.size()/2);
        //int mid = 0;
        //System.out.println(mid);
        //System.out.println(validRules);
        for(int i = 0; i < prints.size();i++){
            //for each value being printed
            int num = prints.get(i);

            for (int j = 0; j < validRules.size(); j++){
                // for each rule in the valid rules
                //System.out.println("valid: "+validRules.get(j).get(0));
                //System.out.println("num: "+num);
                if(validRules.get(j).get(0) == num){
                  //  System.out.printf("=---");
                    //System.out.println();
                    int secondNum = validRules.get(j).get(1);
                 //   System.out.println("1: " + num);
                 //   System.out.println("2: " + secondNum);
                    int index1 = prints.indexOf(num);
                    int index2 = prints.indexOf(secondNum);
                    if(index2 < index1) mid = 0;
                }
            }
        }
        return mid;
    }

    public static int fixNum(ArrayList<ArrayList<Integer>> validRules, ArrayList<Integer> prints){
        // start by fixing array
        //then i need to rearrange
        int temp = midNum(validRules,prints);
        while(temp == 0) {
            Collections.shuffle(prints);
            temp = midNum(validRules,prints);

        }
        return temp;
    }




//    public static int fixMidNum(ArrayList<ArrayList<Integer>> validRules, ArrayList<Integer> prints){
//        int mid = prints.get(prints.size()/2)
//        for(int i = 0; i < prints.size();i++){
//            //for each value being printed
//            int num = prints.get(i);
//
//            for (int j = 0; j < validRules.size(); j++){
//                // for each rule in the valid rules
//                //System.out.println("valid: "+validRules.get(j).get(0));
//                //System.out.println("num: "+num);
//                if(validRules.get(j).get(0) == num){
//                    //  System.out.printf("=---");
//                    //System.out.println();
//                    int secondNum = validRules.get(j).get(1);
//                    //   System.out.println("1: " + num);
//                    //   System.out.println("2: " + secondNum);
//                    int index1 = prints.indexOf(num);
//                    int index2 = prints.indexOf(secondNum);
//                    if(index2 < index1) mid = 0;
//                }
//            }
//        }
//    }
    public static ArrayList ruleCheck(int[][] rules, ArrayList<Integer> prints){
        ArrayList<ArrayList<Integer>> validRules = new ArrayList<>();
        for(int i = 0; i < rules.length; i++){
            if (isIn(rules[i], prints)) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(rules[i][0]);
                temp.add((rules[i][1]));
                validRules.add(temp);
            }
        }
        return validRules;
    }

    public static boolean isIn(int[] nums, ArrayList<Integer> prints){
        int count = 0;
        for (int i = 0; i < prints.size();i++){
            if (prints.get(i) == nums[0]) count++;
            if (prints.get(i) == nums[1]) count++;
        }
        if (count >= 2) return true;
        return false;
    }



    public static ArrayList<String> getFileData (String fileName){
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        } catch (FileNotFoundException e) {
            return fileData;
        }
    }
}
//int middleIndex = list.length/2
//
//for each rule,
//count how many times a number shows up on the right
//which ever number shows up on the right the same number of times as "middleIndex" is the middle numbe72
//
//61,13,29
//
//        61|29
//        61|13
//        29|13
