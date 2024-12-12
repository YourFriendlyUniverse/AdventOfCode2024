import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        ArrayList<Long> stones = new ArrayList<>();

        String[] stonesRow = fileData.getFirst().split(" ");

        for (String i : stonesRow){
            stones.add(Long.parseLong(i));
        }

        // part 1
//        for (int i = 0; i < 25; i++) {
//            ArrayList<Long> newStones = new ArrayList<>();
//            for (int num = 0; num < stones.toArray().length; num++) {
//                if (stones.get(num) == 0) {
//                    newStones.add(1L);
//                }
//                else if (((int) Math.log10(stones.get(num)) + 1) % 2 == 0) {
//                    String number = stones.get(num) + "";
//                    newStones.add(Long.parseLong(number.substring(0, (number.length() / 2))));
//                    newStones.add(Long.parseLong(number.substring(number.length() / 2)));
//                }
//                else {
//                    newStones.add(stones.get(num) * 2024);
//                }
//            }
//            stones.clear();
//            stones.addAll(newStones);
//            System.out.println(i);
//        }
//
//        System.out.println("Part 1: " + (stones.toArray().length));

        // the key will denote the number on the stone(s), and the value will be how many of those stones exist
        HashMap<Long, Long> numOfStones = new HashMap<>();

        // HashMap initialization of values
        for (Long i : stones){
            if (numOfStones.containsKey(i)){
                numOfStones.put(i, numOfStones.get(i) + 1);
            }
            numOfStones.put(i, 1L);
        }

        HashMap<Long, Long> newNumOfStones = new HashMap<>();

        for (int i = 0; i < 75; i++){
            numOfStones.replaceAll((k, v) -> {
                if (k == 0){
                    replaceStonesNumber(newNumOfStones, 1, v);
                }
                else if ((int) (Math.log10(k) + 1) % 2 == 0){
                    long num1 = (long) (k / Math.pow(10, (long) (Math.log10(k) + 1) / 2));
                    long num2 = (long) (k - (num1 * Math.pow(10, (long) (Math.log10(k) + 1) / 2)));
                    replaceStonesNumber(newNumOfStones, num1, v);
                    replaceStonesNumber(newNumOfStones, num2, v);
                }
                else{
                    replaceStonesNumber(newNumOfStones, k * 2024, v);
                }
                return v;
            });
            numOfStones = (HashMap<Long, Long>) newNumOfStones.clone();
            newNumOfStones.clear();
        }

        System.out.println("Part 2: " + countNumOfStones(numOfStones));

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

    public static void replaceStonesNumber(HashMap<Long, Long> stones, long num, long origAmount){
        if (stones.get(num) != null){
            stones.put(num, stones.get(num) + origAmount);
        }
        else{
            stones.put(num, origAmount);
        }
    }

    public static long countNumOfStones(HashMap<Long, Long> stones){
        long amountOfStones = 0;
        for (long i : stones.values()){
            amountOfStones += i;
        }
        return amountOfStones;
    }

}
