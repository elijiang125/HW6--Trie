import java.util.ArrayList;
import java.util.HashMap;

public class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isWord;
    int frequency;

    // TODO: initialize the TrieNode's properties
    public TrieNode() {

        this.children = new HashMap<>();
        this.isWord = false;
        this.frequency = 0;
        //set to change for later
    }

    public String toString () {
        return children.toString();
    }

}
