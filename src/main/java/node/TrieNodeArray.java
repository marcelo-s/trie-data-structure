package node;

public class TrieNodeArray implements ITrieNode {
    private final TrieNodeArray[] charactersToTrieNodeMap;
    private int charactersCount = 0;
    private boolean isEndOfWord;

    public TrieNodeArray() {
        charactersToTrieNodeMap = new TrieNodeArray[26];
    }

    @Override
    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    @Override
    public boolean containsCharacter(char character) {
        return charactersToTrieNodeMap[getCharacterIndex(character)] != null;
    }

    @Override
    public TrieNodeArray getTrieNodeForChar(char character) {
        return charactersToTrieNodeMap[getCharacterIndex(character)];
    }

    @Override
    public void addCharacter(char character) {
        charactersToTrieNodeMap[getCharacterIndex(character)] = new TrieNodeArray();
        charactersCount++;
    }

    @Override
    public void removeCharacter(char character) {
        charactersToTrieNodeMap[getCharacterIndex(character)] = null;
        charactersCount--;
    }

    @Override
    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    @Override
    public boolean isEmpty() {
        return charactersCount == 0;
    }


    private int getCharacterIndex(char character) {
        return character - 'a';
    }
}
