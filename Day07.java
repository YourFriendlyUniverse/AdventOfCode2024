import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day07 {
    public static void main(String[] args) {
        long totalCorrect = 0;
        long totalCorrectPart2 = 0;

        ArrayList<String> fileData = getFileData("src/InputFile");
        for (String l : fileData){
            int[] nums = getNums(l);
            long total = getTotal(l);

            if (solve(nums[0], nums, 1, total)){
                totalCorrect += total;
            }
            else if (solvePart2(nums[0], nums, 1, total)){
                totalCorrectPart2 += total;
            }
        }

        totalCorrectPart2 += totalCorrect;
        System.out.println("\nPart 1: " + totalCorrect);
        System.out.println("Part 2: " + totalCorrectPart2);
    }

    public static boolean solve(long currTotal, int[] nums, int idx, long solution){
        if (idx == nums.length){
            if (currTotal == solution){
                return true;
            }
            else{
                return false;
            }
        }

        else{
            return(solve(currTotal + nums[idx], nums, idx + 1, solution) || solve(currTotal * nums[idx], nums, idx + 1, solution));
        }
    }

    public static boolean solvePart2(long currTotal, int[] nums, int idx, long solution){
        if (idx == nums.length){
            if (currTotal == solution){
                return true;
            }
            else{
                return false;
            }
        }

        else{
            long concatenation = Long.parseLong(currTotal + "" + nums[idx]);
            return(solvePart2(currTotal + nums[idx], nums, idx + 1, solution) || solvePart2(currTotal * nums[idx], nums, idx + 1, solution) || solvePart2(concatenation, nums, idx + 1, solution));
        }
    }

    public static int[] getNums(String line){
        String justNums = line.substring(line.indexOf(":") + 2);
        String[] justNumsArray = justNums.split(" ");
        int[] nums = new int[justNumsArray.length];
        for (int i = 0; i < justNumsArray.length; i++){
            nums[i] = Integer.parseInt(justNumsArray[i]);
        }

        return nums;
    }

    public static long getTotal(String line){
        return Long.parseLong(line.substring(0, line.indexOf(":")));
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