import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");

        ArrayList<String> horizontalWords = fileData;
        ArrayList<String> verticalWords = new ArrayList<>();
        ArrayList<String> diagonalWords = new ArrayList<>();

        for (int column = 0; column < horizontalWords.getFirst().length(); column++){
            String vertical = "";
            for (int i = 0; i < horizontalWords.toArray().length; i++){
                vertical += horizontalWords.get(i).charAt(column);
            }
            verticalWords.add(vertical);
        }

        for (int i = 0; i < (horizontalWords.toArray().length - 1)*(verticalWords.toArray().length - 1) + 1; i++){
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