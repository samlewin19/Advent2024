
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Day.txt");
        System.out.println(fileData);
        int rows = fileData.size();
        int columns = fileData.get(0).length();
        String[][] grid = new String[rows][columns];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c] = fileData.get(r).substring(c, c + 1);
            }
        }
        int count = 0;
        String xmas = "XMAS";
        for(int i =0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++) {
                boolean f = false;
                boolean b = false;
                boolean u = false;
                boolean d = false;
                if (j < grid[i].length - 3) f = true;
                if (j > 3) b = true;
                if (i > 3) u = true;
                if (i < grid.length - 3) d = true;
                //now check for xmas
                if (grid[i][j].equals("X")) {
                    if (f) {
                        //string goes forward
                        String[] strs = grid[i];
                        String sf = strs[j] + strs[j + 1] + strs[j + 2] + strs[j + 3];
                        if (sf.equals(xmas)) count++;
                        if (u) {
                            //upward forward diagnol
                            String sfu= grid[i][j]+ grid[i-1][j+1]+ grid[i-2][j+2]+ grid[i-3][j+3];
                            if (sfu.equals(xmas)) count++;
                        }
                        if (d){
                            //downward forward diagnol
                            String sfd= grid[i][j]+ grid[i+1][j+1]+ grid[i+2][j+2]+ grid[i+3][j+3];
                            if (sfd.equals(xmas)) count++;
                        }
                    }
                    if (b) {
                        //backward string
                        String sb = grid[i][j] + grid[i][j-1] + grid[i][j-2] + grid[i][j-3];
                        if (sb.equals(xmas)) count++;
                        if (u){
                            //upward backward diagnol
                            String sbu= grid[i][j]+ grid[i-1][j-1]+ grid[i-2][j-2]+ grid[i-3][j-3];
                            if (sbu.equals(xmas)) count++;
                        }
                        if (d){
                            //downward backward diagnol
                            String sbd= grid[i][j]+ grid[i+1][j-1]+ grid[i+2][j-2]+ grid[i+3][j-3];
                            if (sbd.equals(xmas)) count++;
                        }

                    }
                    if (d){
                        String sd = grid[i][j] + grid[i+1][j] + grid[i+2][j] + grid[i+3][j];
                        if (sd.equals(xmas))count++;
                    }
                    if (u){
                        String su = grid[i][j] + grid[i-1][j] + grid[i-2][j] + grid[i-3][j];
                        if (su.equals(xmas))count++;
                    }

                }
            }
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