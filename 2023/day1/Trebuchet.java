import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Trebuchet {

    public static int SumFileDigit(String filename) throws IOException{
        Map<String, Integer> spelledDigits = new HashMap<>();
        spelledDigits.put("zero", 0);
        spelledDigits.put("one", 1);
        spelledDigits.put("two", 2);
        spelledDigits.put("three", 3);
        spelledDigits.put("four", 4);
        spelledDigits.put("five", 5);
        spelledDigits.put("six", 6);
        spelledDigits.put("seven", 7);
        spelledDigits.put("eight", 8);
        spelledDigits.put("nine", 9);

        int sum = 0;
        for(String line : Files.readAllLines(Path.of(filename))){
            String word = "";
            int firstDigit = -1;
            int secondDigit = -1;
            for(Character c : line.toCharArray()) {
                if (Character.isDigit(c)) {
                    if (firstDigit == -1) {
                        firstDigit = c - 48;
                    } else {
                        secondDigit = c - 48;
                    }
                } else { //just comment this component to have part 1
                    word = word.concat(c.toString().toLowerCase());
                    if(word.length() > 2) {
                        for (String keyDigit : spelledDigits.keySet()) {
                            if (word.contains(keyDigit)) {
                                if (firstDigit == -1) {
                                    firstDigit = spelledDigits.get(keyDigit);
                                } else {
                                    secondDigit = spelledDigits.get(keyDigit);
                                }
                                word = word.substring(word.length()-1);
                                break;
                            }
                        }
                    }
                }
            }

            if((firstDigit != -1) && (secondDigit != -1)){
                sum += Integer.parseInt(String.valueOf(firstDigit).concat(String.valueOf(secondDigit)));
            } else if(firstDigit != -1){
                sum += Integer.parseInt(String.valueOf(firstDigit).concat(String.valueOf(firstDigit)));
            }
        }
        return sum;
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(SumFileDigit("your_input_file.txt_path"));
    }
}
