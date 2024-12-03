import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Day.txt");
        int count = 0;
        for (int i = 0; i < fileData.size(); i++) {
            String[] strlist = fileData.get(i).split(" ");
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < strlist.length; j++){
                list.add(Integer.valueOf(strlist[j]));
            }
            if (incTest(list) || decTest(list)) count++;
            else{

                boolean b = false;
                for (int j = 0; j < list.size(); j++){
// cant just copy over list have to make loop to add values
                    ArrayList<Integer> tempList = list;
                    tempList.remove(j);
                    if (incTest(tempList) || decTest(tempList)) b = true;
                }
                if (b == true) count++;
            }

        }
        System.out.println(count);
    }
    public static boolean incTest(ArrayList<Integer> list){
        int num = 0;
        for(int j =1; j < list.size(); j++){
            if ((list.get(j-1) > list.get(j)) && Math.abs(list.get(j-1)- list.get(j)) <= 3) num++;
        }
        if (num == list.size()-1) return true;
        return false;
    }
    public static boolean decTest(ArrayList<Integer> list){
        int num = 0;
        for(int j =1; j < list.size(); j++){
            if ((list.get(j-1) < list.get(j)) && Math.abs(list.get(j-1)- list.get(j)) <= 3) num++;
        }
        if (num == list.size()-1) return true;
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