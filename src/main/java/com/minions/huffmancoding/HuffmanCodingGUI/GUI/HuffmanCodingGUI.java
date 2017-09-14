package com.minions.huffmancoding.HuffmanCodingGUI.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.minions.huffmancoding.Algorithm.HuffmanCodingAlgorithm;
import com.minions.huffmancoding.Algorithm.HuffmanNode;
import com.minions.huffmancoding.Algorithm.HuffmanNode.*;

import static javax.swing.JOptionPane.*;

public class HuffmanCodingGUI extends JFrame{

    static HuffmanCodingAlgorithm huffmanCodingAlgorithm;
    public JPanel jpHuffmanCoding;
    private JButton btnChooser;
    private JPanel ActionButton;
    private JPanel SelectFile;
    private JButton btnCompress;
    private JButton btnDecompress;
    private JLabel lbSelectedFiles;
    private JTextField textField1;
    private JTextArea txtSelectedFiles;
    private JPanel jpProcess;
    private JPanel jpFunctions;
    private JTextPane tpProcess;
    private File[] files;

    public HuffmanCodingGUI()  {

        this.setContentPane(jpHuffmanCoding);
        this.setLocationByPlatform(true);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setSize(500,250);
        this.setVisible(true);

        Border border = BorderFactory.createLineBorder(new Color(241,160,46), 2);

        jpFunctions.setSize(200,250);
        jpProcess.setSize(250,250);
        jpProcess.setBorder(border);


        ImageIcon selectFilesIcon = new ImageIcon(new ImageIcon("src/main/java/com/minions/huffmancoding/HuffmanCodingGUI/Images/txt.png").getImage().getScaledInstance(50,70, Image.SCALE_DEFAULT));
        ImageIcon compressIcon = new ImageIcon(new ImageIcon("src/main/java/com/minions/huffmancoding/HuffmanCodingGUI/Images/compressfile.png").getImage().getScaledInstance(50,70, Image.SCALE_DEFAULT));
        ImageIcon decompressIcon = new ImageIcon(new ImageIcon("src/main/java/com/minions/huffmancoding/HuffmanCodingGUI/Images/decompresfile.png").getImage().getScaledInstance(50,70, Image.SCALE_DEFAULT));

        btnChooser.setBorder(null);
        btnChooser.setIcon(selectFilesIcon);

        btnCompress.setBorder(null);
        btnCompress.setIcon(compressIcon);

        btnDecompress.setBorder(null);
        btnDecompress.setIcon(decompressIcon);

        btnChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tpProcess.setText("No Files compressed");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(filter);
                chooser.setMultiSelectionEnabled(true);
                chooser.showOpenDialog(null);
                files = chooser.getSelectedFiles();

                    StringBuilder filesList = new StringBuilder();
                    for (File file:files) {
                        filesList.append(file.getName());
                        filesList.append("\n");
                    }
                    txtSelectedFiles.setText(filesList.toString());
            }
        });

        btnCompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (files==null){
                    showAlertMessage("No Files Selected, Please select at least one .txt file to compress");
                    tpProcess.setText("");
                    txtSelectedFiles.setText("No files selected!");
                }else{
                    List<HuffmanNode> huffmanNodeList = new ArrayList<>();
                    huffmanCodingAlgorithm = new HuffmanCodingAlgorithm();
                    huffmanNodeList = huffmanCodingAlgorithm.solver(files);

                    StringBuilder process = new StringBuilder();
                    for (HuffmanNode huffmanNode: huffmanNodeList){
//                        process.append(huffmanCodingAlgorithm.PrintfLeafAndCodes(huffmanNode));
//                        process.append("\n");
                        process.append(printSymbolCode(huffmanCodingAlgorithm.PrintfLeafAndCodes(huffmanNode)));
                        process.append("\n");
                    }

                    tpProcess.setText(process.toString());

                    files = null;
                    txtSelectedFiles.setText("File Compressed!");
                }
            }

            private String printSymbolCode(HashMap<String, String> huffmanCode) {
                StringJoiner codes = new StringJoiner("\n");
                for(String symbol : huffmanCode.keySet()){
                    codes.add("Symbol: "+symbol+" - Code: "+huffmanCode.get(symbol));
                }
                return codes.toString();
            }
        });
    }

    private void showAlertMessage(String message){
        showMessageDialog(null, message);
    }
}
