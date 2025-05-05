package org.example.Hashing;

import java.util.Random;
import java.util.ArrayList;

public class HashingFunction {
    ArrayList<Integer>primes;
    public HashingFunction() {
        this.primes = sieve();
    }
    public long djb2Hash(String str , Long g) {
        long hash = 0L;
        long mod = 1000000009L;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            hash = (hash*g+ hash) + c;
            hash %= mod;
        }
        return  (hash % mod);
    }


    public int hash(String str, int tableSize, ArrayList<Long> randomVariables) {

        long mod = 1000000009L;
        long hash = djb2Hash(str , randomVariables.get(2))%mod;
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
        int randomIndex = rand.nextInt(primes.size());
        long primeVal = primes.get(randomIndex);
        list.add(primeVal);
        return list;
    }
    public static ArrayList<Integer> sieve() {
        boolean[] isComposite = new boolean[100000 + 1];
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 100000; i++) {
            if (!isComposite[i]) {
                primes.add(i);
                for (int j = i * 2; j <= 100000; j += i) {
                    isComposite[j] = true;
                }
            }
        }
        return primes;
    }
}
