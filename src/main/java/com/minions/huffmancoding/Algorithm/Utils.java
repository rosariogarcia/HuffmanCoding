package com.minions.huffmancoding.Algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains util methods
 */
public class Utils {

    public static boolean containsSymbol(List<HuffmanNode> huffmanNodeList, String symbol) {
        for(HuffmanNode o : huffmanNodeList) {
            if(o != null && o.symbol.equals(symbol)) {
                return true;
            }
        }
        return false;
    }
    
    public static int getIndexHuffmanNode(List<HuffmanNode> huffmanNodeList, String symbol){
        for(int i= 0; i< huffmanNodeList.size(); i++) {
            if(huffmanNodeList.get(i).symbol.equals(symbol)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get Character List from file to compress
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<String> getInput(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        Integer frequencyDefault = 1;
        String strLine;
        List<String> orderedList = new ArrayList<String>();
        while ((strLine = bufferedReader.readLine()) != null) {
            for (char c : strLine.toCharArray()) {
                orderedList.add(String.valueOf(c));
            }
        }
        fileInputStream.close();
        return orderedList;
    }

    public static byte[] getByteArray(Header header) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(header);
            out.flush();;
            byte[] yourBytes = bos.toByteArray();
            return yourBytes;
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }
}
