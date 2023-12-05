public class SeedFertilizer {
    public static long lowestLocationNumber(String filename) throws IOException {

        List<Long> seedsList = new ArrayList<>();
        List<Long> convertedSeedsList = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String line = bufferedReader.readLine();
        line = line.substring(line.indexOf(':') + 1);
        for(String seed : line.trim().split(" ")){
            if((!seed.equals("")) && (!seed.equals(" "))){
                seedsList.add(Long.parseLong(seed));
            }
        }
        long destination = 0;
        long source = 0;
        long range = 0;
        int mapNumber = 0;
        while((line = bufferedReader.readLine()) != null){
            int i = 0;
            if(line.contains(":")){
                ++mapNumber;
                if(mapNumber > 1) {
                    convertedSeedsList.addAll(seedsList);
                    seedsList = convertedSeedsList;
                    convertedSeedsList = new ArrayList<>();
                }
            }
            if((!line.equals("")) && Character.isDigit(line.charAt(0))){
                for(String map_numbers : line.trim().split(" ")) {
                    if((!map_numbers.equals("")) && (!map_numbers.equals(" "))) {
                        switch (i) {
                            case 0 -> destination = Long.parseLong(map_numbers);
                            case 1 -> source = Long.parseLong(map_numbers);
                            case 2 -> range = Long.parseLong(map_numbers);
                        }
                        ++i;
                    }
                }
                for(Iterator<Long> iterator = seedsList.iterator(); iterator.hasNext();){
                    Long seed = iterator.next();
                    if(seed >= source && seed < source + range){
                        seed += destination - source;
                        convertedSeedsList.add(seed);
                        iterator.remove();
                    }
                }
            }
        }
        convertedSeedsList.addAll(seedsList);
        seedsList = convertedSeedsList;
        return Collections.min(seedsList);
    }
  
    public static void main(String[] args) throws IOException {
        System.out.println(lowestLocationNumber("you_input_file_path"));
    }
}
