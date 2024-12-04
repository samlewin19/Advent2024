import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day3 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Day.txt");
        ArrayList<String> allMatches = new ArrayList<String>();
        String megastr = "";
        for (int i = 0; i < fileData.size(); i++) {

            megastr += fileData.get(i);
        }
        String regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)";
        Matcher m = Pattern.compile(regex).matcher(megastr);
        while (m.find()) {
            allMatches.add(m.group());
        }

        for (int i =0; i < allMatches.size(); i++){
            String temp = allMatches.get(i).substring(4, allMatches.get(i).length()-1);
            //split by comma, multiplu, add

        }
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