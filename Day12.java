import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
            int maxGroupNumber = 0;

            for (int row = 0; row < onlyLetters.toArray().length; row++){
                for (int letter = 0; letter < onlyLetters.get(row).toArray().length; letter++){
                    if (onlyLetters.get(row).get(letter).equals(l + "")){
                        denoteGroup(onlyLetters, l, row, letter, maxGroupNumber);
                        maxGroupNumber++;
                    }
                }
            }



            for (int groupNumber = 0; groupNumber <= maxGroupNumber; groupNumber++){
                int y = 0;
                for (ArrayList<String> i : onlyLetters){
                    int x = 0;
                    for (String letter : i){
                        if (letter.equals(l + "" + groupNumber)) {
                            area++;
                            perimeter += getLetterPerimeter(onlyLetters, x, y);
                        }
                        x++;
                    }
                    y++;
                }
                amount += area * perimeter;
                area = 0;
                perimeter = 0;
            }
        }

        System.out.println("Part 1: " + amount);

    }

    private static int getLetterPerimeter(ArrayList<ArrayList<String>> onlyLetters, int x, int y) {
        String letterGroupSearch = onlyLetters.get(y).get(x);
        int perimeterAdd = 0;
        if (x - 1 < 0 || !onlyLetters.get(y).get(x - 1).equals(letterGroupSearch)){
            perimeterAdd++;
        }
        if (x + 1 > onlyLetters.get(y).toArray().length - 1 || !onlyLetters.get(y).get(x + 1).equals(letterGroupSearch)){
            perimeterAdd++;
        }
        if (y - 1 < 0 || !onlyLetters.get(y - 1).get(x).equals(letterGroupSearch)){
            perimeterAdd++;
        }
        if (y + 1 > onlyLetters.toArray().length - 1 || !onlyLetters.get(y + 1).get(x).equals(letterGroupSearch)){
            perimeterAdd++;
        }
        return perimeterAdd;
    }

    private static void denoteGroup(ArrayList<ArrayList<String>> onlyLetters, char l, int row, int letter, int groupNumber) {
        boolean replace = true;
        onlyLetters.get(row).set(letter, l + "" + groupNumber);
        while(replace){
            replace = false;
            for (int y = 0; y < onlyLetters.toArray().length; y++){
                for (int x = 0; x < onlyLetters.get(y).toArray().length; x++){
                    if (onlyLetters.get(y).get(x).equals(l + "") && isNextToGroup(onlyLetters, x, y, groupNumber)){
                        onlyLetters.get(y).set(x, l + "" + groupNumber);
                        replace = true;
                    }
                }
            }
        }
    }

    private static boolean isNextToGroup(ArrayList<ArrayList<String>> onlyLetters, int x, int y, int groupNumber) {
        if (x - 1 >= 0 && onlyLetters.get(y).get(x - 1).contains(groupNumber + "")){
            return true;
        }
        else if (x + 1 <= onlyLetters.get(y).toArray().length - 1 && onlyLetters.get(y).get(x + 1).contains(groupNumber + "")){
            return true;
        }
        else if (y - 1 >= 0 && onlyLetters.get(y - 1).get(x).contains(groupNumber + "")){
            return true;
        }
        else if (y + 1 <= onlyLetters.toArray().length - 1 && onlyLetters.get(y + 1).get(x).contains(groupNumber + "")){
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