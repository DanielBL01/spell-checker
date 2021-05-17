# Spell Checker

## Getting the Dictionary of Words

Ubuntu has a file /usr/share/dict/american-english which is is an ASCII file which contains an alphabetic list of words, one per line.

## Auto Complete

Using the dictionary of words, build a prefix tree or trie using the words in the dictionary. Each node contains a HashMap of 
characters and Nodes such that at the root node, the characters in the map represents the different paths from the current node to the children nodes. 
Each node also contains a boolean to indicate whether the path from the root node to the node is a word.

Then in order to find all words for a prefix, a simple depth first search algorithm can be used to return all words in the tree.
```
class Node {
    String prefix;
    Map<Character, Node> children;
    boolean isWord;

    private Node(String prefix) {
        this.prefix = prefix;
        this.children = new HashMap<>();
    }
}

words: a, ab, b, acd
0 - true, O - false

            root
         a/     b\
         0        O
       b/ \c
       0   O
          /d
         0 
```