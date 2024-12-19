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
        ArrayList<ArrayList<Integer>> loopPos = new ArrayList<>();
        for(int p = 0; p < fileData.size(); p++){
            for (int k = 0; k < fileData.get(p).length(); k++){
                if (p != positions.get(0).get(0) || k != positions.get(0).get(1)){
                    String temp = fileData.get(p);
                    fileData.set(p, temp.substring(0,k) + "#" + temp.substring(k+1));
                    if (loopCheck(positions, fileData)){
                        ArrayList<Integer> lops = new ArrayList<>();
                        lops.add(p);
                        lops.add(k);
                        loopPos.add(lops);
                    }
                }
            }

        }
        System.out.println("answer: " + loopPos.size());

    }
    public static boolean loopCheck(ArrayList<ArrayList<Integer>> positions, ArrayList<String> fileData){
        boolean b = true;
        int i = positions.get(0).get(0);
        int j = positions.get(0).get(1);
        int dir = positions.get(0).get(2);
        ArrayList<Integer> tempPos = new ArrayList<>();
        while (b) {
            //think i creatred infinite loop
            ArrayList<Integer> coords = new ArrayList<>();
            if (dir==0) {
                //System.out.println("upping @ " + positions.get(0));
                if (i == 0) b = false;
                else if (i > 0) {
                    if (fileData.get(i - 1).substring(j, j + 1).equals("#")) {
                        dir = 1;

                    } else {
                        //System.out.println("esle");
                        coords.add(i - 1);
                        coords.add(j);
                        coords.add(dir);
                        i--;
                    }
                }
            }
            else if (dir == 1) {
                //System.out.println("right");
                if (j == 129) b = false;
                else if (j < 129) {
                    if (fileData.get(i).substring(j + 1, j + 2).equals("#")) {
                        dir = 2;
                    } else {
                        coords.add(i);
                        coords.add(j + 1);
                        coords.add(dir);
                        j++;
                    }
                }
            }
            else if (dir == 2) {
                if (i == fileData.size() - 1) b = false;
                else if (i < fileData.size() - 1) {
                    if (fileData.get(i + 1).substring(j, j + 1).equals("#")) {
                        dir = 3;
                    } else {
                        coords.add(i + 1);
                        coords.add(j);
                        coords.add(dir);
                        i++;
                    }
                }
            }
            else if (dir == 3) {
                if (j == 0) b = false;

                else if (j > 0) {
                    if (fileData.get(i).substring(j - 1, j).equals("#")) {
                        dir = 0;
                    } else {
                        coords.add(i);
                        coords.add(j - 1);
                        coords.add(dir);
                        j--;
                    }
                }
            }
            System.out.println(coords);
            //System.out.println(positions);

           for (int t = 0; t < positions.size(); t++) {
               if (coords.size() == 3) {
                   if (i == coords.get(0) && j == coords.get(1) && dir == coords.get(2)) return true;
               }
           }

        }
        return true;
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
