package checker;

public class SpellChecker {
    private WordBank bank;
    private StringBuilder falseStr;
    private String file = "C:/Users/byung/workspace/java/spell-checker/src/checker/WordBank.txt";

    public SpellChecker() {
        falseStr = new StringBuilder();
        bank = new WordBank();
        bank.build(file);
    }

    public String check(String text) {
        String[] words = text.replaceAll("[\\p{P}&&[^\u0027]]", "").toLowerCase().split("\\s+");
        for (String word : words) {
            if (!bank.contains(word.toLowerCase())) {
                falseStr.append(word + " ");
            }
        }
        
        return falseStr.toString();
    }
}