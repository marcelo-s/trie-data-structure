package algorithm;

import node.ITrieNode;
import trie.ITrie;

public class TrieRecursiveAlgorithm implements ITrieAlgorithm {

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
     * This algorithm sets the "isEndOfWord" flag to the trieNode that the last character points.
     *
     * @param trieNode The trieNode to insert the character
     * @param word     The word to insert
     * @param index    The index of the character to insert
     */
    private void insertWord(ITrieNode trieNode, String word, int index) {
        if (wordIsComplete(word, index)) {
            trieNode.setEndOfWord(true);
        } else {
            char currentChar = word.charAt(index);
            if (!trieNode.containsCharacter(currentChar)) {
                trieNode.addCharacter(currentChar);
            }
            insertWord(
                    trieNode.getTrieNodeForChar(currentChar),
                    word,
                    index + 1
            );
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
        if (wordIsComplete(word, index)) {
            if (!trieNode.isEndOfWord()) {
                return false;
            }
            trieNode.setEndOfWord(false);
            return true;
        }
        char currentChar = word.charAt(index);
        if (!trieNode.containsCharacter(currentChar)) {
            return false;
        }
        ITrieNode nextTrieNode = trieNode.getTrieNodeForChar(currentChar);
        boolean deleted = deleteWord(
                nextTrieNode,
                word,
                index + 1
        );
        if (characterShouldBeDeleted(nextTrieNode, deleted)) {
            trieNode.removeCharacter(currentChar);
        }
        return deleted;
    }

    /**
     * Helper method that checks if a character living in a TrieNode should be removed
     *
     * @param trieNode  The trieNode that contains the character
     * @param deleted   Flag that indicates if a word was deleted
     * @return true if the character should be removed from the trieNode
     */
    private boolean characterShouldBeDeleted(ITrieNode trieNode, boolean deleted) {
        return deleted && trieNode.isEmpty() && !trieNode.isEndOfWord();
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
     * Helper recursive method that returns the last matching node.
     * The initial call to this function is with the "root" trieNode of the trie.
     *
     * @param trieNode the trieNode to look for the character
     * @param word     the word to delete
     * @param index    the index of the character of the word
     * @return the last matching trieNode
     */
    private ITrieNode getLastMatchingNode(ITrieNode trieNode, String word, int index) {
        if (wordIsComplete(word, index)) {
            return trieNode;
        } else {
            char currentChar = word.charAt(index);
            if (!trieNode.containsCharacter(currentChar)) {
                return null;
            }
            return getLastMatchingNode(
                    trieNode.getTrieNodeForChar(currentChar),
                    word,
                    index + 1
            );
        }
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
     * Helper method that checks if the word is complete by using the index.
     *
     * @param word  The word to check into
     * @param index The index of the character in the word
     * @return true if the index is equal to the word length, meaning the word is complete
     */
    private boolean wordIsComplete(String word, int index) {
        return index == word.length();
    }
}
