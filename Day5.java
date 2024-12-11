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
            count+=midNum(validRules,prints.get(i));

        }
        System.out.println(count);

    }
    public static int midNum(ArrayList<ArrayList<Integer>> validRules, ArrayList<Integer> prints){
        int mid = prints.get(prints.size()/2);
        //System.out.println(mid);
        for(int i = 0; i < prints.size();i++){
            int num = prints.get(i);
            for (int j = 0; j < validRules.size(); j++){
                if(validRules.get(j).get(0) == num){
                    int secondNum = validRules.get(j).get(1);
                    for (int k = 0; k < prints.size(); k++){
                        if (prints.get(k) == num){

                        }
                    }
                }
            }
        }
        return mid;
    }
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
