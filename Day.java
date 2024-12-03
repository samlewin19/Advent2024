import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Day {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Day.txt");
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
