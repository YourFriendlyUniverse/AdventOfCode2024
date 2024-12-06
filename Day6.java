import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");

        int currentX = 0;
        int currentY = 0;
        int direction = 1;
        // 1 is up, 2 is right, 3 is down, and 4 is left

        for (int i = 0; i < fileData.toArray().length; i++){

            if (fileData.get(i).contains("^")){
                currentY = i;
                currentX = fileData.get(i).indexOf("^");
            }
        }

        boolean inBounds = true;

        while(inBounds){

            String row = fileData.get(currentY);
            fileData.set(currentY, row.substring(0, currentX) + "X" + row.substring(currentX + 1));
            switch (direction){

                case 1 -> {
                    if (currentY - 1 < 0){
                        inBounds = false;
                    }
                    else if (fileData.get(currentY - 1).substring(currentX, currentX + 1).equals("#")) {
                        direction++;
                    }
                    else{
                        currentY--;
                    }

                }
                case 2 -> {
                    if (currentX + 1 > row.length() - 1){
                        inBounds = false;
                    }
                    else if (fileData.get(currentY).substring(currentX + 1, currentX + 2).equals("#")) {
                        direction++;
                    }
                    else{
                        currentX++;
                    }
                }
                case 3 -> {
                    if (currentY + 1 > fileData.toArray().length - 1){
                        inBounds = false;
                    }
                    else if (fileData.get(currentY + 1).substring(currentX, currentX + 1).equals("#")) {
                        direction++;
                    }
                    else{
                        currentY++;
                    }
                }
                case 4 -> {
                    if (currentX - 1 < 0){
                        inBounds = false;
                    }
                    else if (fileData.get(currentY).substring(currentX - 1, currentX).equals("#")) {
                        direction = 1;
                    }
                    else{
                        currentX--;
                    }
                }
            }

//            for (int i = 0; i < fileData.toArray().length; i++){
//                System.out.println(fileData.get(i));
//            }
//
//            System.out.println("---------------------------");

        }

        int spaces = 0;

        for (int i = 0; i < fileData.toArray().length; i++){
            for (int j = 0; j < fileData.get(i).length(); j++){
                if (fileData.get(i).charAt(j) == 'X'){
                    spaces++;
                }
            }
        }

        System.out.println(spaces);

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