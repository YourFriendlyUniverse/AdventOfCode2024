import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day12 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int amount = 0;

        for (char l : alphabet){
            ArrayList<ArrayList<String>> onlyLetters = new ArrayList<>();
            for (String row : fileData){
                ArrayList<String> rowLetters = new ArrayList<>();
                for (int letter = 0; letter < row.length(); letter++){
                    if (row.charAt(letter) == l){
                        rowLetters.add(l + "");
                    }
                    else{
                        rowLetters.add(".");
                    }
                }
                onlyLetters.add(rowLetters);
            }

            int area = 0;
            int perimeter = 0;
            int groupNumber = 0;

            for (int row = 0; row < onlyLetters.toArray().length; row++){
                for (int letter = 0; letter < onlyLetters.get(row).toArray().length; letter++){
                    if (onlyLetters.get(row).get(letter).equals(l + "")){
                        denoteGroup(onlyLetters, l, row, letter, groupNumber);
                        groupNumber++;
                    }
                }
            }

            System.out.println(onlyLetters);

            amount += area * perimeter;

        }

        System.out.println(amount);

    }

    private static void denoteGroup(ArrayList<ArrayList<String>> onlyLetters, char l, int row, int letter, int groupNumber) {
        System.out.println("G#: " + groupNumber);
        onlyLetters.get(row).set(letter, l + "" + groupNumber);

        boolean replace = true;
        while(replace){
            replace = false;
            for (int y = 0; y < onlyLetters.toArray().length; y++){
                for (int x = 0; x < onlyLetters.get(y).toArray().length; x++){
                    System.out.println(onlyLetters.get(y).get(x).equals(l + "") && isNextToGroup(onlyLetters, x, y, groupNumber));
                    if (onlyLetters.get(y).get(x).equals(l + "") && isNextToGroup(onlyLetters, x, y, groupNumber)){
                        onlyLetters.get(y).set(x, l + "" + groupNumber);
                        replace = true;
                    }
                }
            }
        }
    }

    private static boolean isNextToGroup(ArrayList<ArrayList<String>> onlyLetters, int x, int y, int groupNumber) {
//        System.out.println(onlyLetters.get(y).get(x - 1).contains(groupNumber + ""));
//        System.out.println(onlyLetters.get(y).get(x - 1));
//        System.out.println("GROUP NUM: " + groupNumber);
        if (x - 1 > 0 && onlyLetters.get(y).get(x - 1).contains(groupNumber + "")){
            System.out.println("REPLACED ");
            return true;
        }
        else if (x + 1 < onlyLetters.get(y).toArray().length - 1 && onlyLetters.get(y).get(x + 1).contains(groupNumber + "")){
            return true;
        }
        else if (y - 1 > 0 && onlyLetters.get(y - 1).get(x).contains(groupNumber + "")){
            return true;
        }
        else if (y + 1 < onlyLetters.toArray().length - 1 && onlyLetters.get(y + 1).get(x).contains(groupNumber + "")){
            return true;
        }
        else{
            return false;
        }
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