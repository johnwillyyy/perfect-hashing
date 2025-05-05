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
//        System.out.println(table.size());
//        System.out.println(table.toString());
        if (search(s)) return false;
        ArrayList<String>a = new ArrayList<>();
        ArrayList<Integer>b = new ArrayList<>();
        for (int i = 0 ; i< table.size(); i++){
            if(table.get(i)!=null) a.add(table.get(i));
        }
        for (int i = 0 ; i< a.size(); i++){
            b.add(hashingFunction.hash(a.get(i),table.size(),coefficients));
        }
        System.out.println(a.toString());
        System.out.println(b.toString());
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
        if (size==0) return false;
        int index=hashingFunction.hash(s, table.size(), coefficients);
        System.out.println(s);
        System.out.println(index);
        return table.get(index)!=null && table.get(index).equals(s);
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
        while (!f) {
            int newSize = table.size();
            if(resize) newSize = limit*limit ;
            ArrayList<String> elements = new ArrayList<>() ;
            for (int i=0;i<table.size();i++) {
               if(table.get(i)!=null) {
                   elements.add(table.get(i));
               }
            }
            if(s != null)elements.add(s);
            table=new ArrayList<>(Collections.nCopies(newSize, null));
            coefficients=hashingFunction.CreateRandomVariables();
            f = true;
            for (String word:elements) {
                int index=hashingFunction.hash(word, table.size(), coefficients);
                if (table.get(index) != null && !table.get(index).equals(word)){
                    rehashes++;
                    f = false;
                    break;
                }
                table.set(index, word);
            }
        }
        return true;
    }
}