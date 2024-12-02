import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Day1.txt");

        ArrayList<Integer> intlist = new ArrayList<Integer>();
        ArrayList<Integer> intlist2 = new ArrayList<Integer>();
        for (int i = 0; i < fileData.size(); i++) {
            String[] strlist = fileData.get(i).split(" ");
            intlist.add(Integer.valueOf(strlist[0]));
            intlist2.add(i, Integer.valueOf(strlist[3]));
        }
        Collections.sort(intlist);
        Collections.sort(intlist2);
        System.out.println(intlist);
        System.out.println(intlist2);
        int difference = 0;
        for (int i =0; i < intlist2.size(); i++){
            difference+= Math.abs(intlist.get(i) - intlist2.get(i));
        }
        System.out.println(difference);

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
