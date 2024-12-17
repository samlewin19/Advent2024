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
            if (b && !check(coords, positions)) positions.add(coords);
            if (coords.size() == 2) dupPos.add(coords);
        }int dups = 0;
        //System.out.println(positions.size());
        //System.out.println(dupPos);
        for (int k = 1; k < positions.size(); k++) {
            ArrayList<Integer> tempAr = positions.get(k);
            String s = fileData.get(positions.get(k).get(0));
            String ss = s.substring(0, positions.get(k).get(1)) + "0" + s.substring(positions.get(k).get(1) + 1);
            fileData.set(positions.get(k).get(0), ss);
            //System.out.println(s);
            b = true;
             i = positions.get(0).get(0);
             j = positions.get(0).get(1);
             up = true;
             down = false;
             right = false;
             left = false;
             int count = 0;

             while (b) {
                 count++;
                //think i creatred infinite loop
                ArrayList coord = new ArrayList<>();
                if (up) {
                    //System.out.println("upping @ " + positions.get(0));
                    if (i == 0) b = false;
                    else if (i > 0) {
                        if (fileData.get(i - 1).substring(j, j + 1).equals("#")) {
                            up = false;
                            right = true;

                        } else {
                            //System.out.println("esle");
                            coord.add(i - 1);
                            coord.add(j);
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
                            coord.add(i);
                            coord.add(j + 1);
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
                            coord.add(i + 1);
                            coord.add(j);
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
                            coord.add(i);
                            coord.add(j - 1);
                            j--;
                        }
                    }
                }
                if (count > 999999){
                    b = false;
                    dups++;

                }
            }
            fileData.set(positions.get(k).get(0), s);
        }
        System.out.println(dups);
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
