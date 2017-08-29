package com.minions.huffmancoding.Algorithm;

/**
 * Created by Charito on 8/23/2017.
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    
    public String symbol;
    public Integer frequency;
    public String code;
    public HuffmanNode parentNode;
    public HuffmanNode leftTree;
    public HuffmanNode rightTree;
    public boolean isLeaf;

    public HuffmanNode(String symbol, Integer frequency){
        this.symbol = symbol;
        this.frequency = frequency;
        rightTree = leftTree = parentNode = null;
        code = "";
        isLeaf = true;
    }

    public HuffmanNode(HuffmanNode node1, HuffmanNode node2){
        code = "";
        isLeaf = false;
        parentNode = null;

        if (node1.frequency >= node2.frequency)
        {
            rightTree = node1;
            leftTree = node2;
            rightTree.parentNode = leftTree.parentNode = this;
            symbol = node1.symbol + node2.symbol;
            frequency = node1.frequency + node2.frequency;
        }
        else if (node1.frequency < node2.frequency)
        {
            rightTree = node2;
            leftTree = node1;
            leftTree.parentNode = rightTree.parentNode = this;
            symbol = node2.symbol + node1.symbol;
            frequency = node2.frequency + node1.frequency;
        }
    }

    public int compareTo(HuffmanNode node) {
        return this.frequency.compareTo(node.frequency);
    }
}
