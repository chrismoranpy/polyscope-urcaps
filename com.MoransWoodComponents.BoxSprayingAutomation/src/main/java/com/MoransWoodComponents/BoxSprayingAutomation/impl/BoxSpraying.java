package com.MoransWoodComponents.BoxSprayingAutomation.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class BoxSpraying {
	
	public static void main(String[] args) {
		
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
		
		panel.setLayout(null);
		
		JLabel boxLength = new JLabel("box length");
		boxLength.setBounds(10, 25, 80, 25);
		panel.add(boxLength);
		
		JLabel boxWidth = new JLabel("box width");
		boxWidth.setBounds(10, 50, 80, 25);
		panel.add(boxWidth);
		
		JLabel boxHeight = new JLabel("box height");
		boxHeight.setBounds(10, 75, 80, 25);
		panel.add(boxHeight);
		
		
		JTextField lengthInput = new JTextField(20);
		lengthInput.setBounds(100, 25, 165, 25);
		panel.add(lengthInput);
		
		JTextField widthInput = new JTextField(20);
		widthInput.setBounds(100, 50, 165, 25);
		panel.add(widthInput);
		
		JTextField heightInput = new JTextField(20);
		heightInput.setBounds(100, 75, 165, 25);
		panel.add(heightInput);
		
		JButton submit = new JButton("Submit");
		submit.setBounds(100, 100, 120, 25);
		panel.add(submit);
		
		
		

		
		
		
		
		
		
		
		
		
			
}
		}
	
	