package algorithm;

import node.ITrieNode;
import org.javatuples.Pair;
import trie.ITrie;

import java.util.ArrayDeque;
import java.util.Deque;

public class TrieIterativeAlgorithm implements ITrieAlgorithm {

    /**
     * Insert a word in the trie
     *
     * @param trie The Trie where the word will be inserted
     * @param word The word to insert
     */
    @Override
    public void insertWord(ITrie trie, String word) {
        insertWord(trie.getRoot(), word);
    }

    /**
     * Helper method that inserts the word in the Trie.
     * This algorithm sets the "isEndOfWord" flag to the trieNode that the last character points.
     *
     * @param trieNode The trieNode to insert the character
     * @param word     The word to insert
     */
    private void insertWord(ITrieNode trieNode, String word) {
        char currentChar;
        for (int i = 0; i < word.length(); i++) {
            currentChar = word.charAt(i);
            if (!trieNode.containsCharacter(currentChar)) {
                trieNode.addCharacter(currentChar);
            }
            trieNode = trieNode.getTrieNodeForChar(currentChar);
        }
        trieNode.setEndOfWord(true);
    }

    /**
     * Deletes a word from the Trie
     *
     * @param word The word to delete
     * @return true if the word was deleted, that is, if the word existed in the Trie and was deleted
     */
    @Override
    public boolean deleteWord(ITrie trie, String word) {
        return deleteWord(trie.getRoot(), word);
    }

    /**
     * Helper method to delete a word from the Trie, if the word exists in the Trie.
     * The initial call to this function is with the "root" trieNode of the Trie.
     *
     * @param trieNode The trieNode to look for the character
     * @param word     The word to delete
     * @return true if the word was deleted
     */
    private boolean deleteWord(ITrieNode trieNode, String word) {
        char currentChar;
        Deque<Pair<Character, ITrieNode>> trieNodesStack = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            currentChar = word.charAt(i);
            if (!trieNode.containsCharacter(currentChar)) return false;
            trieNodesStack.push(new Pair<>(currentChar, trieNode));
            trieNode = trieNode.getTrieNodeForChar(currentChar);
        }
        boolean wordFound = trieNode.isEndOfWord();
        if (wordFound) {
            trieNode.setEndOfWord(false);
            deleteTrieNodes(trieNodesStack);
        }
        return wordFound;
    }

    /**
     * Helper method that deletes trieNodes as a result of deleting a word
     *
     * @param trieNodesStack The stack of trieNodes that contain the word
     */
    private void deleteTrieNodes(Deque<Pair<Character, ITrieNode>> trieNodesStack) {
        while (!trieNodesStack.isEmpty()) {
            Pair<Character, ITrieNode> trieNodePair = trieNodesStack.remove();
            char character = trieNodePair.getValue0();
            ITrieNode trieNode = trieNodePair.getValue1();
            if (!trieNodeShouldBeRemoved(trieNode.getTrieNodeForChar(character))) return;
            trieNode.removeCharacter(character);
        }
    }

    /**
     * Helper method that checks if a character should be removed based on the trieNode values.
     * If a trieNode is empty, meaning it doesn't contain any characters, and if the trieNode is
     * end of a word, then the trieNode should be removed.
     *
     * @param trieNode The trieNode to be removed
     * @return true if the trieNode should be removed
     */
    private boolean trieNodeShouldBeRemoved(ITrieNode trieNode) {
        return trieNode.isEmpty() && !trieNode.isEndOfWord();
    }

    /**
     * Checks if the Trie contains a specific word
     *
     * @param trie The Trie to search into
     * @param word The word search
     * @return true if the Trie contains the word
     */
    @Override
    public boolean containsWord(ITrie trie, String word) {
        ITrieNode lastMatchingNode = getLastMatchingNode(trie.getRoot(), word);
        return lastMatchingNode != null && lastMatchingNode.isEndOfWord();
    }

    /**
     * Helper recursive method that returns the last matching node.
     * the initial call to this function is with the "root" trienode of the trie.
     *
     * @param trieNode the trieNode to look for the character
     * @param word     the word to delete
     * @return the last matching trienode
     */
    private ITrieNode getLastMatchingNode(ITrieNode trieNode, String word) {
        char currentChar;
        for (int i = 0; i < word.length(); i++) {
            currentChar = word.charAt(i);
            if (!trieNode.containsCharacter(currentChar)) return null;
            trieNode = trieNode.getTrieNodeForChar(currentChar);
        }
        return trieNode;
    }

    /**
     * Checks if the Trie contains a prefix
     * A word is a prefix of itself. So "word" is a prefix of "word"
     *
     * @param trie   The Trie to search into
     * @param prefix The prefix to check
     * @return true if the Trie contains the prefix
     */
    @Override
    public boolean containsPrefix(ITrie trie, String prefix) {
        ITrieNode lastMatchingNode = getLastMatchingNode(trie.getRoot(), prefix);
        return lastMatchingNode != null;
    }
}
