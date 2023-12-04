import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CubeConundrum {
    public static int howManyGames1(String filename) throws IOException {
        int sum = 0;
        int gameNumber = 0;
        for(String line : Files.readAllLines(Path.of(filename))){
            int red = 0;
            int green = 0;
            int blue = 0;
            ++gameNumber;
            boolean gameIsValid = true;
            line = line.substring(line.indexOf(':') + 1);

            for(String cubeSets : line.trim().split(";")){
                for(String singleExtraction : cubeSets.trim().split(",")){
                    int num = 0;
                    for(String word : singleExtraction.trim().split(" ")){
                        if(!word.equals("") && !word.equals(" ")) {
                            if (Character.isDigit(word.charAt(0))) {
                                num = Integer.parseInt(word);
                            } else {
                                switch (word) {
                                    case "red" -> {
                                        red += num;
                                        if(num > 12){
                                            gameIsValid = false;
                                        }
                                    }
                                    case "green" -> {
                                        green += num;
                                        if(num > 13){
                                            gameIsValid = false;
                                        }
                                    }
                                    case "blue" -> {
                                        blue += num;
                                        if(num > 14){
                                            gameIsValid = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(gameIsValid) {
                sum += gameNumber;
            }
        }
        return sum;
    }
    public static int howManyGames2(String filename) throws IOException {
        int sum = 0;

        for(String line : Files.readAllLines(Path.of(filename))){
            int red = 0;
            int green = 0;
            int blue = 0;
            line = line.substring(line.indexOf(':') + 1);

            for(String cubeSets : line.trim().split(";")){

                for(String singleExtraction : cubeSets.trim().split(",")){

                    int num = 0;
                    for(String word : singleExtraction.trim().split(" ")){
                        if(!word.equals("") && !word.equals(" ")) {
                            if (Character.isDigit(word.charAt(0))) {
                                num = Integer.parseInt(word);
                            } else {
                                switch (word) {
                                    case "red" -> {
                                        if(num > red) {
                                            red = num;
                                        }
                                    }
                                    case "green" -> {
                                        if(num > green) {
                                            green = num;
                                        }
                                    }
                                    case "blue" -> {
                                        if(num > blue) {
                                            blue = num;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            sum += (red*green*blue);
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(howManyGames1("your_input_file.txt_path"));
        System.out.println(howManyGames2("your_input_file.txt_path"));
    }
}
