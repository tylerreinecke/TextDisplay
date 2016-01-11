import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PromptWindow implements ActionListener {
	JFrame frame;
	JPanel panel;
	JTextField speedSet;
	JLabel speedInfo;
	JButton run;
	//JFileChooser doc;
	FileDialog fd;
	File file;
	FileReader fr;
	ArrayList<String> words;
	DisplayWindow window;
	int speed;
	
	public static void main(String[] args) {
		PromptWindow r = new PromptWindow();
	}

	public PromptWindow() {
		//Speed and GUI
			frame = new JFrame("Set your speed");
			panel = new JPanel();
			speedSet = new JTextField(5);
			speedInfo = new JLabel();
		
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
			frame.add(panel);
			panel.add(speedInfo);
			panel.add(speedSet);
		
			speedInfo.setText("Please set the word display speed in words per minute.");
		
		
		//Run Button
			run = new JButton("Run");
			panel.add(run);
			frame.setSize(600, 100);
			
			run.addActionListener(this);
	
			
		//File Browser
			/*
			doc = new JFileChooser();
			panel.add(doc);
		
			doc.setCurrentDirectory(new java.io.File("C:/Users/Tyler/Desktop"));
			doc.setDialogTitle("Text Document Chooser");
			doc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			//((UIManager) doc).setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
			if(doc.showOpenDialog(doc) == JFileChooser.APPROVE_OPTION)
				file = doc.getSelectedFile();
		
			System.out.println("You chose: " +doc.getSelectedFile().getAbsolutePath());
			 */
			
		//FileDialog
			
			fd = new FileDialog(frame, "Test", FileDialog.LOAD);
            fd.setVisible(true);
            System.out.println(fd.getFile());
            String address = fd.getDirectory() + fd.getFile();
            file = new File(address);
			
			
		//File Reader
			String text = FileReader.setFile(file);
			System.out.println(text);
		
		//Document Analyzer and Splitter
			words = new ArrayList<String>();
			String distext = "";
				
			for (int i = 0; i < text.length(); i++) {
				if(text.charAt(i) == ' '||i == text.length() - 1) {
					words.add(distext);
					distext = "";
				}
				else {
					distext += text.charAt(i);
				}	
			}	
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == run) {
			speed = Integer.parseInt(speedSet.getText());
			
			window = new DisplayWindow(words, speed);
			
			if(speedSet.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please set a speed for your text display.");
		}
	}
}