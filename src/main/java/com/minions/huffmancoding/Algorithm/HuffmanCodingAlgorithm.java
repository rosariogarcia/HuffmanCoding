package com.minions.huffmancoding.Algorithm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Charito on 8/23/2017.
 */
public class HuffmanCodingAlgorithm {
    
    public HashMap<String, Integer> GetOrderedList(List<Character> inputData) throws IOException {
        Integer frequencyDefault = 1;
        HashMap<String, Integer> orderedList = new HashMap<String, Integer>();
        for (char c : inputData) {
            orderedList.put(String.valueOf(c), orderedList.containsKey(String.valueOf(c)) ? orderedList.get(String.valueOf(c)) + frequencyDefault : frequencyDefault);
        }
        orderedList = (HashMap<String, Integer>) Utils.sortByValue(orderedList);
        return orderedList;
    }

    public List<HuffmanNode> GetHuffmanNodeList(HashMap<String, Integer> orderedList){
        List<HuffmanNode> nodeList = new ArrayList<>();
        for (String symbol: orderedList.keySet()) {
            nodeList.add(new HuffmanNode(symbol, orderedList.get(symbol)));
        }
        return nodeList;
    }

    public void getTreeFromList(List<HuffmanNode> nodeList)
    {
        while (nodeList.size() > 1) {
            HuffmanNode node1 = nodeList.get(0);
            nodeList.remove(0);
            HuffmanNode node2 = nodeList.get(0);
            nodeList.remove(0);
            nodeList.add(new HuffmanNode(node1, node2));
            Collections.sort(nodeList);
        }
    }

    public void setCodeToTheTree(String code, HuffmanNode Nodes)
    {
        if (Nodes == null)
            return;
        if (Nodes.leftTree == null && Nodes.rightTree == null)
        {
            Nodes.code = code;
            return;
        }
        setCodeToTheTree(code + "0", Nodes.leftTree);
        setCodeToTheTree(code + "1", Nodes.rightTree);
    }

    public void PrintTree(int level, HuffmanNode node)
    {
        if (node == null)
            return;
        for (int i = 0; i < level; i++)
        {
            System.out.println("\t");
        }
        System.out.println("[" + node.symbol + "]");
        System.out.println("(" + node.code + ")");
        PrintTree(level + 1, node.rightTree);
        PrintTree(level + 1, node.leftTree);
    }
    HashMap<String, String> leafAndCode = new HashMap<String, String>();
    // Printing the symbols and codes of the Nodes on the tree.
    public HashMap<String, String> PrintfLeafAndCodes(HuffmanNode nodeList)
    {
        if (nodeList == null)
            return null;
        if (nodeList.leftTree == null && nodeList.rightTree == null)
        {
            System.out.printf("Symbol : %s -  Code : %s %n", nodeList.symbol, nodeList.code);
            leafAndCode.put(nodeList.symbol, nodeList.code);
            //return null;
        }
        PrintfLeafAndCodes(nodeList.leftTree);
        PrintfLeafAndCodes(nodeList.rightTree);
        return leafAndCode;
    }

    public List<String> WriteCompressFile(List<Character> realList, HashMap<String, String> huffmanCode) throws IOException {

        List<String> encodedList = new ArrayList<String>();
        for (Character c: realList) {
            encodedList.add(huffmanCode.get(String.valueOf(c)));
        }
        Path path = Paths.get("src/test/resources/output.txt");
        Files.write(path, encodedList, StandardCharsets.UTF_8);
        return encodedList;
    }
}
