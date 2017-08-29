package com.minions.huffmancoding.HuffmanCodingGUI;

import com.minions.huffmancoding.Algorithm.HuffmanCodingAlgorithm;
import com.minions.huffmancoding.Algorithm.HuffmanNode;
import com.minions.huffmancoding.Algorithm.Utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import static com.minions.huffmancoding.Algorithm.Utils.getInput;

public class HuffmanCodingGUI{

    static HuffmanCodingAlgorithm huffmanCodingAlgorithm;
    public JPanel JPanelHuffmanCoding;
    private JButton btnChooser;
    private JTextField txtFiles;
    private JPanel ActionButton;
    private JPanel SelectedFiles;
    private JPanel SelectFile;
    private JButton btnCompress;
    private JButton btnDescompress;
    private JTextPane txtProcess;
    private JPanel Process;
    private File[] files;
    public HuffmanCodingGUI() {

        btnChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(filter);
                chooser.setMultiSelectionEnabled(true);
                chooser.showOpenDialog(null);
                files = chooser.getSelectedFiles();
            }
        });

        btnCompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solver();
            }
        });
    }

    private void solver() {

        if(files.length == 0){
            System.out.println("Any File was selected");
        }
        if(files.length>1){
            for (File file: files) {
                try {
                    List<Character> input = getInput(file.getPath());
                    HashMap<String, Integer> orderedList;
                    orderedList = huffmanCodingAlgorithm.GetOrderedList(input);
                    List<HuffmanNode> huffmanNodeList = huffmanCodingAlgorithm.GetHuffmanNodeList(orderedList);
                    huffmanCodingAlgorithm.getTreeFromList(huffmanNodeList);
                    huffmanCodingAlgorithm.setCodeToTheTree("", huffmanNodeList.get(0));
                    HashMap<String, String> leafAndCode = huffmanCodingAlgorithm.PrintfLeafAndCodes(huffmanNodeList.get(0));
                    huffmanCodingAlgorithm.WriteCompressFile(input, leafAndCode);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
