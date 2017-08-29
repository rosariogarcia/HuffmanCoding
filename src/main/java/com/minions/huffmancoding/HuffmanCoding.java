package com.minions.huffmancoding;

import javax.swing.*;
import com.minions.huffmancoding.HuffmanCodingGUI.HuffmanCodingGUI;

/**
 * Created by Charito on 8/22/2017.
 */
public class HuffmanCoding {
        public static void main(String ags[]) {
            JFrame frame = new JFrame("HuffmanCodingGUI");
            frame.setContentPane(new HuffmanCodingGUI().JPanelHuffmanCoding);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
}
