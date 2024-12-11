import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        ArrayList<Long> stones = new ArrayList<>();

        String[] stonesRow = fileData.getFirst().split(" ");

        for (String i : stonesRow){
            stones.add(Long.parseLong(i));
        }

        for (int i = 0; i < 75; i++) {
            // Anything faster than ArrayList?
            ArrayList<Long> newStones = new ArrayList<>();
            //Focus on this for loop here, code in here runs the most BY FAR
            for (int num = 0; num < stones.toArray().length; num++) {
                if (stones.get(num) == 0) {
                    newStones.add(1L);
                }
                else if (((int) Math.log10(stones.get(num)) + 1) % 2 == 0) {
                    //OPTIMIZE THIS PART (conversion to string, then back is not very healthy)
                    String number = stones.get(num) + "";
                    newStones.add(Long.parseLong(number.substring(0, (number.length() / 2))));
                    newStones.add(Long.parseLong(number.substring(number.length() / 2)));
                }
                else {
                    newStones.add(stones.get(num) * 2024);
                }
            }
            stones.clear();
            //Is there a way to optimize moving the lists?
            stones.addAll(newStones);
            System.out.println(i);
        }

        System.out.println("Solution: " + (stones.toArray().length));
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
