import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Day.txt");
        //  System.out.println(fileData);
        ArrayList<ArrayList<Integer>> positions = new ArrayList<>();
        ArrayList<ArrayList<Integer>> dupPos = new ArrayList<>();
        for (int i = 0; i < fileData.size(); i++) {
            int num = fileData.get(i).indexOf("^");
            // System.out.println(num);
            if (num > 0) {
                ArrayList<Integer> ints = new ArrayList<>();
                ints.add(i);
                ints.add(num);
                ints.add(0);
                positions.add(ints);
                dupPos.add(ints);
            }
        }
        // System.out.println(positions);
        boolean b = true;
        int i = positions.get(0).get(0);
        int j = positions.get(0).get(1);
        boolean up = true;
        boolean down = false;
        boolean right = false;
        boolean left = false;
        while (b) {
            //think i creatred infinite loop
            ArrayList<Integer> coords = new ArrayList<>();
            if (up) {
                //System.out.println("upping @ " + positions.get(0));
                if (i == 0) b = false;
                else if (i > 0) {
                    if (fileData.get(i - 1).substring(j, j + 1).equals("#")) {
                        up = false;
                        right = true;

                    } else {
                        //System.out.println("esle");
                        coords.add(i - 1);
                        coords.add(j);
                        i--;
                    }
                }
            } else if (right) {
                //System.out.println("right");
                if (j == 129) b = false;
                else if (j < 129) {
                    if (fileData.get(i).substring(j + 1, j + 2).equals("#")) {
                        right = false;
                        down = true;
                    } else {
                        coords.add(i);
                        coords.add(j + 1);
                        j++;
                    }
                }
            } else if (down) {
                if (i == fileData.size() - 1) b = false;
                else if (i < fileData.size() - 1) {
                    if (fileData.get(i + 1).substring(j, j + 1).equals("#")) {
                        down = false;
                        left = true;
                    } else {
                        coords.add(i + 1);
                        coords.add(j);
                        i++;
                    }
                }
            } else if (left) {
                if (j == 0) b = false;

                else if (j > 0) {
                    if (fileData.get(i).substring(j - 1, j).equals("#")) {
                        left = false;
                        up = true;
                    } else {
                        coords.add(i);
                        coords.add(j - 1);
                        j--;
                    }
                }
            }

            //System.out.println(positions);
            if (b && !check(coords, positions)){
                if(up) coords.add(0);
                else if (right) coords.add(1);
                else if (down) coords.add(2);
                else if (left) coords.add(3);
                positions.add(coords);
            }
            if (coords.size() == 2) dupPos.add(coords);
        }
        System.out.println(positions.size());
        System.out.println(positions);

        for(int j = 0; j < fileData.size(); j ++){
            for (int k = 0; k < fileData.get(j).length(); k++){
                if (j != positions.get(0).get(0) || k != positions.get(0).get(1)){
                    
                }
            }
        }
        
        
        
        
        
        
//        for (int p = 0; p < positions.size()-1;p++ ){
//            int row = positions.get(p).get(0);
//            int col = positions.get(p).get(1);
//            int dir = positions.get(p).get(2);
////            System.out.println(row);
////            System.out.println(col );
////            System.out.println(dir);
//            if (dir == 0){
//                 
//            }
//        }

    }

    public static boolean check(ArrayList<Integer> coords, ArrayList<ArrayList<Integer>> positions){
        //System.out.println(coords);
        //System.out.println(positions);
        if (coords.size() != 2) return true;

        for(int i = 0; i < positions.size();i++) {
            if (coords.get(0) == positions.get(i).get(0) && coords.get(1) == positions.get(i).get(1)){
                return true;
            }
        }

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
