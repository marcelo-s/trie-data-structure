package algorithm;

import node.ITrieNode;
import trie.ITrie;

public class TrieRecursiveAlgorithm2 implements ITrieAlgorithm {

    /**
     * Insert a word in the trie
     *
     * @param trie The Trie where the word will be inserted
     * @param word The word to insert
     */
    @Override
    public void insertWord(ITrie trie, String word) {
        insertWord(trie.getRoot(), word, 0);
    }

    /**
     * Helper method that inserts the word in the Trie.
     * This algorithm sets the "isEndOfWord" flag to the trieNode that contains the last character
     *
     * @param trieNode The trieNode to insert the character
     * @param word     The word to insert
     * @param index    The index of the character to insert
     */
    private void insertWord(ITrieNode trieNode, String word, int index) {
        char character = word.charAt(index);
        if (!trieNode.containsCharacter(word.charAt(index))) {
            trieNode.addCharacter(character);
        }
        if (isLastCharacterInWord(word, index)) {
            trieNode.setEndOfWord(true);
        } else {
            insertWord(trieNode.getTrieNodeForChar(character), word, index + 1);
        }
    }


    /**
     * Deletes a word from the Trie
     *
     * @param word The word to delete
     * @return true if the word was deleted, that is, if the word existed in the Trie and was deleted
     */
    @Override
    public boolean deleteWord(ITrie trie, String word) {
        return deleteWord(trie.getRoot(), word, 0);
    }

    /**
     * Helper recursive method to delete a word from the Trie, if the word exists in the Trie.
     * The initial call to this function is with the "root" trieNode of the Trie.
     *
     * @param trieNode The trieNode to look for the character
     * @param word     The word to delete
     * @param index    The index of the character of the word
     * @return true if the word was deleted
     */
    private boolean deleteWord(ITrieNode trieNode, String word, int index) {
        char character = word.charAt(index);
        if (!trieNode.containsCharacter(character)) return false;
        boolean deleted;
        if (isLastCharacterInWord(word, index)) {
            deleted = trieNode.isEndOfWord();
            trieNode.setEndOfWord(false);
        } else {
            deleted = deleteWord(trieNode.getTrieNodeForChar(character), word, index + 1);
        }
        if (characterShouldBeRemoved(trieNode, character, deleted)) {
            trieNode.removeCharacter(character);
        }
        return deleted;
    }

    /**
     * Helper method that checks if a character living in a TrieNode should be removed
     *
     * @param trieNode  The trieNode that contains the character
     * @param character The character to remove
     * @param deleted   Flag that indicates if a word was deleted
     * @return true if the character should be removed from the trieNode
     */
    private boolean characterShouldBeRemoved(ITrieNode trieNode, char character, boolean deleted) {
        return deleted && trieNode.getTrieNodeForChar(character).isEmpty() && !trieNode.isEndOfWord();
    }

    /**
     * Checks if the Trie contains a specific word
     *
     * @param trie The Trie to search into
     * @param word The word to search
     * @return true if the Trie contains the word
     */
    @Override
    public boolean containsWord(ITrie trie, String word) {
        ITrieNode lastMatchingNode = getLastMatchingNode(trie.getRoot(), word, 0);
        return lastMatchingNode != null && lastMatchingNode.isEndOfWord();
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
        ITrieNode lastMatchingNode = getLastMatchingNode(trie.getRoot(), prefix, 0);
        return lastMatchingNode != null;
    }

    /**
     * Helper recursive method that returns the last matching node.
     * The initial call to this function is with the "root" trieNode of the trie.
     *
     * @param trieNode the trieNode to look for the character
     * @param word     the word to delete
     * @param index    the index of the character of the word
     * @return the last matching trieNode
     */
    private ITrieNode getLastMatchingNode(ITrieNode trieNode, String word, int index) {
        char character = word.charAt(index);
        if (!trieNode.containsCharacter(character)) return null;
        return isLastCharacterInWord(word, index)
                ? trieNode
                : getLastMatchingNode(trieNode.getTrieNodeForChar(character), word, index + 1);
    }

    /**
     * Helper method that checks if the index is at the last character of a word
     * @param word The word to check
     * @param index The index of the word
     * @return true if the index is equal to the position of the last character of the word
     */
    private boolean isLastCharacterInWord(String word, int index) {
        return index == word.length() - 1;
    }
}
