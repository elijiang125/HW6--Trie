import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AutoCompleteTest {
    // TODO: accuracy tests


    private AutoComplete test;
    private AutoComplete test2;
    Trie t1 = new Trie();
    public AutoCompleteTest() throws IOException {
        test = new AutoComplete();

        test2 = new AutoComplete("C:\\Users\\Elizabeth\\Documents\\C343Labs\\HW6\\dataset.txt");

    }

    @Test
    public void autoCompleteTest() {

        ArrayList<Entry> topSix = test2.autoComplete("can");


        assertEquals("canopy", topSix.get(0).getName()); //canopy
        assertEquals("canceled", topSix.get(1).getName()); //canceled
        assertEquals("canvas", topSix.get(2).getName()); //canvas
        assertEquals("cant", topSix.get(3).getName()); //cant


    }
    @Test
    public void bubbleSortTest() {
        Entry e1 = new Entry(1, "Elizabeth");
        Entry e2 = new Entry(2, "Fluffy");
        Entry e3 = new Entry(3, "God");
        Entry e4 = new Entry(4, "PogChamp");

        ArrayList<Entry> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);



        ArrayList<Entry> newList = test.bubbleSort(list);

        assertEquals(newList.get(0).getName(), "PogChamp");
        assertEquals(newList.get(0).getFrequency(), 4);
        assertEquals(newList.get(1).getName(), "God");
        assertEquals(newList.get(1).getFrequency(), 3);
        assertEquals(newList.get(2).getName(), "Fluffy");
        assertEquals(newList.get(2).getFrequency(), 2);
        assertEquals(newList.get(3).getName(), "Elizabeth");
        assertEquals(newList.get(3).getFrequency(), 1);

    }
}
