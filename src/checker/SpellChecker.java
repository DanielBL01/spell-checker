package checker;

import java.util.List;

public class SpellChecker {
    private WordBank bank;
    // private StringBuilder falseStr;
    private StringBuilder list;
    private AutoComplete ac;
    private String file = "C:/Users/byung/workspace/java/spell-checker/wordbank/wordbank.txt";

    public SpellChecker() {
        // falseStr = new StringBuilder();
        list = new StringBuilder();
        bank = new WordBank();
        ac = new AutoComplete();

        bank.build(file);
        ac.buildTrie(bank.get());
    }

    public String check(String text) {
        // String[] words = text.replaceAll("[\\p{P}&&[^\u0027]]", "").toLowerCase().split("\\s+");
        // for (String word : words) {
        //     if (!bank.contains(word.toLowerCase())) {
        //         falseStr.append(word + " ");
        //     }
        // }
        
        // return falseStr.toString();
        List<String> result = ac.getWords(text);
        for (String word : result) {
            list.append(word + " ");
        }

        return list.toString();
    }
}