import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");

        ArrayList<String> horizontalWords = fileData;
        ArrayList<String> verticalWords = new ArrayList<>();
        ArrayList<String> diagonalLeftRightWords = new ArrayList<>(); // diagonals which go from bottom left to top right
        ArrayList<String> diagonalRightLeftWords = new ArrayList<>(); // diagonals which go from bottom right to top left

        Pattern p = Pattern.compile("(?=XMAS|SAMX)");

        for (int column = 0; column < horizontalWords.getFirst().length(); column++){
            String vertical = "";
            for (int i = 0; i < horizontalWords.toArray().length; i++){
                vertical += horizontalWords.get(i).charAt(column);
            }
            verticalWords.add(vertical);
        }

        for (int i = 0; i < (horizontalWords.toArray().length - 1) + (verticalWords.toArray().length - 1) + 1; i++){
            int row;
            int column;

            // checks if the total of the coordinates is greater than the list's length
            if (i > horizontalWords.toArray().length - 1){
                column = horizontalWords.toArray().length - 1;
                row = i - column;
            }
            else{
                column = i;
                row = 0;
            }

            int origRow = row;
            String diagonal = "";

            while (column != origRow - 1){
                diagonal += horizontalWords.get(column).charAt(row);

                column--;
                row++;
            }

            diagonalLeftRightWords.add(diagonal);
        }
        int maxRow = 0;

        for (int i = 0; i < (horizontalWords.toArray().length) + (verticalWords.toArray().length) - 1; i++){
            int row;
            int column;

            // checks if the total of the coordinates is greater than the list's length
            if (i >= horizontalWords.toArray().length - 1){
                column = 0;
                row = i - (horizontalWords.toArray().length - 1);
            }
            else{
                column = horizontalWords.toArray().length - i - 1;
                row = 0;
            }


            String diagonal = "";

            while (row <= maxRow){
                diagonal += horizontalWords.get(column).charAt(row);
                column++;
                row++;
            }

            diagonalRightLeftWords.add(diagonal);
            if (maxRow < horizontalWords.toArray().length - 1){
                maxRow++;
            }
        }


        System.out.println("Part 1: " + (matches(horizontalWords, p) + matches(verticalWords, p) + matches(diagonalLeftRightWords, p) + matches(diagonalRightLeftWords, p)));

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

    public static int matches(ArrayList<String> words, Pattern p){
        int amount = 0;
        for (String i : words){
            Matcher m = p.matcher(i);
            while (m.find()){
                amount++;
            }
        }

        return amount;
    }

}