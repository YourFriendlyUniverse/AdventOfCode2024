import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day05 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> pageNumbers = new ArrayList<>();
        ArrayList<String> incorrectLists = new ArrayList<>();

        int partOneAnswer = 0;

        // separates input into rules and the numbers
        for (String i : fileData){
            if (i.contains("|")){
                rules.add(i);
            }
            else{
                pageNumbers.add(i);
            }
        }

        for (String i : pageNumbers){
            boolean isValid = true;
            String[] nums = i.split(",");
            ArrayList<String> previousNums = new ArrayList<>();

            // loops through every number
            for (int j = 0; j < nums.length; j++){
                // looks at all rules with this number
                for (String rule : rules){
                    // looks for the number's dependency/dependencies and if it follows the rules
                    if (rule.contains("|" + nums[j])){
                        String previousNumberNeeded = rule.substring(0, rule.indexOf("|"));
                        boolean hasPrevNum = false;
                        // checks if the rule(s) hold up
                        for (String prevNums : previousNums){
                            if (prevNums.equals(previousNumberNeeded)) {
                                hasPrevNum = true;
                                break;
                            }
                        }
                        if (!hasPrevNum && ruleApplies(rule, nums)){
                            isValid = false;
                        }
                    }
                }
                previousNums.add(nums[j]);
            }

            if (isValid){
                partOneAnswer += Integer.parseInt(nums[nums.length / 2]);
            }
            else{
                incorrectLists.add(i);
            }
        }

        System.out.println("Part 1: " + partOneAnswer);

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

    // checks if the rule applies
    public static boolean ruleApplies(String rule, String[] nums){
        boolean hasFirstNum = false;
        boolean hasSecondNum = false;

        String firstNum = rule.substring(0, rule.indexOf("|"));
        String secondNum = rule.substring(rule.indexOf("|") + 1);

        for (String num : nums){
            if (num.equals(firstNum)){
                hasFirstNum = true;
            }
            else if (num.equals(secondNum)){
                hasSecondNum = true;
            }
        }

        return hasFirstNum && hasSecondNum;
    }

}