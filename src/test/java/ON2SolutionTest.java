
import org.example.Hashing.HashingFunction;
import org.example.Hashing.ON2Solution;
import org.example.Hashing.ONSolution;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ON2SolutionTest {

    @Test
    public void testBasicOperations() {
        ON2Solution hash = new ON2Solution();

        assertTrue(hash.insert("apple"));
        assertTrue(hash.insert("banana"));
        assertTrue(hash.search("apple"));
        assertTrue(hash.delete("apple"));
        assertFalse(hash.search("apple"));
        assertFalse(hash.delete("apple"));
        System.out.println(hash.SizeOfHashTable());
        System.out.println(hash.NumberOfRehashes());
    }

    @Test
    public void testLargeInsertions() {
        ON2Solution hash = new ON2Solution();
        int count =10000;
        for (int i = 0; i < count; i++) {
            assertTrue(hash.insert("kerokero" + i));
        }

        for (int i = 0; i < count; i++) {
            assertTrue(hash.search("kerokero" + i));
        }

        for (int i = 0; i < count; i += 2) {
            assertTrue(hash.delete("kerokero" + i));
        }

        for (int i = 0; i < count; i++) {
            System.out.println(i);
            if (i % 2 == 0) {
                assertFalse(hash.search("kerokero" + i));
            } else {
                assertTrue(hash.search("kerokero" + i));
            }
        }

        System.out.println(hash.SizeOfHashTable());
        System.out.println(hash.NumberOfRehashes());
    }
    @Test
    public void testRandomInsertions() {
        ON2Solution hash = new ON2Solution();
        int count = 10000;
        Random random = new Random();
        HashSet<String> inserted = new HashSet<>();

        for (int i = 0; i < count; i++) {
            String word = generateRandomWord(random, 5);
            while (!inserted.add(word)) {
                word = generateRandomWord(random, 5);
            }
            assertTrue(hash.insert(word));
        }
        for (String word : inserted) {
            assertTrue(hash.search(word));
        }
        System.out.println("Finished inserting and verifying " + count + " random words.");
        System.out.println("Hash Table Size: " + hash.SizeOfHashTable());
        System.out.println("Number of Rehashes: " + hash.NumberOfRehashes());
    }
    @Test
    public void testWordSize10() {
        ON2Solution hash = new ON2Solution();
        int count = 10000;
        List<String> words = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String word = generateRandomWord(new Random(), 10);
            assertTrue(hash.insert(word));
            words.add(word);
        }
        for (String word : words) {
            assertTrue(hash.search(word));
        }

        System.out.println("Finished inserting and verifying " + count + " random words of size 10.");
        System.out.println("Hash Table Size: " + hash.SizeOfHashTable());
        System.out.println("Number of Rehashes: " + hash.NumberOfRehashes());

    }

    @Test
    public void testWordSize15() {
        ON2Solution hash = new ON2Solution();
        int count = 10000;
        List<String> words = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String word = generateRandomWord(new Random(), 15);
            assertTrue(hash.insert(word));
            words.add(word);
        }
        for (String word : words) {

            assertTrue(hash.search(word));
        }


        System.out.println("Finished inserting and verifying " + count + " random words of size 15.");
        System.out.println("Hash Table Size: " + hash.SizeOfHashTable());
        System.out.println("Number of Rehashes: " + hash.NumberOfRehashes());

    }

    @Test
    public void testWordSizeRandom1to20() {
        ON2Solution hash = new ON2Solution();
        int count = 10000;
        Random random = new Random();
        HashSet<String> inserted = new HashSet<>();

        for (int i = 0; i < count; i++) {
            int wordSize = random.nextInt(20)+6;
            String word = generateRandomWord(random, wordSize);
            while (!inserted.add(word)) {
                word = generateRandomWord(random, wordSize);
            }
            assertTrue(hash.insert(word));
        }

        for (String word : inserted) {
            assertTrue(hash.search(word));
        }

        System.out.println("Finished inserting and verifying " + count + " random words of random sizes (1 to 20).");
        System.out.println("Hash Table Size: " + hash.SizeOfHashTable());
        System.out.println("Number of Rehashes: " + hash.NumberOfRehashes());
    }



    private String generateRandomWord(Random random, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }
}
