import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day12 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int amount = 0;

        for (char l : alphabet){
            ArrayList<String> onlyLetters = new ArrayList<>();
            for (String row : fileData){
                String rowLetters = "";
                for (int letter = 0; letter < row.length(); letter++){
                    if (row.charAt(letter) == l){
                        rowLetters += l;
                    }
                    else{
                        rowLetters += ".";
                    }
                }
                onlyLetters.add(rowLetters);
            }

            HashMap<String, Integer> coords = new HashMap<>();
            int area = 0;

            for (int row = 0; row < onlyLetters.toArray().length; row++){
                for (int letter = 0; letter < onlyLetters.get(row).length(); letter++){
                    if (onlyLetters.get(row).charAt(letter) != '.'){
                        checkPerimeter(onlyLetters, coords, row, letter);
                        area++;
                    }
                }
            }

            int perimeter = 0;
            for (int i : coords.values()){
                perimeter += i;
            }

            System.out.println(coords);
            amount += area * perimeter;

        }

        System.out.println(amount);

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

    public static void checkPerimeter(ArrayList<String> letters, HashMap<String, Integer> coords, int row, int letter){
        if (row - 1 < 0 || letters.get(row - 1).charAt(letter) == '.'){
            coords.put((row - 1) + "," + letter, 1);
        }
        if (row + 1 > letters.toArray().length - 1 || letters.get(row + 1).charAt(letter) == '.'){
            coords.put((row + 1) + "," + letter, 1);
        }
        if (letter - 1 < 0 || letters.get(row).charAt(letter - 1) == '.'){
            coords.put((row) + "," + (letter - 1), 1);
        }
        if (letter + 1 > letters.getFirst().length() - 1 || letters.get(row).charAt(letter + 1) == '.'){
            coords.put(row + "," + (letter + 1), 1);
        }
    }

}