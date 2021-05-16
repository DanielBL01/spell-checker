package checker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpellChecker {
    private WordBank bank;
    private StringBuilder falseStr;
    private Set<String> track;
    private StringBuilder list;
    private AutoComplete ac;
    private String file = System.getProperty("user.dir") + "/wordbank/wordbank.txt";

    public SpellChecker() {
        track = new HashSet<>();
        list = new StringBuilder();
        falseStr = new StringBuilder();
        bank = new WordBank();
        ac = new AutoComplete();

        bank.build(file);
        ac.buildTrie(bank.get());
    }

    public String autoComplete(String prefix) {
        track.clear();
        list.setLength(0);

        List<String> result = ac.getWords(prefix);
        if (result.isEmpty()) {
            return "0";
        } else {
            for (String word : result) {
                if (track.contains(word.substring(0, word.length() - 1)) || word.substring(word.length() - 2, word.length()).equals("'s")) {
                    continue;
                }
                track.add(word);
                list.append(word + " ");
            }
    
            return list.toString();
        }
    }

    public String spellCheck(String text) {
        String[] words = text.replaceAll("[\\p{P}&&[^\u0027]]", "").toLowerCase().split("\\s+");
        for (String word : words) {
            if (!bank.get().contains(word)) {
                falseStr.append(word + " ");
            }
        }

        return falseStr.toString();
    }
}