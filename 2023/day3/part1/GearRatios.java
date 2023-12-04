import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GearRatios {

    public static int gearRatios(String filename) throws IOException {
        int sum = 0;
        int row_number = 0;
        int col_number;
        int begin = 0;
        String number = "";
        var path = Path.of(filename);

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
        j = 0;

        for(int row = 0; row<row_number;++row){
            for(int col = 0; col<col_number;++col){
                System.out.print(engineMap[row][col] + " ");

                if(Character.isDigit(engineMap[row][col])){
                    if(number.equals("")) {
                        begin = col;
                    }
                    number = number.concat(String.valueOf(engineMap[row][col]));
                    continue;
                }

                if(number.equals("")) continue;
                if(isPartNumber(begin, row, number.length(), engineMap, col_number, row_number)){
                    sum += Integer.parseInt(number);
                }
                number = "";
                begin = 0;
            }

            if((!number.equals("")) && isPartNumber(begin, row, number.length(), engineMap, col_number, row_number)){
                sum += Integer.parseInt(number);
            }
            number = "";
            begin = 0;
            System.out.println();
        }
        return sum;
    }
  
    public static boolean isPartNumber(int col, int row, int length, char[][] map, int col_number, int row_number){

        for(int k = 0; k < 2 + length; ++k){
            try{ //top symbols
                if(!Character.isDigit(map[row - 1][col - 1 + k]) && (map[row - 1][col - 1 + k] != '.')){
                    return true;
                }
            } catch (IndexOutOfBoundsException ignored){}

            try{ //bottom symbols
                if(!Character.isDigit(map[row + 1][col - 1 + k]) && (map[row + 1][col - 1 + k] != '.')){
                    return true;
                }
            }catch (IndexOutOfBoundsException ignored){}
        }

        try{ //left symbols
            if(!Character.isDigit(map[row][col - 1]) && (map[row][col - 1] != '.')){
                return true;
            }
        }catch (IndexOutOfBoundsException ignored){}

        try{ //right symbols
            if(!Character.isDigit(map[row][col + length]) && (map[row][col + length] != '.')){
                return true;
            }
        }catch (IndexOutOfBoundsException ignored){}
         return false;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(gearRatios("your_input_file.txt_path"));
    }
}
