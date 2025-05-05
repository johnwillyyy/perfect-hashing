package org.example;

import org.example.Hashing.ON2Solution;
import org.example.Hashing.ONSolution;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ON2Solution hash = new ON2Solution();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a word (or type 'exit' to quit): ");
            String word = scanner.nextLine().trim();

            if (word.equalsIgnoreCase("exit")) {
                break;
            }

            if (hash.search(word)) {
                System.out.println("The word already exists in the table ❌");
            } else {
                hash.insert(word);
                System.out.println("The word was not found and has been inserted ✅");
            }
        }

        System.out.println("Number of table rehashes: " + hash.NumberOfRehashes());
        System.out.println("Final hash table size: " + hash.SizeOfHashTable());
    }
}
