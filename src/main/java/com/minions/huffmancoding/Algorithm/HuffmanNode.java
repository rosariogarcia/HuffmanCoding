package com.minions.huffmancoding.Algorithm;

/**
 * This class represent a Huffman Node
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    
    public String symbol;
    public Integer frequency;
    public String code;
    public HuffmanNode parentNode;
    public HuffmanNode leftTree;
    public HuffmanNode rightTree;
    public boolean isLeaf;

    /**
     * Class constructor
     * Initialize a new Huffman Node
     * @param symbol
     */
    public HuffmanNode(String symbol){
        this.symbol = symbol;
        this.frequency = 1;
        rightTree = leftTree = parentNode = null;
        code = "";
        isLeaf = true;
    }

    /**
     * Class Constructor
     * Join two Huffman Nodes
     * @param node1
     * @param node2
     */
    public HuffmanNode(HuffmanNode node1, HuffmanNode node2){
        code = "";
        isLeaf = false;
        parentNode = null;

        if (node1.frequency > node2.frequency)
        {
            rightTree = node1;
            leftTree = node2;
            rightTree.parentNode = leftTree.parentNode = this;
            symbol = node2.symbol + node1.symbol;
            frequency = node1.frequency + node2.frequency;
        }
        else if (node1.frequency <= node2.frequency)
        {
            rightTree = node2;
            leftTree = node1;
            leftTree.parentNode = rightTree.parentNode = this;
            symbol = node1.symbol + node2.symbol;
            frequency = node2.frequency + node1.frequency;
        }
    }

    /**
     * @see Comparable
     * @param node
     * @return
     */
    public int compareTo(HuffmanNode node) {
        return this.frequency.compareTo(node.frequency);
    }
    
    public void addFrequency(){
        frequency++;
    }
}
