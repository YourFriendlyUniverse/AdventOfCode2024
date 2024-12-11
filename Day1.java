import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        int[] firstList = new int[fileData.toArray().length];
        int[] secondList = new int[fileData.toArray().length];

        for (int i = 0; i < fileData.toArray().length; i++){
            firstList[i] = Integer.parseInt(fileData.get(i).substring(0, fileData.get(i).indexOf(" ")));
            secondList[i] = Integer.parseInt(fileData.get(i).substring(fileData.get(i).indexOf(" ") + 3));
        }

        boolean didSort = true;

        while (didSort){
            didSort = false;

            for (int i = 1; i < firstList.length; i++){
                if (firstList[i] < firstList[i - 1]){
                    int temp = firstList[i];
                    firstList[i] = firstList[i - 1];
                    firstList[i - 1] = temp;
                    didSort = true;
                }
            }
            for (int i = 1; i < secondList.length; i++){
                if (secondList[i] < secondList[i - 1]){
                    int temp = secondList[i];
                    secondList[i] = secondList[i - 1];
                    secondList[i - 1] = temp;
                    didSort = true;
                }
            }
        }

        int distancesSum = 0;

        for (int i = 0; i < firstList.length; i++){
            distancesSum += Math.abs(firstList[i] - secondList[i]);
        }

        int similarityScore = 0;

        for (int i = 0; i < firstList.length; i++){
            int timesAppeared = 0;
            for (int j = 0; j < secondList.length; j++){
                if (firstList[i] == secondList[j]){
                    timesAppeared++;
                }
            }
            similarityScore += timesAppeared * firstList[i];
        }

        System.out.println("Part 1: " + distancesSum);
        System.out.println("Part 2: " + similarityScore);

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