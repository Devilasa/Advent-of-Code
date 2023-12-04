import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Scratchcards {

    public static int calculatePoints(String filename) throws IOException {
        int totalPoints = 0;
        String winningNumbersLine;
        for(String line : Files.readAllLines(Path.of(filename))){
            int cardPoints = 0;
            List<Integer> winningNumbers = new ArrayList<>();
            winningNumbersLine = line.substring(line.indexOf(':') + 1, line.lastIndexOf('|'));
            for(String number : winningNumbersLine.trim().split(" ")) {
                if(number.equals(""))continue;
                winningNumbers.add(Integer.parseInt(number));
            }
            line = line.substring(line.indexOf('|') + 1);
            for(String playedNumber : line.trim().split(" ")){
                if(playedNumber.equals(""))continue;
                if(winningNumbers.contains(Integer.parseInt(playedNumber))){
                    if(cardPoints == 0) {
                        cardPoints = 1;
                    } else {
                        cardPoints *= 2;
                    }
                }
            }
            totalPoints += cardPoints;
        }
         return totalPoints;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(calculatePoints("your_input_file.txt_path"));
    }
}
