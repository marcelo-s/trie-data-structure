package trie;

import algorithm.ITrieAlgorithm;
import node.ITrieNode;

public interface ITrie {

    void setTrieAlgorithm(ITrieAlgorithm trieAlgorithm);

    ITrieAlgorithm getTrieAlgorithm();

    ITrieNode getRoot();

    void insertWord(String word);

    boolean deleteWord(String word);

    boolean containsWord(String word);

    boolean containsPrefix(String prefix);
}
