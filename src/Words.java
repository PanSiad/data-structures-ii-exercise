import java.util.*;

public class Words {

    public static void main(String[] args) {

        //Φέρε το κείμενο ως ένα Array απο String
        String[] words = getWords();

        //Τύπωσε ποιο είναι το μήκος της κάθε λέξης του κειμένου.
        countWordsLength(words);

        //Τύπωσε πόσες φορές εμφανίζεται η κάθε λέξη στο κείμενο.
        countWordsOccurrences(words);

        //Τύπωσε τη μεγαλυτερη σε μήκος λέξη και το μήκος της.
        findLongestWord(words);

        //Τύπωσε τη λέξη που εμφανίζεται περισσότερες φορές στο κείμενο και πόσες είναι.
        findMostOccurredWord(words);

        //Τύπωσε τις διακριτές λέξεις που εμφανίζονται στο κείμενο
        // καθώς και τις διπλότυπες με τη σειρά εμφάνισης τους
        countDistinctAndPrintNonDistinctWords(words);

        //Τύπωσε τις διακριτές λέξεις που εμφανίζονται στο κείμενο με λεξικογραφική (φυσική) σειρά
        printDistinctWordsInLexicographicOrder(words);

        //Τύπωσε τις διακριτές λέξεις με τη σειρά που εμφανίζονται στο κείμενο
        // αφου αφαιρεσεις (φιλτραρεις) οσες εμφανίζονται ακριβώς 3 φορές
        removeWordsThatOccurExactlyThreeTimes(words);

        //Τύπωσε με λεξικογραφική (φυσική) σειρά τις διακριτές λέξεις που εμφανίζονται στο κείμενο
        // αφου αφαιρεσεις (φιλτραρεις) οσες εχουν μήκος μεγαλύτερο απο 3 γράμματα
        removeWordsLongerThanThreeLetters(words);
    }

    private static String[] getWords() {
        String jfk = "We choose to go to the moon. " +
                "We choose to go to the moon in this decade and do the other things, " +
                "not because they are easy, " +
                "but because they are hard, " +
                "because that goal will serve to organize and measure the best of our energies and skills, " +
                "because that challenge is one that we are willing to accept, " +
                "one we are unwilling to postpone, " +
                "and one which we intend to win, " +
                "and the others, too.";
        return jfk
                .toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .split(" ");
    }

    private static void countWordsLength(String[] words) {

        Set<String> uniqueWords = new HashSet<>();
        uniqueWords.addAll(Arrays.asList(words));

        //Print words' length
        System.out.println("Total unique words are " + uniqueWords.size() + ".\n");
        for (String word : uniqueWords
        ) {
            System.out.println("Length of word '" + word + "' is " + word.length() + ".");
        }
        System.out.println();
    }

    private static void countWordsOccurrences(String[] words) {

        //Create a HashMap data structure to store the occurrences
        Map<String, Integer> wordOccurrences = new HashMap<>();

        //Store the occurrences of each word to a unique HashMap value
        for (int i = 0; i < words.length; i++) {
            if (wordOccurrences.containsKey(words[i])) {
                wordOccurrences.put(words[i], wordOccurrences.get(words[i]) + 1);
            } else {
                wordOccurrences.put(words[i], 1);
            }
        }

        //Print the results
        System.out.println("Total occurrences for each word: \n");
        for (Map.Entry<String, Integer> entry : wordOccurrences.entrySet()
        ) {
            System.out.println("Word '" + entry.getKey() + "' occurrences are " + entry.getValue() + ".");
        }
        System.out.println();
    }

    private static void findLongestWord(String[] words) {

        Set<String> uniqueWords = new HashSet<>();
        uniqueWords.addAll(Arrays.asList(words));

        //Put words inside a TreeMap based on their keys (lengths) with ascending order
        //Value of each key represents words found with this length
        Map<Integer, String> wordLengths = new TreeMap<>();
        for (String word : uniqueWords
        ) {
            if (wordLengths.containsKey(word.length())) {
                String wordList = wordLengths.get(word.length());
                wordLengths.put(word.length(), wordList + " / " + word);
            } else {
                wordLengths.put(word.length(), word);
            }
        }

        //As it is a sorted Collection, it prints the value of largest key to find word(s) with max length
        System.out.println("Word(s) with maximum length (" + (wordLengths.size() + 1) + "): " + wordLengths.get(wordLengths.size() + 1));
        System.out.println();
    }

    private static void findMostOccurredWord(String[] words) {

        //Prepare the map with words and total occurrences
        //Get the maximum times a word occurred
        Map<String, Integer> nonSortedWordOccurrences = new HashMap<>();
        int mostOccurrences = 0;
        for (String word : words
        ) {
            if (nonSortedWordOccurrences.containsKey(word)) {
                nonSortedWordOccurrences.put(word, nonSortedWordOccurrences.get(word) + 1);
                if (nonSortedWordOccurrences.get(word) > mostOccurrences) {
                    mostOccurrences = nonSortedWordOccurrences.get(word);
                }
            } else {
                nonSortedWordOccurrences.put(word, 1);
            }
        }

        //print word(s) with max occurrences
        System.out.println("Most occurred word(s) with " + mostOccurrences + " occurrences:");
        for (Map.Entry<String, Integer> entry : nonSortedWordOccurrences.entrySet()
        ) {
            if (entry.getValue().equals(mostOccurrences)) {
                System.out.println(entry.getKey());
            }
        }
        System.out.println();
    }

    private static void countDistinctAndPrintNonDistinctWords(String[] words) {

        //LinkedHashMap to retain insertion order
        Map<String, Integer> wordOccurrences = new LinkedHashMap<>();
        for (String word : words
        ) {
            if (wordOccurrences.containsKey(word)) {
                wordOccurrences.put(word,wordOccurrences.get(word) + 1);
            } else {
                wordOccurrences.put(word, 1);
            }
        }

        //Print distinct words or add with correct insertion order to a non-distinct map
        Map<String, Integer> nonDistinctWords = new LinkedHashMap<>();
        System.out.println("Distinct words:");
        for (Map.Entry<String, Integer> entry : wordOccurrences.entrySet()
        ) {
            if (entry.getValue() == 1) {
                System.out.println(entry.getKey());
            } else {
                nonDistinctWords.put(entry.getKey(), entry.getValue());
            }
        }

        //Print non-distinct words
        System.out.println("\nNon-distinct words:");
        for (Map.Entry<String, Integer> entry : nonDistinctWords.entrySet()
        ) {
            System.out.println(entry.getKey());
        }
        System.out.println();
    }

    private static void printDistinctWordsInLexicographicOrder(String[] words) {

        //Get the lexicographic order of the words
        Set<String> distinctWordsInLexicographicOrder = new TreeSet<>();
        distinctWordsInLexicographicOrder.addAll(Arrays.asList(words));

        //Store the occurrences of each word to a unique HashMap value in order to distinguish distinct words
        Map<String, Integer> wordOccurrences = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (wordOccurrences.containsKey(words[i])) {
                wordOccurrences.put(words[i], wordOccurrences.get(words[i]) + 1);
            } else {
                wordOccurrences.put(words[i], 1);
            }
        }

        //Print distinct words in lexicographic order
        System.out.println("Distinct words in lexicographic order:");
        for (String word : distinctWordsInLexicographicOrder
        ) {
            if (wordOccurrences.get(word) == 1) {
                System.out.println(word);
            }
        }
        System.out.println();
    }

    private static void removeWordsThatOccurExactlyThreeTimes(String[] words) {

        //LinkedHashMap to retain insertion order
        Map<String, Integer> wordOccurrences = new LinkedHashMap<>();
        for (String word : words
        ) {
            if (wordOccurrences.containsKey(word)) {
                wordOccurrences.put(word, wordOccurrences.get(word) + 1);
            } else {
                wordOccurrences.put(word, 1);
            }
        }

        //Remove words with 3 occurrences via an Iterator
        System.out.println("Distinct words in natural order (words with 3 occurrences are removed):");
        int wordsRemoved = 0;

        Iterator<Map.Entry<String, Integer>> it = wordOccurrences.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() == 3) {
                it.remove();
                wordsRemoved++;
            }
        }

        //Print distinct words
        for (Map.Entry<String, Integer> entry : wordOccurrences.entrySet()
        ) {
            if (entry.getValue() == 1) {
                System.out.println(entry.getKey());
            }
        }

        System.out.println();
        System.out.println("Total word(s) removed: " + wordsRemoved);
        System.out.println();
    }

    private static void removeWordsLongerThanThreeLetters(String[] words) {

        //Get the lexicographic order of the words
        Map<String, Integer> wordsInLexicographicOrder = new TreeMap<>();
        for (String word : words
        ) {
            if (wordsInLexicographicOrder.containsKey(word)) {
                wordsInLexicographicOrder.put(word, wordsInLexicographicOrder.get(word) + 1);
            } else {
                wordsInLexicographicOrder.put(word, 1);
            }
        }

        //Remove words with length > 3 letters
        for (Iterator<Map.Entry<String, Integer>> it = wordsInLexicographicOrder.entrySet().iterator(); it.hasNext(); ) {
            if (it.next().getKey().length() > 3) {
                it.remove();
            }
        }

        //Print distinct words in lexicographic order
        System.out.println("Distinct words in lexicographic order:");
        for (Map.Entry<String, Integer> entry : wordsInLexicographicOrder.entrySet()
        ) {
            if (entry.getValue() == 1) {
                System.out.println(entry.getKey());
            }
        }
    }
}
