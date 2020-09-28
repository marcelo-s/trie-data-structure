package trie;

import algorithm.ITrieAlgorithm;
import node.ITrieNode;
import node.TrieNodeMap;

public class TrieMap implements ITrie {
    private ITrieAlgorithm trieAlgorithm;

    private final ITrieNode root;

    public TrieMap(ITrieAlgorithm trieAlgorithm) {
        setTrieAlgorithm(trieAlgorithm);
        this.root = new TrieNodeMap();
    }

    @Override
    public void setTrieAlgorithm(ITrieAlgorithm trieAlgorithm) {
        this.trieAlgorithm = trieAlgorithm;
    }

    @Override
    public ITrieAlgorithm getTrieAlgorithm() {
        return this.trieAlgorithm;
    }

    @Override
    public ITrieNode getRoot() {
        return this.root;
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
