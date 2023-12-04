import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scratchcards2 {
    public static int calculatePlayedCards(String filename) throws IOException {
        final int NUMBER_OF_PLAYED_CARDS = 193;
        List<Integer> cards = new ArrayList<>(Collections.nCopies(NUMBER_OF_PLAYED_CARDS, 1));

        int playedCards = 0;
        int numberOfCurrentCard = 0;
        String winningNumbersLine;

        for(String line : Files.readAllLines(Path.of(filename))){
            ++numberOfCurrentCard;
            int matchingNumbers = 0;
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
                    ++matchingNumbers;
                }
            }
            for(int i = 0; i < matchingNumbers; ++i){
                cards.set(numberOfCurrentCard + i, cards.get(numberOfCurrentCard+i) + cards.get(numberOfCurrentCard-1));  //cards.get(numberOfCurrentCard-1) represents how many cards
            }                                                                                                             // I have of the card I'm currently playing
            playedCards +=  cards.get(numberOfCurrentCard - 1);
        }
        return playedCards;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(calculatePlayedCards("your_input_file.txt"));
    }
}
