package org.example.Hashing;

import org.example.Hashing.PrefectHashing;

import java.util.ArrayList;
import java.util.Collections;

public class ONSolution implements PrefectHashing {
    ArrayList<ArrayList<String>> table;
    ArrayList<ArrayList<Long>> randomValuesForEachIndex;
    ArrayList<Long>randomValue;
    ArrayList<Integer>elementsInEachIndex;
    ArrayList<Integer>virtualelementsInEachIndex;
    int tableSize;
    int elementsInTable;
    int NumberOfRehashes;
    int NumberOfReHashesIndex;
    HashingFunction hashingFunction;


    public ONSolution() {
        hashingFunction = new HashingFunction();
        tableSize = 16;
        elementsInTable = 0 ;
        NumberOfRehashes = 0;
        NumberOfReHashesIndex = 0 ;
        table = new ArrayList<>();
        randomValuesForEachIndex = new ArrayList<>();
        elementsInEachIndex = new ArrayList<>();
        virtualelementsInEachIndex = new ArrayList<>();
        for (int i = 0; i < tableSize; i++) {
            table.add(new ArrayList<String>());
            elementsInEachIndex.add(0);
            virtualelementsInEachIndex.add(0);
            randomValuesForEachIndex.add(new ArrayList<>());
        }
        randomValue = hashingFunction.CreateRandomVariables();
    }



    private void checkBucketLoadFactor(int index) {
        int count = elementsInEachIndex.get(index);
        int size = virtualelementsInEachIndex.get(index);
        if (count >=size) {
            increaseSizeOfBucket(index);
        }
    }


    public void insertInBucket(int index, String value) {
        int size = table.get(index).size();
        ArrayList<Long> randomVars = randomValuesForEachIndex.get(index);
        int pos = hashingFunction.hash(value, size, randomVars);
        if ( table.get(index).get(pos) == null) {
            table.get(index).set(pos, value);
            elementsInEachIndex.set(index, elementsInEachIndex.get(index) + 1);
            checkBucketLoadFactor(index);
            return;
        }

        ArrayList<String> elements = new ArrayList<>();
        for (String val :  table.get(index)) {
            if (val != null) {
                elements.add(val);
            }
        }
        elements.add(value);
        boolean placed = tryPlaceElementsInBucket(index, elements);
        if (!placed) {
            reHashTable(false);
            insert(value);
        }
    }
    private boolean tryPlaceElementsInBucket(int index, ArrayList<String> elements) {
        int size = table.get(index).size();
        int maxAttempts = 10;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            NumberOfReHashesIndex++;
            ArrayList<Long>A = new ArrayList<>();
            for (int i = 0; i < elements.size(); i++) {
                A.add(hashingFunction.djb2Hash(elements.get(i)));
            }
            System.out.println(A.toString());
            ArrayList<String> newBucket = new ArrayList<>(Collections.nCopies(size, null));
            ArrayList<Long> randomVars = hashingFunction.CreateRandomVariables();
            boolean success = true;
            for (String element : elements) {
                int pos = hashingFunction.hash(element,size,randomVars);
                if (newBucket.get(pos) == null) {
                    newBucket.set(pos, element);
                } else {
                    success = false;
                    break;
                }
            }
            if (success) {
                table.set(index, newBucket);
                randomValuesForEachIndex.set(index, randomVars);
                return true;
            }
        }
        return false;
    }

    public void increaseSizeOfBucket(int index) {
        ArrayList<String> elements = new ArrayList<>();
        for (String val : table.get(index)) {
            if (val != null) {
                elements.add(val);
            }
        }
        virtualelementsInEachIndex.set(index, virtualelementsInEachIndex.get(index) *2);
        int currentSize = virtualelementsInEachIndex.get(index) ;
        int newSize = currentSize * currentSize;
        if(newSize<0||newSize>10000)newSize = 10000;
        ArrayList<String> newBucket = new ArrayList<>(Collections.nCopies(newSize, null));
        table.set(index, newBucket);
        tryPlaceElementsInBucket(index, elements);
    }

    public Boolean canInsert(String s){
        int index = hashingFunction.hash(s,tableSize,randomValue);
        int sizeOfIndex = this.table.get(index).size();
        if(sizeOfIndex == 0){
            ArrayList<Long> randVars = hashingFunction.CreateRandomVariables();
            ArrayList<String> newBucket = new ArrayList<>(Collections.nCopies(64, null));
            virtualelementsInEachIndex.set(index,8);
            randomValuesForEachIndex.set(index, randVars);
            elementsInEachIndex.set(index, 1);
            int index2 = hashingFunction.hash(s,64,randVars);
            newBucket.set(index2, s);
            table.set(index, newBucket);
            return true;
        }
        int index2 = hashingFunction.hash(s, sizeOfIndex, randomValuesForEachIndex.get(index));
        String current = table.get(index).get(index2);
        if (current != null && current.equals(s)) {
            return false;
        }
        insertInBucket(index, s);
        return true;
    }



    public void reHashTable(boolean increaseSize) {
        ArrayList<String> allElements = new ArrayList<>();
        for (int i = 0; i < tableSize; i++) {
            for (String val : table.get(i)) {
                if (val != null) {
                    allElements.add(val);
                }
            }
        }
        if(increaseSize)tableSize*=2;
        elementsInTable = 0 ;
        elementsInEachIndex = new ArrayList<>();
        table = new ArrayList<>();
        virtualelementsInEachIndex = new ArrayList<>();
        randomValuesForEachIndex = new ArrayList<>();
        for (int i = 0; i < tableSize; i++) {
            table.add(new ArrayList<String>());
            elementsInEachIndex.add(0);
            virtualelementsInEachIndex.add(0);
            randomValuesForEachIndex.add(new ArrayList<>());
        }
        randomValue = hashingFunction.CreateRandomVariables();
        for (String s : allElements) {
            insert(s);
        }
        NumberOfRehashes ++;

    }



    @Override
    public boolean insert(String s) {
//        System.out.println(tableSize);
//        System.out.println(elementsInTable);
//        System.out.println(elementsInEachIndex.toString());
//        System.out.println(virtualelementsInEachIndex.toString());
//        ArrayList<Integer> r = new ArrayList<>();
//        for (int i = 0; i < tableSize; i++) {
//            r.add(table.get(i).size());
//        }
//        System.out.println(r.toString());
//        for (int i = 0; i < tableSize; i++) {
//            ArrayList<String> bucket = table.get(i);
//            for (int j = 0; j < bucket.size(); j++) {
//                String val = bucket.get(j);
//                if (val != null&&val.equals("word0")) {
//                    System.out.println("String \"" + val + "\" is at index1 = " + i + ", index2 = " + j);
//                }
//            }
//        }
        if(canInsert(s)){
            elementsInTable++;
            if (elementsInTable == tableSize) {
                reHashTable(true);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String s) {
        int index = hashingFunction.hash(s,tableSize,randomValue);
        int sizeOfIndex = this.table.get(index).size();
        if (sizeOfIndex == 0) return false;
        int index2 = hashingFunction.hash(s, sizeOfIndex, randomValuesForEachIndex.get(index));
        String current = table.get(index).get(index2);
        if (current != null && current.equals(s)) {
            table.get(index).set(index2, null);
            elementsInTable--;
            elementsInEachIndex.set(index, elementsInEachIndex.get(index) + -1);
            return true;
        }
        return false;
    }

    @Override
    public boolean search(String s) {
//        System.out.println("Searching for " + s);
        int index = hashingFunction.hash(s,tableSize,randomValue);
//        System.out.println(index);
        int sizeOfIndex = this.table.get(index).size();
        if(sizeOfIndex == 0) return false;
        int index2 = hashingFunction.hash(s,sizeOfIndex,randomValuesForEachIndex.get(index));
//        System.out.println(index2);
        String value = table.get(index).get(index2);

        return value != null && value.equals(s);
    }

    @Override
    public int NumberOfRehashes() {
        return NumberOfRehashes;
    }

    public int NumberOfRehashesIndex() {
        return NumberOfReHashesIndex;
    }

    @Override
    public int SizeOfHashTable() {
        return tableSize;
    }

}
