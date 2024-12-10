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
        ArrayList<Integer>[] prints = new ArrayList<Integer>[];
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