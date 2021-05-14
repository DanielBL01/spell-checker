package checker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WordBank {
    private Set<String> bank;

    public WordBank() {
        bank = new HashSet<>();
    }

    public void build(String wordBank) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(wordBank));
            String word;
            while ((word = reader.readLine()) != null) {
                bank.add(word);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean contains(String word) {
        return bank.contains(word);
    }

    public Set<String> get() {
        return bank;
    }
}
