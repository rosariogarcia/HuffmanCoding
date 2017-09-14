package com.minions.huffmancoding.HuffmanCodingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HuffmanCodingGUI{

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

    public HuffmanCodingGUI() {
        btnChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(filter);
                //chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
                chooser.setMultiSelectionEnabled(true);
// Show the dialog; wait until dialog is closed
                chooser.showOpenDialog(null);

// Retrieve the selected files.
                File[] files = chooser.getSelectedFiles();
            }
        });
    }
}
