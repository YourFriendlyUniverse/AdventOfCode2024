import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day02 {
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
            else{
                for (int j = 0; j < level.length; j++){
                    safe = true;
                    int[] newLevel = new int[level.length - 1];
                    int index = 0;
                    // makes new int array with one number removed
                    for (int k = 0; k < level.length; k++){
                        if (k != j){
                            newLevel[index] = level[k];
                            index++;
                        }
                    }
                    for (int k = 1; k < newLevel.length; k++){
                        // checks to see if the level is still not safe
                        if (increasing){
                            if (newLevel[k - 1] + 1 > newLevel[k] || newLevel[k - 1] + 3 < newLevel[k]){
                                safe = false;
                            }
                        }
                        else if (newLevel[k - 1] - 1 < newLevel[k] || newLevel[k - 1] - 3 > newLevel[k]){
                            safe = false;
                        }
                    }
                    if (safe){
                        dampenedSafeReports++;
                        break;
                    }
                }
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