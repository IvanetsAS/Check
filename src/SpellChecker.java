import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SpellChecker {

    private HashSet<String> dict;

    private final int MAXDEPTH = 2;


    public SpellChecker() {
        createDictionary();
    }


    public boolean check(String word) {
        if (dict.contains(word.toLowerCase()))
            return true;
        else
            return false;
    }


    public Set<String> getCorrections(String word) {
        Set<String> corrections = new LinkedHashSet<>();

        Hashtable<Integer, Set<String>> leveledCorrections = new Hashtable<>();

        char[] wordArr = word.toLowerCase().toCharArray();

        correctMistake(leveledCorrections, wordArr, 0, 1);

        for (int i = 0; i < MAXDEPTH; i++)
            if (leveledCorrections.containsKey(i))
                corrections.addAll(leveledCorrections.get(i));

        return corrections;
    }

    private void correctMistake(Hashtable<Integer, Set<String>> corrections, char[] wordArr, int startIndex, int currentDepth) {

        for (int i = startIndex; i < wordArr.length; i++) {
            char letter = wordArr[i];

            for (int j = 1072; j < 1104; j++) {
                wordArr[i] = (char) j;
                String newWord = String.valueOf(wordArr);
                if (dict.contains(newWord)) {
                    if (!corrections.containsKey(startIndex))
                        corrections.put(startIndex, new LinkedHashSet<>());
                    corrections.get(startIndex).add(newWord);
                }

                if (currentDepth < MAXDEPTH)
                    correctMistake(corrections, wordArr, startIndex + 1, currentDepth + 1);
            }

            wordArr[i] = letter;
        }
    }

    private void createDictionary() {
        try {

            dict = new HashSet<>();
            Scanner scanner = new Scanner(new File("assets/dict.opcorpora.txt"), "utf-8");

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                if (
                        !currentLine.isEmpty()
                                && !currentLine.matches("^\\d*")
                )
                    dict.add(currentLine.split("\t")[0].toLowerCase());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
