import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) {
        int totalCorrect = 0;

        ArrayList<String> fileData = getFileData("src/InputFile");
        for (String l : fileData){
            int[] nums = getNums(l);
            int total = getTotal(l);
            int currTotal = nums[0];

            for (int i = 1; i < nums.length; i++){
                if(currTotal * nums[i] > total){
                    currTotal += nums[i];
                }
                else{
                    currTotal *= nums[i];
                }
            }

            if (currTotal == total){
                System.out.println(total);
                totalCorrect += total;
            }
        }

        System.out.println("Part 1: " + totalCorrect);
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

    public static int getTotal(String line){
        return Integer.parseInt(line.substring(0, line.indexOf(":")));
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