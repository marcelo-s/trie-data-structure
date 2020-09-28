package trie;

import algorithm.ITrieAlgorithm;
import node.ITrieNode;
import node.TrieNodeArray;

public class TrieArray implements ITrie {
    private ITrieAlgorithm trieAlgorithm;

    private final ITrieNode root;

    public TrieArray(ITrieAlgorithm trieAlgorithm) {
        setTrieAlgorithm(trieAlgorithm);
        this.root = new TrieNodeArray();
    }

    @Override
    public void setTrieAlgorithm(ITrieAlgorithm trieAlgorithm) {
        this.trieAlgorithm = trieAlgorithm;
    }

    public ITrieAlgorithm getTrieAlgorithm() {
        return this.trieAlgorithm;
    }

    @Override
    public ITrieNode getRoot() {
        return root;
    }

    @Override
    public void insertWord(String word) {
        trieAlgorithm.insertWord(this, word);
    }

    @Override
    public boolean deleteWord(String word) {
        return trieAlgorithm.deleteWord(this, word);
    }

    @Override
    public boolean containsWord(String word) {
        return trieAlgorithm.containsWord(this, word);
    }

    @Override
    public boolean containsPrefix(String prefix) {
        return trieAlgorithm.containsPrefix(this, prefix);
    }

}
