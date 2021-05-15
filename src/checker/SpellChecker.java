package checker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpellChecker {
    private WordBank bank;
    private Set<String> track;
    private StringBuilder list;
    private AutoComplete ac;
    private String file = System.getProperty("user.dir") + "/wordbank/wordbank.txt";

    public SpellChecker() {
        track = new HashSet<>();
        list = new StringBuilder();
        bank = new WordBank();
        ac = new AutoComplete();

        bank.build(file);
        ac.buildTrie(bank.get());
    }

    public String autoComplete(String text) {
        track.clear();
        list.setLength(0);

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