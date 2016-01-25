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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PromptWindow implements ActionListener {
	JFrame frame;
	JPanel panel;
	JTextField speedSet;
	JTextArea textPaste;
	JLabel speedInfo;
	JLabel paste;
	JButton run;
	FileDialog fd;
	File file;
	FileReader fr;
	ArrayList<String> words;
	ArrayList<String> wordsPaste;
	DisplayWindow window;
	DisplayWindow pasteWindow;
	int speed;
	boolean input;
	
	public static void main(String[] args) {
		PromptWindow r = new PromptWindow();
	}

	public PromptWindow() {
			//Speed and GUI
				frame = new JFrame("Set your speed");
				panel = new JPanel();
				speedInfo = new JLabel();
				speedSet = new JTextField(5);
				paste = new JLabel();
				textPaste = new JTextArea();
			
				frame.setVisible(true);
				
				panel.setLayout(null);	
				
				speedInfo.setBounds(10, 10, 350, 30);
				speedSet.setBounds(360, 10, 80, 30);
				paste.setBounds(10, 50, 500, 30);
				textPaste.setBounds(320, 55, 470, 600);
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
			
				frame.add(panel);
				panel.add(speedInfo);
				panel.add(speedSet);
				panel.add(paste);
				panel.add(textPaste);
			
				speedInfo.setText("Please set the word display speed in words per minute:");
				paste.setText("Or input desired text here if no file was selected: ");
				
				textPaste.setColumns(2);
				textPaste.setRows(100);
			
			//JOptionPane
				int result = JOptionPane.showConfirmDialog(null, "Do you want to display text from a file?", null, JOptionPane.YES_NO_OPTION);
			
			//Run Button
				run = new JButton("Run");
				run.setBounds(100, 160, 80, 30);
				panel.add(run);
				frame.setSize(800, 800);
				
				run.addActionListener(this);	
				
			//TextPaste or File		
				if(result == JOptionPane.YES_OPTION)
				{
					//FileDialog
						fd = new FileDialog(frame, "Test", FileDialog.LOAD);
				        fd.setVisible(true);
				        System.out.println(fd.getFile());
				        String address = fd.getDirectory() + fd.getFile();
				        file = new File(address);
			            
			        //File Reader
						String text = FileReader.setFile(file);
						System.out.println(text);
					
					//Document Analyzer and Splitter for FileReader
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
						
						input = true;
				}
				else if(result == JOptionPane.NO_OPTION)
				{
					//Text Paste
						String paste = textPaste.getText();
						
						String disPaste = "";
						wordsPaste = new ArrayList<String>();
						
						for (int i = 0; i < paste.length(); i++) {
							if(paste.charAt(i) == ' '||i == paste.length() - 1) {
								wordsPaste.add(disPaste);
								
								disPaste = "";
							}
							else {
								disPaste += paste.charAt(i);
								System.out.println(paste.charAt(i));
							}	
						}
						
						input = false;
				}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == run) {
			speed = Integer.parseInt(speedSet.getText());
			
			if(input == true)
			{
				window = new DisplayWindow(words, speed);
			}
			else if(input == false)
			{
				pasteWindow = new DisplayWindow(wordsPaste, speed);
			}

			
			if(speedSet.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please set a speed for your text display.");
		}
	}
}