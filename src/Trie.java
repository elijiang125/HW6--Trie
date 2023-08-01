import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Trie {
    TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public Trie(TrieNode root){
        this.root = root;
    }

    // Setters & Getters
    public TrieNode getRoot(){
        return this.root;
    }

    public void setRoot(TrieNode root){
        this.root = root;
    }

    // Actual methods -- part of Lab7
    // TODO:
    void insert(String word) {
        insertHelper(this.root, word);
    }

    void insertHelper(TrieNode node, String word) {
        //if there are no other characters in the word
        if (word.length() <= 0) {
            node.isWord = true;
            node.frequency++;
        }

        //if this character has not been in the HashMap yet
        else if (node.children.get(word.charAt(0)) == null) {
            //add onto HashMap and commit recursion
            node.children.put(word.charAt(0), new TrieNode());
            insertHelper(node.children.get(word.charAt(0)), word.substring(1));

        }
        else { //if character already exists, only commit recursion
            insertHelper(node.children.get(word.charAt(0)), word.substring(1));
        }

    }


    // TODO:
    boolean search(String word) {
        return searchHelper(this.root, word);
    }

    boolean searchHelper(TrieNode node, String word) {
        //if the word has no characters left, return true
        if (word.length() <= 0) {
            return node.isWord;
        }
        //if first character isn't in hashMap, return false
        else if (node.children.get(word.charAt(0)) == null) {
            return false;
        }
        else {
            return searchHelper(node.children.get(word.charAt(0)), word.substring(1));
        }

    }

    /*
    TODO: Remove the TrieNodes associated with the word. There are 3 cases to be concerned with.
        - key is unique: no part of key contains another key nor is the key itself a prefix of another key in the trie: remove all nodes
        - key is prefix key of another key: unmark the leaf node
        This is NOT the delete you implemented in lab.
 */
    void delete(String word){

        TrieNode firstNode = this.root.children.get(word.charAt(0));

        //condition 1: key is unique
        if (conditionOne(firstNode, word.substring(1))) {
            //if condition one fulfilled, delete all trieNodes
            this.root.children.remove(word.charAt(0));
        }
        //condition 2: key is prefix of another key: change isWord
        else if (conditionTwo(this.root, word)) {
            changeisWord(this.root, word);
        }
        //condition 3: last key isn't prefix of another key, but others are
        else if (!conditionTwo(this.root, word)){
            deleteNodes(this.root, word);
        }
    }

    // TODO: Gets all possible words with the prefix through traversing the Trie. If it's a word, then turn it into an Entry.
    //       If not, ignore. Put the Entry's into a list and return.
    //       Hint: Look at your MazeSolver with a stack for inspiration for the traversal.
    //       EX: If you have prefix "ca", then it should look at all combinations of the words starting with "ca".
    public ArrayList<Entry> generateWordsFromPrefix(String prefix){
        ArrayList<Entry> ls = new ArrayList<>();
            TrieNode node = root;
         for (char ch: prefix.toCharArray()) {
                if (node.children.containsKey(ch)) {
                    node = node.children.get(ch);
                }
                else {
                    return ls;
                }
         }

         prefixHelper(node, ls, prefix);
         return ls;
    }

    void prefixHelper(TrieNode node, ArrayList<Entry> list, String prefix) {
        if (node.isWord) {
            list.add(new Entry(node.frequency, prefix));
        }
        for (Character ch: node.children.keySet()) {
            prefixHelper(node.children.get(ch), list, prefix + ch);
        }
    }


    //helper function to get the key
    //NOTE: Test Successful
    public char getKey(HashMap<Character, TrieNode> map, TrieNode node) {
        char answer = ' ';
        for (char key: map.keySet()) {
            if (node.equals(map.get(key))) {
                answer = key;
            }
        }
        return answer;
    }


    //NOTE: Test Successful
    public boolean conditionOne(TrieNode node, String name) {
        //TODO: see if there aren't any nodes when given the word
        //NOTE: should be recursive
        //NOTE: this method HAS TO START FROM THE LETTER NODE, NOT ROOT

        if (name.isEmpty()) {
            //if there's nothing left, then it's correct!
            return true;
        }

        else if (morethanNNode(node, 1)) {
            //if there is more than one node, then condition 1 isn't met
            return false;
        }
        else {
            return conditionOne(node.children.get(name.charAt(0)), name.substring(1));
        }
    }

    //NOTE: Test Successful
    public boolean conditionTwo(TrieNode node, String name) {
        for (int i = 0; i < name.length(); i++) {
            node = node.children.get(name.charAt(i));
            //find the node for the last character in the string
        }
        if (morethanNNode(node, 0)) {
            //if there is at least one TrieNode in HashMap, return true;
            return true;
        }
        else {
            return false;
        }
    }

    //NOTE: Test Successful
    public void deleteNodes(TrieNode node, String name) {
        //TODO: delete some nodes that need to be deleted
        //Arguments: node -> this.root | name -> prefix
        //Notes: Under condition three, so no need to find nodes after the name

        //Step by Step process:
        //
        //Have a temporary TrieNode variable to be set as a permanent variable
        //Have a temporary char variable to be set as the key removed
        //where you cannot delete anything before that TrieNode

        //Next, run a for loop with a condition to continue until length of string
        //  if there is more than 1 node in the TrieNode, set it as permanent
        //  if there is one, but it's a word, set it as permanent
        //  go to the next hashMap based on the name

        //set the char to the getKey function using permanent variable's next hashMap
        //Then, use the permanent variable to remove

        TrieNode permanent = node;
        char c = ' ';

        for (int i = 0; i < name.length(); i++) {
            if(morethanNNode(node, 1)) {
                permanent = node;
                c = getKey(permanent.children, permanent.children.get(name.charAt(i)));
            }
            else if (node.isWord) {
                permanent = node;
                c = getKey(permanent.children, permanent.children.get(name.charAt(i)));
            }

            node = node.children.get(name.charAt(i));
        }

        System.out.println(permanent.children.toString());
        System.out.println(c);

        permanent.children.remove(c);
    }

    //NOTE: Test Successful
    public boolean morethanNNode(TrieNode node, int n) {
        //TODO: checks if there is more than n amount of nodes in the HashMap or not
        //True if there is more than n
        //False if there isn't

        if (node.children.size() > n) {
            return true;
        }
        else {
            return false;
        }
    }

    //NOTE: Test successful
    public void changeisWord(TrieNode node, String name) {
        //TODO: change isWord of a node to false

        if (name.isEmpty()) {
            node.isWord = false;
        }
        else {
            changeisWord(node.children.get(name.charAt(0)), name.substring(1));
        }

    }


}
