import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GearRatios2 {
    public static List<Point> positions =  new ArrayList<>();
  
    public static int gearRatios2(String filename) throws IOException {
        int sum = 0;
        int row_number = 0;
        int col_number = 0;
        int gearNumber = 0;
        var path = Path.of(filename);
        List<Integer> gearNumbers = new ArrayList<>();

        String firstLine = Files.readAllLines(path).get(0);
        char[] c = firstLine.toCharArray();
        col_number = c.length;
        for(String lines : Files.readAllLines(path)) {
            ++row_number;
        }

        char [][] engineMap = new char[row_number][col_number];

        int j = 0;
        for(String line : Files.readAllLines(path)) {
            char[] chars = line.toCharArray();
            engineMap[j] = chars;
            ++j;
        }

        for(int row = 0; row<row_number;++row){
            for(int col = 0; col<col_number;++col){
                System.out.print(engineMap[row][col] + " ");
              
                if(engineMap[row][col] == '*'){                
                    for(int k = 0; k < 3; ++k){
                        boolean NumberNotUsed = true;
                        try{ //top numbers
                            if(Character.isDigit(engineMap[row - 1][col - 1 + k])){
                                for(Point field : positions){
                                    if (field.x == (row - 1) && field.y == (col - 1 + k)) {
                                        NumberNotUsed = false;
                                        break;
                                    }
                                }
                                if(NumberNotUsed) {
                                    gearNumber = whichNumber(col - 1 + k, row - 1, engineMap, col_number, row_number);
                                    gearNumbers.add(gearNumber);
                                }
                            }
                        } catch (IndexOutOfBoundsException ignored){
                        }

                        NumberNotUsed = true;
                        try{ //bottom numbers
                            if(Character.isDigit(engineMap[row + 1][col - 1 + k])){
                                for(Point field : positions){
                                    if (field.x == (row + 1) && field.y == (col - 1 + k)) {
                                        NumberNotUsed = false;
                                        break;
                                    }
                                }
                                if(NumberNotUsed) {
                                    gearNumber = whichNumber(col - 1 + k, row + 1, engineMap, col_number, row_number);
                                    gearNumbers.add(gearNumber);
                                }
                            }
                        } catch (IndexOutOfBoundsException ignored){
                        }
                    }

                    boolean NumberNotUsed = true;
                    try{ //left number
                        if(Character.isDigit(engineMap[row][col - 1])){
                            for(Point field : positions){
                                if (field.x == (row) && field.y == (col - 1)) {
                                    NumberNotUsed = false;
                                    break;
                                }
                            }
                            if(NumberNotUsed) {
                                gearNumber = whichNumber(col - 1, row, engineMap, col_number, row_number);
                                gearNumbers.add(gearNumber);
                            }
                        }
                    } catch (IndexOutOfBoundsException ignored){
                    }

                    NumberNotUsed = true;
                    try{ //right number
                        if(Character.isDigit(engineMap[row][col + 1])){
                            for(Point field : positions){
                                if (field.x == (row) && field.y == (col + 1)) {
                                    NumberNotUsed = false;
                                    break;
                                }
                            }
                            if(NumberNotUsed) {
                                gearNumber = whichNumber(col + 1, row, engineMap, col_number, row_number);
                                gearNumbers.add(gearNumber);
                            }
                        }
                    } catch (IndexOutOfBoundsException ignored){
                    }

                    if(gearNumbers.size()==2){
                        sum += (gearNumbers.get(0) * gearNumbers.get(1));
                    }
                    gearNumbers = new ArrayList<>();
                    positions = new ArrayList<>();
                }
            }
            System.out.println();
        }

        return sum;
    }
  
    public static int whichNumber(int col, int row, char [][] map, int col_number, int row_number) {
        int begin = col;
        String number = "";
        if(col == 0) {
            for(int i = 0; i<col_number && Character.isDigit(map[row][col + i]); ++i) {
                positions.add(new Point(row, col + i));
                number = number.concat(String.valueOf(map[row][col + i]));
            }
        } else {
            for (int i = 0; col - i >= 0 && Character.isDigit(map[row][col - i]); ++i) {
                begin = col - i;
            }
            for(int i = 0; (begin + i)<col_number && Character.isDigit(map[row][begin + i]);++i) {
                positions.add(new Point(row, begin + i));
                number = number.concat(String.valueOf(map[row][begin + i]));
            }
        }
        return Integer.parseInt(number);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(gearRatios2("your_input_file.txt_path"));
    }
}
