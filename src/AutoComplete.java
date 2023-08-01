import java.io.IOException;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AutoComplete {
    private Trie trie;
    private String file = "dataset.txt"; // hardcoded for our testing purposes :p

    public AutoComplete() throws IOException {
        this.trie = new Trie();
        buildTrie();
    }

    public AutoComplete(String file) throws IOException {
        this.trie = new Trie();
        this.file = file;
        buildTrie();
    }

    public Trie getTrie(){
        return this.trie;
    }


    // TODO: Build the trie from the words from the file.
    public void buildTrie() throws IOException {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            System.out.print(file + " loaded. ");
            Instant start = Instant.now();
            String line;
            int wordCount = 0;
            while((line = reader.readLine()) != null){
                String text = line.toLowerCase();
                text = text.replaceAll("\\p{Punct}", "");
                String[] words = text.split("\\s+"); // splits by whitespace
                for(String word: words){
                    // TODO: add here lol
                    this.trie.insert(word);
                    //inserts the words into the trie
                }
            }
            Instant end = Instant.now();
            long time = Duration.between(start, end).toMillis();
            System.out.print(wordCount + " words loaded into Trie in " + time + " ms\n");
        } catch (IOException e){
            throw e;
        }

    }


    // TODO: Returns a list of the top 6 ranked (frequency) words starting with the
    //       given prefix (must use BubbleSort)
    //       Hint: don't overthink this.
    public ArrayList<Entry> autoComplete(String prefix){
        ArrayList<Entry> returnedList = new ArrayList<Entry>();
        // get their nodes using bubblesort + that function
        ArrayList<Entry> fullList =  trie.generateWordsFromPrefix(prefix);
        fullList = bubbleSort(fullList);

        System.out.println("List sorted.");
        System.out.println(fullList.get(0).getName());

        if (fullList.size() > 6) {
            for (int i = 0; i < 6; i++) { //get top 6 most frequent words
                returnedList.add(fullList.get(i));
            }
        }
        return returnedList;
    }

    // TODO: Implement BubbleSort. Sort by frequency of the Entry. Return the list of entries sorted.
    //     Hint:
    public ArrayList<Entry> bubbleSort(ArrayList<Entry> ls){
        //Entry to sort
        for (int i = 0; i < ls.size(); i++) {
            for (int j = 0; j < ls.size() - 1 - i; j++) {
                if (ls.get(j).getFrequency() < ls.get(j + 1).getFrequency()) {

                    //if greater, move the spots
                    Entry greaterFreq = ls.get(j);
                    Entry lessFreq = ls.get(j + 1);
                    ls.remove(j); //remove both entries
                    ls.remove(j);
                    ls.add(j, greaterFreq); //add both entries
                    ls.add(i, lessFreq);
                }
            }
        }
        return ls;
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String file = input.nextLine();
        AutoComplete a = new AutoComplete(file);
        String answer = "y";
        while (answer.equals("y")) {
            System.out.print("Enter word to find prefix (0 to quit program): ");
            String term = input.nextLine();
            if(term.equals("0")){
                System.out.println("buh bye");
                break;
            }
            for(Entry e : a.autoComplete(term)){
                System.out.println(e);
            }
            System.out.println("==========================================");
        }
        input.close();

    }
}
