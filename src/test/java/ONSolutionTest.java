
import org.example.Hashing.HashingFunction;
import org.example.Hashing.ONSolution;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ONSolutionTest {

    @Test
    public void testBasicOperations() {
        ONSolution hash = new ONSolution();

        assertTrue(hash.insert("apple"));
        assertTrue(hash.insert("banana"));
        assertTrue(hash.search("apple"));
        assertTrue(hash.delete("apple"));
        assertFalse(hash.search("apple"));
        assertFalse(hash.delete("apple"));
        System.out.println(hash.SizeOfHashTable());
        System.out.println(hash.NumberOfRehashes());
        System.out.println(hash.NumberOfRehashesIndex());
    }

    @Test
    public void testLargeInsertions() {
        HashingFunction hash1 = new HashingFunction();
        ONSolution hash = new ONSolution();
        int count =1000000;
        for (int i = 0; i < count; i++) {
            assertTrue(hash.insert("word" + i));
        }

        for (int i = 0; i < count; i++) {
            assertTrue(hash.search("word" + i));
        }

        for (int i = 0; i < count; i += 2) {
            assertTrue(hash.delete("word" + i));
        }

        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                assertFalse(hash.search("word" + i));
            } else {
                assertTrue(hash.search("word" + i));
            }
        }

        System.out.println(hash.SizeOfHashTable());
        System.out.println(hash.NumberOfRehashes());
        System.out.println(hash.NumberOfRehashesIndex());
    }
    @Test
    public void testRandomInsertions() {
        ONSolution hash = new ONSolution();
        int count = 1000000;
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
        System.out.println(hash.NumberOfRehashesIndex());
    }
    @Test
    public void testWordSize10() {
        ONSolution hash = new ONSolution();
        int count = 1000000;
        List<String> words = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String word = generateRandomWord(new Random(), 10);
            System.out.println(i);
            assertTrue(hash.insert(word));
            words.add(word);
        }
        for (String word : words) {
            assertTrue(hash.search(word));
        }

        System.out.println("Finished inserting and verifying " + count + " random words of size 10.");
        System.out.println("Hash Table Size: " + hash.SizeOfHashTable());
        System.out.println("Number of Rehashes: " + hash.NumberOfRehashes());
        System.out.println(hash.NumberOfRehashesIndex());
    }

//    @Test
//    public void testWordSize15() {
//        ONSolution hash = new ONSolution();
//        int count = 1000000;
//        List<String> words = new ArrayList<>();
//
//        for (int i = 0; i < count; i++) {
//            String word = generateRandomWord(new Random(), 15);
//            assertTrue(hash.insert(word));
//            words.add(word);
//        }
//        for (String word : words) {
//
//            assertTrue(hash.search(word));
//        }
//
//
//        System.out.println("Finished inserting and verifying " + count + " random words of size 15.");
//        System.out.println("Hash Table Size: " + hash.SizeOfHashTable());
//        System.out.println("Number of Rehashes: " + hash.NumberOfRehashes());
//        System.out.println(hash.NumberOfRehashesIndex());
//    }
//
////    @Test
////    public void testWordSizeRandom1to20() {
////        ONSolution hash = new ONSolution();
////        int count = 1000000;
////        Random random = new Random();
////        HashSet<String> inserted = new HashSet<>();
////
////        for (int i = 0; i < count; i++) {
////            int wordSize = random.nextInt(20) + 1;
////            String word = generateRandomWord(random, wordSize);
////            while (!inserted.add(word)) {
////                word = generateRandomWord(random, wordSize);
////            }
////            assertTrue(hash.insert(word));
////        }
////
////        for (String word : inserted) {
////            assertTrue(hash.search(word));
////        }
////
////        System.out.println("Finished inserting and verifying " + count + " random words of random sizes (1 to 20).");
////        System.out.println("Hash Table Size: " + hash.SizeOfHashTable());
////        System.out.println("Number of Rehashes: " + hash.NumberOfRehashes());
////        System.out.println(hash.NumberOfRehashesIndex());
////    }
//


    private String generateRandomWord(Random random, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }
}
