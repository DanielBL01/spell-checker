package checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AutoComplete {
    private class Node {
        String prefix;
        Map<Character, Node> children;
        boolean isWord;
    
        private Node(String prefix) {
            this.prefix = prefix;
            this.children = new HashMap<>();
        }
    }

    private Node trie;

    public void buildTrie(Set<String> words) {
        trie = new Node("");
        Iterator<String> it = words.iterator();
        while(it.hasNext()) {
            insertWord(it.next());
        }
    }

    private void insertWord(String word) {
        Node curr = trie;
        for (int i = 0; i < word.length(); i++) {
            if (!curr.children.containsKey(word.charAt(i))) {
                curr.children.put(word.charAt(i), new Node(word.substring(0, i + 1)));
            }

            curr = curr.children.get(word.charAt(i));
            if (i == word.length() - 1) {
                curr.isWord = true;
            }
        }
    }

    public List<String> getWords(String pref) {
        List<String> result = new ArrayList<>();
        Node curr = trie;
        for (char c : pref.toCharArray()) {
            if (curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            } else {
                return result;
            }
        }

        findAllChildWords(curr, result);
        return result;
    }

    private void findAllChildWords(Node node, List<String> result) {
        if (node.isWord) {
            result.add(node.prefix);
        }

        for (char c : node.children.keySet()) {
            findAllChildWords(node.children.get(c), result);
        }
    }
}