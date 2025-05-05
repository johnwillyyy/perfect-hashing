package org.example.Hashing;

import java.util.Random;
import java.util.ArrayList;

public class HashingFunction {
    // djb2 hash function
    public long djb2Hash(String str) {
        long hash = 0L;
        long mod = 1000000009L;
        Long g = 31L;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            hash = (hash*g+ hash) + c;
            hash %= mod;
        }
        return  (hash % mod);
    }

    // quadratic hash function using (ax^2 + bx + c) mod tableSize
    public int hash(String str, int tableSize, ArrayList<Long> randomVariables) {

        long mod = 1000000009L;
        long hash = djb2Hash(str)%mod;
        // Use larger random values for better spread
        long a = randomVariables.get(0) % mod;
        long b = randomVariables.get(1) % mod;
        long result = (a * hash + b) % mod;
        return (int) (result % tableSize);
    }

    // Generate random coefficients a, b, c
    public ArrayList<Long> CreateRandomVariables() {
        ArrayList<Long> list = new ArrayList<>();
        Random rand = new Random();
        long mod = 1000000009L;
        for (int i = 0; i < 2; i++) {
            long val = Math.abs(rand.nextLong()) % mod;
            if (val == 0) val = 1;
            list.add(val);
        }
        return list;
    }
}
