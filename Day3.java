import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/InputFile");
        ArrayList<String> allMatches = new ArrayList<String>();
        ArrayList<String> allEnabledMatches = new ArrayList<String>();

        Pattern p = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Pattern enabledP = Pattern.compile("(mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\))");
        int totalResult = 0;
        int enabledResult = 0;


        for (int i = 0; i < fileData.toArray().length; i++){
            Matcher m = p.matcher(fileData.get(i));
            Matcher enabledM = enabledP.matcher(fileData.get(i));
            while (m.find()) {
                allMatches.add(m.group());
            }

            while (enabledM.find()){
                allEnabledMatches.add(enabledM.group());
            }

            for (int j = 0; j < allMatches.toArray().length; j++){
                String mult = allMatches.get(j);
                int num1 = Integer.parseInt(mult.substring(mult.indexOf("(") + 1, mult.indexOf(",")));
                int num2 = Integer.parseInt(mult.substring(mult.indexOf(",") + 1, mult.indexOf(")")));
                totalResult += num1 * num2;
            }

            allMatches.clear();

        }

        System.out.println(totalResult);

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