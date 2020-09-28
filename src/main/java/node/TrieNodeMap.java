package node;

import java.util.HashMap;
import java.util.Map;

public class TrieNodeMap implements ITrieNode {
    private final Map<Character, TrieNodeMap> charactersToTrieNodeMap;
    private boolean isEndOfWord;

    public TrieNodeMap() {
        charactersToTrieNodeMap = new HashMap<>();
    }

    @Override
    public void addCharacter(char character) {
        charactersToTrieNodeMap.put(character, new TrieNodeMap());
    }

    @Override
    public void removeCharacter(char character) {
        charactersToTrieNodeMap.remove(character);
    }

    @Override
    public TrieNodeMap getTrieNodeForChar(char character) {
        return charactersToTrieNodeMap.get(character);
    }

    @Override
    public boolean containsCharacter(char character) {
        return charactersToTrieNodeMap.containsKey(character);
    }

    @Override
    public boolean isEmpty() {
        return charactersToTrieNodeMap.isEmpty();
    }

    @Override
    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    @Override
    public boolean isEndOfWord() {
        return isEndOfWord;
    }
}
