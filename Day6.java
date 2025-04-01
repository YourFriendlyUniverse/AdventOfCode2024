import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        int[] currCoords = new int[]{0, 0};
        int direction = 1;
        // 1 is up, 2 is right, 3 is down, and 4 is left

        char[][] map = new char[fileData.size()][fileData.getFirst().length()];

        for (int i = 0; i < fileData.size(); i++){
            if (fileData.get(i).contains("^")){
                currCoords[0] = i;
                currCoords[1] = fileData.get(i).indexOf("^");
            }
            for (int j = 0; j < fileData.getFirst().length(); j++){
                map[i][j] = fileData.get(i).charAt(j);
            }
        }

        int[] startingPos = new int[]{currCoords[0], currCoords[1]};
        char[][] origMap = refreshMap(map);
        boolean inBounds = true;

        while(inBounds){
            map[currCoords[0]][currCoords[1]] = 'X';

            direction = checkObstruction(currCoords, direction, map);
            currCoords = move(currCoords, direction);
            inBounds = boundsCheck(currCoords, map);

        }
        map[currCoords[0]][currCoords[1]] = 'X';
        int spaces = 0;
        ArrayList<Integer[]> potentialObstructions = new ArrayList<Integer[]>();

        for (int row = 0; row < map.length; row++){
            for (int col = 0; col < map[row].length; col++){
                if (map[row][col] == 'X'){
                    spaces++;
                    potentialObstructions.add(new Integer[]{row, col});
                }
//                System.out.print(tile);
            }
//            System.out.println();
        }

        System.out.println("Part 1: " + spaces);

        int loops = 0;

        for (Integer[] obstruction : potentialObstructions){
            map = refreshMap(origMap);
            map[obstruction[0]][obstruction[1]] = '#';

            boolean inLoop = false;
            inBounds = true;
            currCoords = new int[]{startingPos[0], startingPos[1]};
            direction = 1;

            while (inBounds && !inLoop){
                if (map[currCoords[0]][currCoords[1]] == '1'){
                    map[currCoords[0]][currCoords[1]] = '2';
                }
                else if (map[currCoords[0]][currCoords[1]] == '2'){
                    map[currCoords[0]][currCoords[1]] = '3';
                }
                else if(map[currCoords[0]][currCoords[1]] == '3'){
                    inLoop = true;
                    loops++;
                }
                else{
                    map[currCoords[0]][currCoords[1]] = '1';
                }

                if (!inLoop){
                    direction = checkObstruction(currCoords, direction, map);
                    currCoords = move(currCoords, direction);
                    inBounds = boundsCheck(currCoords, map);
                }
            }

        }

        System.out.println("Part 2: " + loops);

    }

    private static char[][] refreshMap (char[][] m){
        char[][] newMap = new char[m.length][m[0].length];
        for (int r = 0; r < m.length; r++){
            for (int c = 0; c < m[0].length; c++){
                newMap[r][c] = m[r][c];
            }
        }
        return newMap;
    }

    private static boolean boundsCheck(int[] cords, char[][] m) {
        if (cords[0] - 1 < 0){
            return false;
        }
        else if (cords[1] + 1 >= m[0].length){
            return false;
        }
        else if (cords[0] + 1 >= m.length){
            return false;
        }
        else if (cords[1] - 1 < 0){
            return false;
        }
        else {
            return true;
        }
    }

    private static int checkObstruction(int[] cords, int d, char[][] m) {
        if (d == 1 && m[cords[0] - 1][cords[1]] == '#'){
            d++;
        }
        else if (d == 2 && m[cords[0]][cords[1] + 1] == '#'){
            d++;
        }
        else if (d == 3 && m[cords[0] + 1][cords[1]] == '#'){
            d++;
        }
        else if (d == 4 && m[cords[0]][cords[1] - 1] == '#'){
            d = 1;
        }
        return d;
    }

    public static int[] move(int[] cords, int d){
        switch (d){
            case (1) -> cords[0]--;
            case (2) -> cords[1]++;
            case (3) -> cords[0]++;
            case (4) -> cords[1]--;
        }
        return new int[]{cords[0], cords[1]};
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