import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        SpellChecker spellChecker = new SpellChecker();

        System.out.println("Ready to check! Enter word: ");
        String currentWord = in.nextLine();

        boolean isCorrect = spellChecker.check(currentWord);

        System.out.println("Is word correct: " + isCorrect);
        if (!isCorrect)
            System.out.println(spellChecker.getCorrections(currentWord));
    }

}
