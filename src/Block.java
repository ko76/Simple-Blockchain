package src;

import src.*;
import java.util.*;
import java.sql.Timestamp;

/**
 * A Block object to add to the chain. Contains the meta data, including index, previous block's hash, its own hash, the nonce, and the list of transactions ( arraylist data)
 * For simplicity, time is not included in the header 
 */
public class Block{

    private String hash;
    public String prevHash;
    private String data;
    private int index;
    private int nonce;
    private String time;

    /**
     * Construct a Block that will calculate the header of the block
     * @param data transaction
     * @param prevHash previous hash
     */
    public Block(int index, String prevHash, String data, int nonce, String hash, String time){
        this.data = data;
        this.prevHash = prevHash;
        this.hash = hash;
        this.nonce = nonce;
        this.index = index;
        this.time = time;
    }
 

    public String getHash(){
        return hash;
    }

    public String getTime(){
        return time;
    }
    
    public int getIndex(){
        return index;
    }

    public int getNonce(){
        return nonce;
    }

    public String getHashMetaData(){
        return Integer.toString(index) + prevHash + Hash.getMerkleRoot(data) + Integer.toString(nonce);
    }

    public String toString(){
        return "Index = " + index +"\nPrevious Hash = " + prevHash + "\nNonce = "+nonce;
    }
}
