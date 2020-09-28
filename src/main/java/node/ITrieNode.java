package node;

public interface ITrieNode {

    void addCharacter(char character);

    void removeCharacter(char character);

    ITrieNode getTrieNodeForChar(char character);

    boolean containsCharacter(char character);

    void setEndOfWord(boolean setEndOfWord);
    boolean isEndOfWord();

    boolean isEmpty();
}
