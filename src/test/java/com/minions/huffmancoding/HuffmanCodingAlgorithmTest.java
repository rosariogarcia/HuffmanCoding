package com.minions.huffmancoding;

import java.util.HashMap;
import java.util.List;

import com.minions.huffmancoding.Algorithm.HuffmanCodingAlgorithm;
import com.minions.huffmancoding.Algorithm.HuffmanNode;
import com.minions.huffmancoding.Algorithm.Utils;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * Created by Charito on 8/23/2017.
 */
public class HuffmanCodingAlgorithmTest {

    static HuffmanCodingAlgorithm huffmanCodingAlgorithm;

    @BeforeClass
    public static void setUp() throws Exception {
        huffmanCodingAlgorithm = new HuffmanCodingAlgorithm();
    }

    @Test
    public void setCodeToTheTree() throws Exception {
        List<Character> input = Utils.getInput("src/test/resources/input.txt");
        HashMap<String, Integer> orderedList = huffmanCodingAlgorithm.GetOrderedList(input);
        List<HuffmanNode> huffmanNodeList = huffmanCodingAlgorithm.GetHuffmanNodeList(orderedList);
        huffmanCodingAlgorithm.getTreeFromList(huffmanNodeList);
        huffmanCodingAlgorithm.setCodeToTheTree("", huffmanNodeList.get(0));
        HashMap<String, String> leafAndCode = huffmanCodingAlgorithm.PrintfLeafAndCodes(huffmanNodeList.get(0));
        huffmanCodingAlgorithm.WriteCompressFile(input, leafAndCode);
    }
}