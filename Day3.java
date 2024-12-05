import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
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
//        //System.out.println(megastr);
//        String d = "do()";
//        String dn = "don't()";
//        for (int i = 0; i < megastr.length()-7; i++){
//            Matcher m = Pattern.compile(dn).matcher(megastr.substring(i,i+7));
////            System.out.print(m.find());
////            System.out.println(i);
//            if (m.find()){
//                for (int j = i+7; j < megastr.length()-4; j++){
//                    Matcher w = Pattern.compile(d).matcher(megastr.substring(j,j+4));
////                    System.out.print(j);
////                    System.out.println(w.find());
//                    if (w.find()){
//                        String s = megastr.substring(0, i) + megastr.substring(j);
//                        megastr =s;
//                    }
//                }
//            }
//        }
        String s = "";
        boolean b = true;
        for(int i = 0; i < megastr.length()-7; i++) {
            if (megastr.substring(i,i+7).equals("don't()")) {
                //System.out.println("found dont " + i);
                b = false;
            }
            if (megastr.substring(i,i+4).equals("do()")) {
                //System.out.println("found do " + i);
                b = true;
            }
            if (b) s+=megastr.substring(i, i+1);
//                for (int j = i+6; j< megastr.length()-4; j++){
//                    if (megastr.substring(j,j+4).equals("do()")) {
//                        System.out.println(j);
//                        i = j + 3;
//                        j = 99999999;
//                    }
//                }
//                s+=megastr.substring(i,i+1);

        }
        megastr = s + megastr.substring(megastr.length()-7);
        System.out.println(megastr);
        //System.out.println(megastr);
        String regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)";
        Matcher m = Pattern.compile(regex).matcher(megastr);
        while (m.find()) {
            allMatches.add(m.group());
        }
        //System.out.println(allMatches);
        int count = 0;
        for (int i =0; i < allMatches.size(); i++){
            String temp = allMatches.get(i).substring(4, allMatches.get(i).length()-1);
            //System.out.println(temp);
            //split by comma, multiplu, add
            String[] str = temp.split(",");
            int i1 = Integer.valueOf(str[0]);
            int i2 = Integer.valueOf(str[1]);
            int t = i1 * i2;
            count+=t;
        }
        System.out.println(count);
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
