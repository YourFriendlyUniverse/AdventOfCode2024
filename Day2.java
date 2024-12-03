import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");

        int safeReports = 0;
        int dampenedSafeReports = 0;

        for (int i = 0; i < fileData.toArray().length; i++){
            String[] numbers = fileData.get(i).split(" ");
            int[] level = new int[numbers.length];

            // conversion into an array of integers
            for (int j = 0; j < numbers.length; j++){
                level[j] = Integer.parseInt(numbers[j]);
            }

            boolean safe = true;
            boolean increasing = level[0] < level[level.length - 1];

            for (int j = 1; j < level.length; j++){
                // checks if the level is safe by checking if the next number is within 1-3 above or below the previous number depending on if its increasing or decreasing
                if (increasing){
                    if (level[j - 1] + 1 > level[j] || level[j - 1] + 3 < level[j]){
                        safe = false;
                    }
                }
                else{
                    if (level[j - 1] - 1 < level[j] || level[j - 1] - 3 > level[j]){
                        safe = false;
                    }
                }
            }
            if (safe){
                safeReports++;
                dampenedSafeReports++;
            }
        }

        System.out.println("Part 1: " + safeReports);
        System.out.println("Part 2: " + dampenedSafeReports);

    }


    public static ArrayList<String> getFileData(String fileName) {
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
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}