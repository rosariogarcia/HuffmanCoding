package com.minions.huffmancoding;

import java.nio.file.Paths;
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
        List<String> inputText = Utils.getInput("src/test/resources/input.txt");
        List<HuffmanNode> orderedList = huffmanCodingAlgorithm.GetOrderedList(inputText);
        //List<HuffmanNode> huffmanNodeList = huffmanCodingAlgorithm.GetHuffmanNodeList(orderedList);
        HuffmanNode rootHuffmanTree = huffmanCodingAlgorithm.buildTreeFromOrderedList(orderedList);
        huffmanCodingAlgorithm.setCodeToTheTree("", orderedList.get(0));
        HashMap<String, String> symbolAndCode = huffmanCodingAlgorithm.PrintfLeafAndCodes(orderedList.get(0));
        huffmanCodingAlgorithm.writeCompressFile(inputText, symbolAndCode, "output.hc");
        huffmanCodingAlgorithm.uncompressFile(Paths.get("D:/me/minions/Gitlab/HuffmanCoding/output.hc"));
    }
}