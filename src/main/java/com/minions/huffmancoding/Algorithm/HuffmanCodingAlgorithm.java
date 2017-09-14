package com.minions.huffmancoding.Algorithm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static com.minions.huffmancoding.Algorithm.Utils.getIndexHuffmanNode;
import static com.minions.huffmancoding.Algorithm.Utils.getInput;

/**
 * This class represent the Huffman Coding Algorithm
 */
public class HuffmanCodingAlgorithm {

    private HashMap<String, String> leafAndCode = new HashMap<>();

    /**
     * Get ordered list from real string content file
     * @param inputData
     * @return
     * @throws IOException
     */
    public List<HuffmanNode> GetOrderedList(List<String> inputData) throws IOException {
        List<HuffmanNode> orderedList = new ArrayList<>();
        for (String symbol : inputData) {
            if (Utils.containsSymbol(orderedList, symbol)){
                orderedList.get(getIndexHuffmanNode(orderedList, symbol)).addFrequency();
            }else{
            orderedList.add(new HuffmanNode(symbol));}
        }
        orderedList.sort(HuffmanNode::compareTo);
        return orderedList;
    }

    /**
     * Create a Tree from node list
     * @param nodeList
     */
    public HuffmanNode buildTreeFromOrderedList(List<HuffmanNode> nodeList){
        while (nodeList.size() > 1) {
            HuffmanNode node1 = nodeList.get(0);
            nodeList.remove(0);
            HuffmanNode node2 = nodeList.get(0);
            nodeList.remove(0);
            nodeList.add(new HuffmanNode(node1, node2));
            Collections.sort(nodeList);
        }
		return nodeList.get(0);
    }

    /**
     * Find the Code of symbol from Tree
     * @param code
     * @param node
     */
    public void setCodeToTheTree(String code, HuffmanNode node){
        if (node == null)
            return;
        if (node.leftTree == null && node.rightTree == null)
        {
            node.code = code;
            return;
        }
        setCodeToTheTree(code + "0", node.leftTree);
        setCodeToTheTree(code + "1", node.rightTree);
    }

    /**
     * Printing the symbols and codes of the Nodes on the tree.
     * @param nodeList List of Nodes
     */
    public HashMap<String, String> PrintfLeafAndCodes(HuffmanNode nodeList) {
        if (nodeList == null)
            return null;
        if (nodeList.leftTree == null && nodeList.rightTree == null)
        {
            //System.out.printf("Symbol : %s -  Code : %s %n", nodeList.symbol, nodeList.code);
            leafAndCode.put(nodeList.symbol, nodeList.code);
        }
        PrintfLeafAndCodes(nodeList.leftTree);
        PrintfLeafAndCodes(nodeList.rightTree);
        return leafAndCode;
    }

    /**
     * Write Compress file
     * @param inputText List of real content file
     * @param huffmanCode Map of codes by symbol
     * @param fileName Name of file to create
     * @throws IOException
     */
    public void writeCompressFile(List<String> inputText, HashMap<String, String> huffmanCode, String fileName) throws IOException {
        //This list contains the code assigned to symbol, represents the encoded data
        List<String> encodedList = inputText.stream().map(huffmanCode::get).collect(Collectors.toList());
        String encodedText = String.join("", encodedList);

        if (encodedText.length() % 8 != 0){
            encodedText = encodedText + addLength(encodedText);
        }

        System.out.println(encodedText);

        BitSet bitSetEncoded = bitSetFromString(encodedText);
        byte[] byteArray = bitSetEncoded.toByteArray();
        int encodedTextSize = byteArray.length;

        byte[] byteHeader = getHeaderFile(huffmanCode);
        int headerSize = byteHeader.length;

        String headerLength= String.format("%05d", byteHeader.length);
        byte[] byteLength= headerLength.getBytes();
        int lengthHeader = byteLength.length;

        //Write Compressed File
        int totalSize=lengthHeader+headerSize+encodedTextSize;
        byte[] arrayWrite= new byte[totalSize];
        int j=0;
        for(int i=0;i<lengthHeader;i++,j++){
            arrayWrite[j]=byteLength[i];
        }
        for(int i=0;i<headerSize;i++,j++){
            arrayWrite[j]=byteHeader[i];
        }
        for(int i=0;i<encodedTextSize;i++,j++){
            arrayWrite[j]=byteArray[i];
        }

        FileOutputStream outputFile = new FileOutputStream(fileName);
        outputFile.write(arrayWrite);
        outputFile.close();
    }

    public void uncompressFile(Path nameFile) throws IOException {
        byte[] data = Files.readAllBytes(nameFile);
        int tamLongitud = 5;

        byte[] codeLongitud = new byte[tamLongitud];
        System.arraycopy(data,0,codeLongitud,0,codeLongitud.length);
        String strLongitud = new String(codeLongitud, StandardCharsets.UTF_8);

        byte[] codesByte = new byte[Integer.parseInt(strLongitud)];
        System.arraycopy(data,tamLongitud,codesByte,0,codesByte.length);
        byte[] headerFilePrueba = Utils.getByteArray(new Header("texto"));
        String strRespuesta = new String(headerFilePrueba, StandardCharsets.UTF_8);

        byte[] codesSequence = new byte[data.length-codesByte.length-codeLongitud.length];
        int sss = codesByte.length+codeLongitud.length;
        System.arraycopy(data,sss,codesSequence,0,codesSequence.length);

        BitSet bytetoBitSet=BitSet.valueOf(codesSequence);
        String secuenciaABuscar= convert(bytetoBitSet);
    }

    public static String convert(BitSet bits) {
        String cadena = "";
        for (int i = 0; i < bits.length(); ++i) {
            cadena+=(bits.get(i)?1:0);
        }
        return  cadena;
    }

    /**
     * Convert huffman code hashmap to String to add in the header of file and get the array bytes
     * @param huffmanCode
     * @return byte[] of string that contains the symbols with codes
     */
    private byte[] getHeaderFile(HashMap<String, String> huffmanCode) {
        StringJoiner codes = new StringJoiner(",");
        for(String symbol : huffmanCode.keySet()){
            codes.add(symbol+"="+huffmanCode.get(symbol));
        }
        return bitSetFromString(codes.toString()).toByteArray();
    }

    /**
     * Create the bits to complete the stream
     * @param text
     * @return
     */
    private String addLength(String text) {
        String lengthToAdd = "";
        for(int i = 0 ; i<text.length()%8; i++){
            lengthToAdd += "1";
        }
        return lengthToAdd;
    }

    public static void getBitSet(BitSet bits) {
        for (int i = 0; i < bits.length(); ++i) {
            System.out.print(bits.get(i)?1:0);
        }
    }

    /**
     * Convert the binary string to bit set
     * @param binaryString
     * @return Bit set
     */
    private static BitSet bitSetFromString(String binaryString) {
        BitSet bitset = new BitSet(binaryString.length());
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                bitset.set(i);
            }
        }
        return bitset;
    }

    /**
     * This method is used to call all needed methods to compress files
     * @param files Files list selected from GUI
     */
    public List<HuffmanNode> solver(File[] files) {
        List<HuffmanNode> huffmanNodeList = new ArrayList<>();
        for (File file: files) {
            try {
                List<String> inputText = getInput(file.getPath());
                List<HuffmanNode> orderedList = GetOrderedList(inputText);
                HuffmanNode rootHuffmanTree = buildTreeFromOrderedList(orderedList);
                setCodeToTheTree("", orderedList.get(0));
                HashMap<String, String> leafAndCode = PrintfLeafAndCodes(orderedList.get(0));
                writeCompressFile(inputText, leafAndCode, file.getName());
                huffmanNodeList.add(rootHuffmanTree);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return huffmanNodeList;
    }
}
