package src;

import src.*;
import java.util.ArrayList;
import com.google.gson.*;
import java.util.Scanner;

public class BlockChain{
    public static final String HASH_ALGORITHM = "SHA-256";
    public static int DIFFICULTY = 4;
    public static int MAX_NONCE = Integer.MAX_VALUE;
    private Block genesis;

    private static ArrayList<Block> chain = new ArrayList<Block>();

    public BlockChain(){
        
    }


    public boolean createBlock(String merk){
        Block b = Hash.mineBlock(getLastIndex()+1,getLastHash(),merk);
        if(b == null){
            return false;
        }
        chain.add(b);
        
        return true;
    }

    public void createGenesis(String merkle){
        String gen = "";
        for (int i = 0; i < 64; i++){
            gen+="0";
        }
        genesis = Hash.mineBlock(0, gen, merkle);
        System.out.println("-----Genesis Block Created-----");
        chain.add(genesis);
    }

    public static boolean verifyChain(){
        Block block;
        Block prev; 
        String target = "";
        for(int i = 0; i < DIFFICULTY; i++){
            target+="0";
        }
        for(int i = 1; i < chain.size(); i++ ){
            block = chain.get(i);
            prev = chain.get(i-1);
            
            if(!block.getHash().equals(Hash.getHash(block))){
                return false;
            }

            if(!prev.getHash().equals(Hash.getHash(prev)))
                return false;

            if(!block.getHash().substring(0,DIFFICULTY).equals(target)){
                return false;
            }
        }

        return true;
    }


    public ArrayList<Block> getBlockChain(){
        return chain;
    }

    public String getLastHash(){
        return chain.get(getLastIndex()).getHash();
    }

    public int getLastIndex(){
        return chain.size()-1;
    }

    public void printBlockChain(){
        String blockJson = new GsonBuilder().setPrettyPrinting().create().toJson(chain);
        //System.out.println(genesis);
        System.out.println("\n-----Blockchain-----");
        System.out.println(blockJson+"\n");
    }


    public void printBlock(int index){
        Block b = chain.get(index);

        GsonBuilder gson = new GsonBuilder();
        String print = gson.setPrettyPrinting().create().toJson(b);
        System.out.println("\n-----Block number " + chain.size() + "-----");
        System.out.println(print+"\n");
    }
}