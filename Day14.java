import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day14 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        int width = 101;
        int height = 103;

        for (String i : fileData){
            String[] posAndVelocity = i.split(" ");
            int x = Integer.parseInt(posAndVelocity[0].substring(2,posAndVelocity[0].indexOf(",")));
            int y = Integer.parseInt(posAndVelocity[0].substring(posAndVelocity[0].indexOf(",") + 1));
            int xVelocity;
            int yVelocity;
            if (posAndVelocity[1].substring(2,posAndVelocity[1].indexOf(",")).contains("-")){
                xVelocity = Integer.parseInt(posAndVelocity[1].substring(3, posAndVelocity[1].indexOf(","))) * -1;
            }
            else{
                xVelocity = Integer.parseInt(posAndVelocity[1].substring(2, posAndVelocity[1].indexOf(",")));
            }
            if (posAndVelocity[1].substring(posAndVelocity[1].indexOf(",") + 1).contains("-")){
                yVelocity = Integer.parseInt(posAndVelocity[1].substring(posAndVelocity[1].indexOf(",") + 2)) * -1;
            }
            else{
                yVelocity = Integer.parseInt(posAndVelocity[1].substring(posAndVelocity[1].indexOf(",") + 1));
            }

            int endingX = Math.abs((x + (xVelocity * 100)) % width);
            int endingY = Math.abs((y + (yVelocity * 100)) % height);

            System.out.println(endingX + "," + endingY);
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