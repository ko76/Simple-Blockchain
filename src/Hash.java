package src;

import src.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;


public class Hash{
    /**
     * Returns a hash of the input string in hexadecimal
     * 
     * @param s the input String
     * @return hash of input string (hexidecimal
     */
    public static String hashString(String s){
        
        try {
            MessageDigest h = MessageDigest.getInstance(BlockChain.HASH_ALGORITHM);
            byte[] hash = h.digest(s.getBytes("UTF-8"));
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) result.append('0');
                    result.append(hex);
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }

    public static Block mineBlock(int index, String prevhash, String merkle){
        System.out.println("-----Mining Block number " + (index+1)+"-----");

        int nonce = 0;
        String header = Integer.toString(index) + prevhash + getMerkleRoot(merkle);
        String hash = "";
        String target = "";
        for(int i = 0; i < BlockChain.DIFFICULTY; i++){
            target+="0";
        }
        //System.out.println(target);
        //find nonce
        while(nonce<BlockChain.MAX_NONCE){
            String test = hashString(header + Integer.toString(nonce));
            //System.out.println(test);
            if(test.substring(0,BlockChain.DIFFICULTY).equals(target)){
                System.out.println("-----Block number "+(index+1)+" (index "+index +") found-----");
                Date d = new Date();
                Timestamp t = new Timestamp(d.getTime());
                return new Block(index, prevhash,merkle,nonce,test,t.toString());
            }
            //System.out.println(nonce);
            nonce++;
        }
        System.out.println("Mining block number " + (index+1) + " failed at nonce = " + nonce +".\n Exiting now.");
        return null;
    }

    /**
     * Calculates the hash of the block by header
     * @param b Block to hash
     * @return hash of the block
     */
    public static String getHash(Block b){
        return hashString(b.getHashMetaData());
    }

    /**
     * Gets the hash of the merkle root
     * @param merk merkle tree
     * @return hash of merkle root
     */
   public static String getMerkleRoot(ArrayList<String> merk){
        System.out.println("-----Finding merkle root------");
       ArrayList<String> newMerk = merk;
        if(newMerk.isEmpty()){
            return "";
        }
        if(newMerk.size()==1){
            return hashString(merk.get(0));
        }

        if(newMerk.size()%2==1){
            newMerk.add(merk.get(merk.size()-1));
        }
        
        while(newMerk.size()>1){
            ArrayList<String> tree = new ArrayList<String>();

            for(int i = 0; i < newMerk.size(); i+=2){
                tree.add(hashString(merk.get(i)+merk.get(i+1)));
            }
            newMerk = tree;
        }

        return newMerk.get(0);
   } 

   /**
     * Gets the hash of the merkle root
     * @param merk merkle tree
     * @return hash of merkle root
     */
    public static String getMerkleRoot(String merk){
        System.out.println("-----Finding merkle root------");
        return hashString(merk);
        /*
        newMerk = merk;
        if(newMerk.isEmpty()){
            return "";
        }
        if(newMerk.size()==1){
            return hashString(merk.get(0));
        }

        if(newMerk.size()%2==1){
            newMerk.add(merk.get(merk.size()-1));
        }
        
        while(newMerk.size()>1){
            String tree = new String();

            for(int i = 0; i < newMerk.size(); i+=2){
                tree.add(hashString(merk.get(i)+merk.get(i+1)));
            }
            newMerk = tree;
        }

        return newMerk.get(0);
        */
   } 
    
}
