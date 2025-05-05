package org.example.Hashing;

import java.util.ArrayList;
import java.util.Collections;

public class ON2Solution implements PrefectHashing {
    private ArrayList<String> table;
    private ArrayList<Long> coefficients;
    private int rehashes;
    private final HashingFunction hashingFunction;
    private int limit;
    private int size;

    public ON2Solution() {
        this.hashingFunction=new HashingFunction();
        this.rehashes=0;
        this.limit=8;
        this.size=0;
        this.coefficients=hashingFunction.CreateRandomVariables();
        this.table=new ArrayList<>(Collections.nCopies(64, null));
    }

    @Override
    public boolean insert(String s) {
        if (search(s)) return false;
        int index=hashingFunction.hash(s, table.size(), coefficients);
        if(table.get(index)==null) {
            table.set(index, s);
            size++ ;
            if (size>limit) {
                limit*=2;
                rehash(true , null);
            }
            return true;
        }
        return rehash(false, s);
    }

    @Override
    public boolean delete(String s) {
        if (!search(s)) return false;
        int index=hashingFunction.hash(s, table.size(), coefficients);
        table.set(index, null);
        size--;
        return true;
    }

    @Override
    public boolean search(String s) {
        int index=hashingFunction.hash(s, table.size(), coefficients);
        if(table.get(index)!=null && table.get(index).equals(s)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int NumberOfRehashes() {
        return rehashes;
    }

    @Override
    public int SizeOfHashTable() {
        return table.size();
    }

    private boolean rehash(boolean resize , String s) {
        boolean f =false;
        ArrayList<Long> newCoefficients = new ArrayList<>();
        ArrayList<String> newTable = new ArrayList<>();
        while (!f) {
            rehashes++;
            int newSize = table.size();
            if(resize) newSize = limit*limit ;

            ArrayList<String> elements = new ArrayList<>() ;
            for (int i=0;i<table.size();i++) {
               if(table.get(i)!=null) {
                   elements.add(table.get(i));
               }
            }
            if(s != null)elements.add(s);

            newCoefficients = hashingFunction.CreateRandomVariables();
            newTable = new ArrayList<>(Collections.nCopies(newSize, null));

            f = true;
            for (String word : elements) {
                int index = hashingFunction.hash(word, newSize, newCoefficients);
                if (newTable.get(index) != null) {
                    // Collision detected, need to retry with new coefficients
                    f = false;
                    break;
                }
                newTable.set(index, word);
            }
        }
        table = newTable;
        coefficients = newCoefficients;
        size = s != null ? size + 1 : size;
        return true;
    }
}