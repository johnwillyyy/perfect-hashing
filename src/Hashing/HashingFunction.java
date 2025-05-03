package Hashing;
import java.util.Random;
import java.util.ArrayList;

public class HashingFunction {
    public int djb2Hash(String str) {
        int hash = 5381;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            hash = ((hash << 5) + hash) + c; // hash * 33 + c
        }
        return Math.abs(hash);
    }
    public int hash(String str , int tableSize , ArrayList<Integer> RandomVaribales) {
        int hash = djb2Hash(str);
        long i = hash % tableSize;
        long result = (i * i * RandomVaribales.get(0)) % tableSize;
        result = (result + (i * RandomVaribales.get(1)) % tableSize) % tableSize;
        result = (result + RandomVaribales.get(2)) % tableSize;
        return (int)(result % tableSize);
    }
    public ArrayList<Integer> CreateRandomVariables  (int tableSize){
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            list.add(rand.nextInt(tableSize));
        }
        return list;
    }



}
