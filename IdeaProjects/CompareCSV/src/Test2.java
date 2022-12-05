import java.util.ArrayList;
import java.util.stream.Stream;

public class Test2 {
    static ArrayList<String> array = new ArrayList<>();

    public static void main(String[] args) {
//        for (String s : setIndexesOfCells(200)) {
//            System.out.println(s);
//        }
        for (String s : setIndexes(200)) {
            System.out.println(s);
        }
    }

    private static String[] setIndexesOfCells(int cellsCount) {
        String[] lettersIndexOfCells = new String[cellsCount];
        String appender = "";
        int count = 0;
        char letter;
        char additionalLetter;
        int indexLetter = 0;
        int additionalLetterCount = 0;

        for (int i = 0; i < cellsCount; i++) {
            letter = (char) (indexLetter + 65);
            lettersIndexOfCells[count] = appender + letter;
//            System.out.println(lettersIndexOfCells[count]);
            indexLetter++;
            count++;
            if ((i != 0) && (i % 25 == 0)) {
//                System.out.println("i % 26 == 0");
                additionalLetter = (char) (additionalLetterCount + 65);
                additionalLetterCount++;
                appender = String.valueOf(additionalLetter);
                indexLetter = 0;
            }
        }
        return lettersIndexOfCells;
    }
    private static ArrayList<String> setIndexes(int cellsCount) {
        char letter;
        char additionalLetter;
        int count = array.size();

        if (array.size() < cellsCount) {
            if (count < 26) {
                letter = (char) (count + 65);
                array.add(String.valueOf(letter));
            } else {
                letter = (char) ((count % 26) + 65);
                additionalLetter = (char) (((count / 26) - 1) + 65);
                array.add(String.valueOf(additionalLetter) + letter);
            }
            setIndexes(cellsCount);
        }
        return array;
    }

    private void test(String s) {
        Stream.of(s)
                .map((s1) -> s.split(";"))
                .toArray();
    }
}
