import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        int[] currCoords = new int[]{0, 0};
        int direction = 1;
        // 1 is up, 2 is right, 3 is down, and 4 is left

        for (int i = 0; i < fileData.toArray().length; i++){
            if (fileData.get(i).contains("^")){
                currCoords[0] = i;
                currCoords[1] = fileData.get(i).indexOf("^");
            }
        }

        int[] startingPos = new int[]{currCoords[0], currCoords[1]};

        boolean inBounds = true;

        while(inBounds){

            String row = fileData.get(currCoords[0]);
            fileData.set(currCoords[0], row.substring(0, currCoords[1]) + "X" + row.substring(currCoords[1] + 1));

            int[] result = move(direction, currCoords, fileData);
            currCoords = new int[]{result[0], result[1]};
            direction = result[2];

            switch (direction){
                case 1 -> {
                    if (currCoords[0] - 1 < 0){
                        inBounds = false;
                    }
                }
                case 2 -> {
                    if (currCoords[1] + 1 > row.length() - 1){
                        inBounds = false;
                    }
                }
                case 3 -> {
                    if (currCoords[0] + 1 > fileData.toArray().length - 1) {
                        inBounds = false;
                    }
                }
                case 4 -> {
                    if (currCoords[1] - 1 < 0){
                        inBounds = false;
                    }
                }
            }
        }
        String row = fileData.get(currCoords[0]);
        fileData.set(currCoords[0], row.substring(0, currCoords[1]) + "X" + row.substring(currCoords[1] + 1));

        int spaces = 0;
        ArrayList<Integer[]> potentialObstructions = new ArrayList<Integer[]>();
        char[][] map = new char[fileData.size()][fileData.getFirst().length()];
        char[][] origMap = new char[fileData.size()][fileData.getFirst().length()];

        for (int r = 0; r < fileData.size(); r++){
            for (int c = 0; c < fileData.get(r).length(); c++){
                if (fileData.get(r).charAt(c) == 'X'){
                    spaces++;
                    potentialObstructions.add(new Integer[]{r, c});
                }
                map[r][c] = fileData.get(r).charAt(c);
                origMap[r][c] = fileData.get(r).charAt(c);
            }
        }

        System.out.println("Part 1: " + spaces);
        for (Integer[] cord : potentialObstructions){
            System.out.println(cord[0] +", ");
        }

        boolean notLooping = true;
        int successfulLoops = 0;
        currCoords = new int[]{startingPos[0], startingPos[1]};
        direction = 1;
        inBounds = true;
        for (Integer[] obstruction : potentialObstructions){
            map = refreshMap(origMap);
            map[obstruction[0]][obstruction[1]] = '#';

            while (notLooping && inBounds){
                if (map[currCoords[0]][currCoords[1]] == '1'){
                    map[currCoords[0]][currCoords[1]] = '2';
                }
                else if (map[currCoords[0]][currCoords[1]] == '3'){
                    notLooping = false;
                    successfulLoops++;
                }
                else {
                    map[currCoords[0]][currCoords[1]] = '1';
                }

                System.out.println(currCoords[0] + ", " + currCoords[1]);

                switch (direction){
                    case 1 -> {
                        if (currCoords[0] - 1 < 0){
                            inBounds = false;
                        }
                        else if (map[currCoords[0] - 1][currCoords[1]] == '#') {
                            direction++;
                        }
                        else{
                            currCoords[0]--;
                        }
                    }
                    case 2 -> {
                        if (currCoords[1] + 1 > map.length - 1){
                            inBounds = false;
                        }
                        else if (map[currCoords[0]][currCoords[1] + 1] == '#') {
                            direction++;
                        }
                        else{
                            currCoords[1]++;
                        }
                    }
                    case 3 -> {
                        if (currCoords[0] + 1 > map[0].length - 1) {
                            inBounds = false;
                        }
                        else if (map[currCoords[0] + 1][currCoords[1]] == '#') {
                            direction++;
                        }
                        else{
                            currCoords[0]++;
                        }
                    }
                    case 4 -> {
                        if (currCoords[1] - 1 < 0){
                            inBounds = false;
                        }
                        else if (map[currCoords[0]][currCoords[1] - 1] == '#') {
                            direction = 1;
                        }
                        else{
                            currCoords[1]--;
                        }
                    }
                }
            }
        }

        System.out.println("Part 2: " + successfulLoops);

    }

    public static char[][] refreshMap(char[][] m){
        char[][] newMap = new char[m.length][m[0].length];
        for (int r = 0; r < m.length; r++){
            for (int c = 0; c < m[0].length; c++){
                newMap[r][c] = m[r][c];
            }
        }
        return newMap;
    }

    public static int[] move(int d, int[] coords, ArrayList<String> fData){
        switch (d){

            case 1 -> {
                if (fData.get(coords[0] - 1).substring(coords[1], coords[1] + 1).equals("#")) {
                    d++;
                }
                else{
                    coords[0]--;
                }

            }
            case 2 -> {
                if (fData.get(coords[0]).substring(coords[1] + 1, coords[1] + 2).equals("#")) {
                    d++;
                }
                else{
                    coords[1]++;
                }
            }
            case 3 -> {
                if (fData.get(coords[0] + 1).substring(coords[1], coords[1] + 1).equals("#")) {
                    d++;
                }
                else{
                    coords[0]++;
                }
            }
            case 4 -> {
                if (fData.get(coords[0]).substring(coords[1] - 1, coords[1]).equals("#")) {
                    d = 1;
                }
                else{
                    coords[1]--;
                }
            }
        }
        return new int[]{coords[0], coords[1], d};
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