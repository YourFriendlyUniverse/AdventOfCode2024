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
        int[] startingPos = new int[]{currCoords[0], currCoords[1]};
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

//        char[][] origMap = new char[fileData.size()][fileData.getFirst().length()];
        boolean inBounds = true;

        while(inBounds){
            map[currCoords[0]][currCoords[1]] = 'X';

            inBounds = boundsCheck(currCoords, direction, map);
            direction = checkObstruction(currCoords, direction, map);
            currCoords = move(currCoords, direction);


        }
        int spaces = 0;

        for (char[] row : map){
            for (char tile : row){
                if (tile == 'X'){
                    spaces++;
                }
                System.out.print(tile);
            }
            System.out.println();
        }

        System.out.println("Part 1: " + spaces);

        System.out.println("Part 2: ");

    }

    private static boolean boundsCheck(int[] cords, int d, char[][] m) {
        if (d == 1 && cords[0] - 1 < 0){
            return false;
        }
        else if (d == 2 && cords[1] + 1 >= m[0].length){
            return false;
        }
        else if (d == 3 && cords[0] + 1 >= m.length){
            return false;
        }
        else if (d == 4 && cords[1] - 1 < 0){
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