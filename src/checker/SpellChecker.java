package checker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpellChecker {
    private WordBank bank;
    private Set<String> track;
    private StringBuilder list;
    private AutoComplete ac;
    private String file = "C:/Users/byung/workspace/java/spell-checker/wordbank/wordbank.txt";

    public SpellChecker() {
        track = new HashSet<>();
        list = new StringBuilder();
        bank = new WordBank();
        ac = new AutoComplete();

        bank.build(file);
        ac.buildTrie(bank.get());
    }

    public String check(String text) {
        List<String> result = ac.getWords(text);
        for (String word : result) {
            if (track.contains(word.substring(0, word.length() - 1)) || track.contains(word.substring(0, word.length() - 2))) {
                continue;
            }
            track.add(word);
            list.append(word + " ");
        }

        return list.toString();
    }
}