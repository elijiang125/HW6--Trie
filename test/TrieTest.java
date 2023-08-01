import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
public class TrieTest {
    // TODO: accuracy tests

    Trie t1 = new Trie();
    Trie t2 = new Trie(new TrieNode());


    @Test
    public void insertTest() {
        t1.insert("food");

        assertTrue(t1.search("food"));
        assertFalse(t1.search("foo"));

        t1.insert("to");

        t1.insert("A");

        t1.insert("tea");
        t1.insert("ted");
        t1.insert("ten");
        t1.insert("i");
        t1.insert("in");
        t1.insert("inn");

        assertTrue(t1.search("to"));
        assertTrue(t1.search("A"));
        assertTrue(t1.search("tea"));
        assertTrue(t1.search("ted"));
        assertTrue(t1.search("ten"));
        assertTrue(t1.search("i"));
        assertTrue(t1.search("in"));
        assertTrue(t1.search("inn"));
        assertFalse(t1.search("innn"));

    }

    @Test
    public void searchTest() {
        t1.insert("food");

        assertTrue(t1.search("food"));
        assertFalse(t1.search("foo"));

        t1.insert("to");

        t1.insert("A");

        t1.insert("tea");
        t1.insert("ted");
        t1.insert("ten");
        t1.insert("i");
        t1.insert("in");
        t1.insert("inn");

        assertTrue(t1.search("to"));
        assertTrue(t1.search("A"));
        assertTrue(t1.search("tea"));
        assertTrue(t1.search("ted"));
        assertTrue(t1.search("ten"));
        assertTrue(t1.search("i"));
        assertTrue(t1.search("in"));
        assertTrue(t1.search("inn"));
        assertFalse(t1.search("innn"));
    }

    @Test
    public void deleteTest() {

        t1.insert("act");

        t1.insert("can");

        t1.insert("cant");

        t1.insert("cat");

        t1.insert("cats");

        t1.delete("act");
        assertFalse(t1.search("act"));

        t1.delete("cat");
        assertFalse(t1.search("cat"));
        assertTrue(t1.search("cats"));

        t1.delete("can");
        assertFalse(t1.search("can"));

        assertTrue(t1.search("cats"));

    }

    @Test
    public void generateWordsFromPrefixTest() {

        ArrayList<Entry> answer1 = new ArrayList<>();

        t1.insert("food");

        assertTrue(t1.search("food"));
        assertFalse(t1.search("foo"));

        t1.insert("to");

        t1.insert("A");

        t1.insert("tea");
        t1.insert("ted");
        t1.insert("ten");
        t1.insert("i");
        t1.insert("in");
        t1.insert("inn");

        answer1 = t1.generateWordsFromPrefix("te");

        assertEquals(answer1.get(0).getName(), "tea");



    }

    @Test
    public void getKeyTest() {
        TrieNode trieNode = new TrieNode();
        Trie testRoot = new Trie(trieNode);

        testRoot.insert("apple");
        testRoot.insert("banana");
        testRoot.insert("orange");

        char c = testRoot.getKey(testRoot.getRoot().children, testRoot.getRoot().children.get('a'));

        assertEquals('a', c);

        c = testRoot.getKey(testRoot.getRoot().children, testRoot.getRoot().children.get('b'));

        assertEquals('b', c);

        c = testRoot.getKey(testRoot.getRoot().children, testRoot.getRoot().children.get('o'));

        assertEquals('o', c);

        //okay let's go deeper into the trie

        testRoot.root = testRoot.getRoot().children.get('a');

        c = testRoot.getKey(testRoot.getRoot().children, testRoot.getRoot().children.get('p'));

        assertEquals('p', c);

    }

    @Test
    public void morethanNNodeTest() {
        t1.insert("act");

        t1.insert("can");

        t1.insert("cat");

        t1.insert("cats");

        assertFalse(t1.morethanNNode(t1.getRoot().children.get('a'), 1));

        assertFalse(t1.morethanNNode(t1.getRoot().children.get('a').children.get('c'), 1));

        assertFalse(t1.morethanNNode(t1.getRoot().children.get('a').children.get('c').children.get('t'), 1));
    }

    @Test
    public void changeisWordTest() {
        t1.insert("act");

        t1.insert("can");

        t1.insert("cant");

        t1.insert("cat");

        t1.insert("cats");

        t1.changeisWord(t1.root, "cat");

        assertFalse(t1.root.children.get('c').children.get('a').children.get('t').isWord);

        t1.changeisWord(t1.root, "cats");

        assertFalse(t1.root.children.get('c').children.get('a').children.get('t').children.get('s').isWord);
    }

    @Test
    public void deleteNodesTest() {
        t1.insert("act");

        t1.insert("can");

        t1.insert("cant");

        t1.insert("cat");

        t1.insert("cats");

        t1.deleteNodes(t1.root, "cant");

        assertFalse(t1.search("cant"));

        t1.deleteNodes(t1.root, "can");
        assertFalse(t1.search("can"));

        assertTrue(t1.search("cats"));
    }

    @Test
    public void conditionTwoTest() {
        t1.insert("act");

        t1.insert("can");

        t1.insert("cant");

        t1.insert("cat");

        t1.insert("cats");

        assertTrue(t1.conditionTwo(t1.root, "cat"));
        assertTrue(t1.conditionTwo(t1.root, "can"));

        assertFalse(t1.conditionTwo(t1.root, "cats"));
        assertFalse(t1.conditionTwo(t1.root, "cant"));
    }

    @Test
    public void conditionOneTest() {
        t1.insert("act");

        t1.insert("can");

        t1.insert("cant");

        t1.insert("cat");

        t1.insert("cats");

        assertTrue(t1.conditionOne(t1.root.children.get('a'), "ct"));

        assertFalse(t1.conditionOne(t1.root.children.get('c'), "an"));
    }





}
