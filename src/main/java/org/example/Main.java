package org.example;

import org.example.Hashing.ONSolution;
import org.example.Hashing.ON2Solution;
import org.example.Hashing.PrefectHashing;
import org.example.Hashing.BatchOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    public static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PrefectHashing hash;

        System.out.println(CYAN+"👋 Welcome to your Friendly Dictionary CLI!"+RESET);
        System.out.println(CYAN+"Choose a hash table implementation:"+RESET);
        System.out.println("1. O(n) Solution");
        System.out.println("2. O(n^2) Solution");
        System.out.print(BLUE+"Enter your choice (1 or 2): "+RESET);

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            hash = new ONSolution();
            System.out.println("✅ O(n) solution selected.");
        } else {
            hash = new ON2Solution();
            System.out.println("✅ O(n^2) solution selected.");
        }

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println(GREEN+"1. Insert a word"+RESET);
            System.out.println(YELLOW+"2. Delete a word"+RESET);
            System.out.println(PURPLE+"3. Search for a word"+RESET);
            System.out.println(BLUE+"4. Batch insert from file"+RESET);
            System.out.println(CYAN+"5. Batch delete from file"+RESET);
            System.out.println(RED+"6. Exit"+RESET);
            System.out.print("Enter your choice: ");

            int op = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (op) {
                case 1 -> {
                    System.out.print("Enter word to insert: ");
                    String word = scanner.nextLine().trim();
                    if (hash.insert(word)) {
                        System.out.println(GREEN + "✅ Word inserted successfully." + RESET);
                    } else {
                        System.out.println(RED + "❌ Word already exists." + RESET);
                    }
                }
                case 2 -> {
                    System.out.print("Enter word to delete: ");
                    String word = scanner.nextLine().trim();
                    if (hash.delete(word)) {
                        System.out.println(GREEN+"✅ Word deleted successfully."+RESET);
                    } else {
                        System.out.println(RED+"❌ Word doesn't exist in the dictionary."+RESET);
                    }
                }
                case 3 -> {
                    System.out.print("Enter word to search: ");
                    String word = scanner.nextLine().trim();
                    if (hash.search(word)) {
                        System.out.println(GREEN+"✅ Word found in the dictionary."+RESET);
                    } else {
                        System.out.println(RED+"❌ Word not found in the dictionary."+RESET);
                    }
                }
                case 4 -> {
                    System.out.print("Enter path to batch insert file: ");
                    String path = scanner.nextLine().trim();
                    BatchOperations.batchInsert(hash, path);
                }
                case 5 -> {
                    System.out.print("Enter path to batch delete file: ");
                    String path = scanner.nextLine().trim();
                    BatchOperations.batchDelete(hash, path);
                }
                case 6 -> {
                    System.out.println("\nNumber of table rehashes: " + hash.NumberOfRehashes());
                    System.out.println("Final hash table size: " + hash.SizeOfHashTable());
                    System.out.println(YELLOW+"👋 Thanks for using your Friendly Dictionary!"+RESET);
                    return;
                }
                default -> System.out.println(RED+"❌ Invalid option. Please try again."+RESET);
            }
        }
    }




}
