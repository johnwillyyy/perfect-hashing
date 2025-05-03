package Hashing;

public interface PrefectHashing {
    boolean insert (String s);
    boolean delete (String s);
    boolean search (String s);
    int NumberOfRehashes ();
    int SizeOfHashTable ();
}
