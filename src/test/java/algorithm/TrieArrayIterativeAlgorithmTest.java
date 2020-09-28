package algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trie.ITrie;
import trie.TrieArray;
import trie.TrieMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrieArrayIterativeAlgorithmTest {
    private ITrie trie;

    @BeforeEach
    void setUp() {
        ITrieAlgorithm iterativeAlgorithm = new TrieIterativeAlgorithm();
        trie = new TrieArray(iterativeAlgorithm);
    }

    @Test
    void setTrie() {
        assertTrue(trie.getTrieAlgorithm() instanceof TrieIterativeAlgorithm);
        TrieRecursiveAlgorithm recursiveAlgorithm = new TrieRecursiveAlgorithm();

        trie.setTrieAlgorithm(recursiveAlgorithm);
        assertTrue(trie.getTrieAlgorithm() instanceof TrieRecursiveAlgorithm);
    }

    @Test
    void insertWord() {
        String wordToInsert = "cat";
        assertFalse(trie.containsWord(wordToInsert));
        trie.insertWord(wordToInsert);
        assertTrue(trie.containsWord(wordToInsert));
    }

    @Test
    void insertSimilarWords() {
        String originalWord = "cat";
        assertFalse(trie.containsWord(originalWord));
        trie.insertWord("cat");
        assertTrue(trie.containsWord("cat"));

        String prefixWord = "ca";
        trie.insertWord(prefixWord);
        assertTrue(trie.containsWord(prefixWord));
        assertTrue(trie.containsWord(originalWord));

        String suffixWord = "cata";
        trie.insertWord(suffixWord);
        assertTrue(trie.containsWord(suffixWord));
        assertTrue(trie.containsWord(prefixWord));
        assertTrue(trie.containsWord(originalWord));
    }

    @Test
    void deleteWord() {
        String wordToInsert = "dog";
        assertFalse(trie.containsWord(wordToInsert));
        trie.insertWord(wordToInsert);
        assertTrue(trie.containsWord(wordToInsert));

        trie.deleteWord(wordToInsert);
        assertFalse(trie.containsWord(wordToInsert));
    }

    @Test
    void deleteWordWithSimilarWordsInTrie() {
        // Insert initial word
        String wordToInsert = "dog";
        assertFalse(trie.containsWord(wordToInsert));
        trie.insertWord(wordToInsert);
        assertTrue(trie.containsWord(wordToInsert));

        String oneCharacterWord = "d";
        trie.insertWord(oneCharacterWord);

        String prefix = "do";
        trie.insertWord(prefix);

        String suffix = "doggie";
        trie.insertWord(suffix);

        // At this point the Trie contains the following words:
        // d, do, dog, doggie

        assertTrue(trie.containsWord(wordToInsert));
        assertTrue(trie.containsWord(prefix));
        assertTrue(trie.containsWord(suffix));
        assertTrue(trie.containsWord(oneCharacterWord));
        assertFalse(trie.getRoot().isEmpty());

        // "doggie" is deleted, but the other words should still be in the Trie
        trie.deleteWord(suffix);
        assertFalse(trie.containsWord(suffix));
        assertTrue(trie.containsWord(wordToInsert));
        assertTrue(trie.containsWord(prefix));
        assertTrue(trie.containsWord(oneCharacterWord));
        assertFalse(trie.getRoot().isEmpty());

        // "dog" is deleted, but the other words should still be in the Trie
        trie.deleteWord(wordToInsert);
        assertFalse(trie.containsWord(suffix));
        assertFalse(trie.containsWord(wordToInsert));
        assertTrue(trie.containsWord(prefix));
        assertTrue(trie.containsWord(oneCharacterWord));
        assertFalse(trie.getRoot().isEmpty());

        // "do" is deleted, only "d" remains"
        trie.deleteWord(prefix);
        assertFalse(trie.containsWord(suffix));
        assertFalse(trie.containsWord(wordToInsert));
        assertFalse(trie.containsWord(prefix));
        assertTrue(trie.containsWord(oneCharacterWord));
        assertFalse(trie.getRoot().isEmpty());

        // All words are deleted, the Trie should be empty
        trie.deleteWord(oneCharacterWord);
        assertFalse(trie.containsWord(suffix));
        assertFalse(trie.containsWord(wordToInsert));
        assertFalse(trie.containsWord(prefix));
        assertFalse(trie.containsWord(oneCharacterWord));
        assertTrue(trie.getRoot().isEmpty());
    }

    @Test
    void deleteWordWithSimilarWordsInTrie2() {
        // Insert initial word
        String wordToInsert = "dog";
        assertFalse(trie.containsWord(wordToInsert));
        trie.insertWord(wordToInsert);
        assertTrue(trie.containsWord(wordToInsert));

        String oneCharacterWord = "d";
        trie.insertWord(oneCharacterWord);

        String prefix = "do";
        trie.insertWord(prefix);

        String suffix = "doggie";
        trie.insertWord(suffix);

        // At this point the Trie contains the following words:
        // d, do, dog, doggie

        assertTrue(trie.containsWord(wordToInsert));
        assertTrue(trie.containsWord(prefix));
        assertTrue(trie.containsWord(suffix));
        assertTrue(trie.containsWord(oneCharacterWord));
        assertFalse(trie.getRoot().isEmpty());

        // "d" is deleted, but the other words should still be in the Trie
        trie.deleteWord(oneCharacterWord);
        assertFalse(trie.containsWord(oneCharacterWord));
        assertTrue(trie.containsWord(wordToInsert));
        assertTrue(trie.containsWord(prefix));
        assertTrue(trie.containsWord(suffix));
        assertFalse(trie.getRoot().isEmpty());

        // "do" is deleted, but the other words should still be in the Trie
        trie.deleteWord(wordToInsert);
        assertFalse(trie.containsWord(oneCharacterWord));
        assertFalse(trie.containsWord(wordToInsert));
        assertTrue(trie.containsWord(prefix));
        assertTrue(trie.containsWord(suffix));
        assertFalse(trie.getRoot().isEmpty());

        // "dog" is deleted, only "doggie" remains"
        trie.deleteWord(prefix);
        assertFalse(trie.containsWord(oneCharacterWord));
        assertFalse(trie.containsWord(wordToInsert));
        assertFalse(trie.containsWord(prefix));
        assertTrue(trie.containsWord(suffix));
        assertFalse(trie.getRoot().isEmpty());

        // All words are deleted, the Trie should be empty
        trie.deleteWord(suffix);
        assertFalse(trie.containsWord(oneCharacterWord));
        assertFalse(trie.containsWord(wordToInsert));
        assertFalse(trie.containsWord(prefix));
        assertFalse(trie.containsWord(suffix));
        assertTrue(trie.getRoot().isEmpty());
    }

    @Test
    void containsWord() {
        String wordToInsert = "testword";
        assertFalse(trie.containsWord(wordToInsert));

        trie.insertWord(wordToInsert);
        assertTrue(trie.containsWord(wordToInsert));
    }

    @Test
    void containsWordWithSimilarWords() {
        String wordToInsert = "cat";
        assertFalse(trie.containsWord(wordToInsert));

        trie.insertWord(wordToInsert);
        assertTrue(trie.containsWord(wordToInsert));

        String samePrefix = "ca";
        assertFalse(trie.containsWord(samePrefix));

        String withSuffix = wordToInsert + "b";
        assertFalse(trie.containsWord(withSuffix));
    }

    @Test
    void containsWordWithSimilarWordsInserted() {
        String wordToInsert = "cat";
        assertFalse(trie.containsWord(wordToInsert));

        trie.insertWord(wordToInsert);
        assertTrue(trie.containsWord(wordToInsert));

        String samePrefix = "ca";
        assertFalse(trie.containsWord(samePrefix));

        String withSuffix = wordToInsert + "b";
        assertFalse(trie.containsWord(withSuffix));

        trie.insertWord(samePrefix);
        assertTrue(trie.containsWord(samePrefix));

        trie.insertWord(withSuffix);
        assertTrue(trie.containsWord(withSuffix));
    }


    @Test
    void containsPrefix() {
        String wordToInsert = "catastrophic";
        assertFalse(trie.containsPrefix(wordToInsert));

        trie.insertWord(wordToInsert);
        assertTrue(trie.containsPrefix(wordToInsert));

        String prefix = "cat";
        assertTrue(trie.containsPrefix(prefix));

        String nonExistentPrefix = "cot";
        assertFalse(trie.containsPrefix(nonExistentPrefix));
    }
}

