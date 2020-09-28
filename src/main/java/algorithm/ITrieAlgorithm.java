package algorithm;

import trie.ITrie;

public interface ITrieAlgorithm {

    void insertWord(ITrie trie, String word);

    boolean deleteWord(ITrie trie, String word);

    boolean containsWord(ITrie trie, String word);

    boolean containsPrefix(ITrie trie, String prefix);
}
