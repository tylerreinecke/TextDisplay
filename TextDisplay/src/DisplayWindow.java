import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class DisplayWindow implements ActionListener{
	JFrame disframe;
	JPanel dispanel;
	ArrayList<String> wordList;
	Timer timer;
	JLabel currentWord;
	int counter = 1;
	
	public DisplayWindow(ArrayList<String> x, int y)
	{
		wordList = x;
		disframe = new JFrame();
		disframe.setVisible(true);
		disframe.setSize(400, 400);
		dispanel = new JPanel();
		
		disframe.add(dispanel);
		
		currentWord = new JLabel(wordList.get(0));
		dispanel.setLayout(null);
		dispanel.add(currentWord);
		currentWord.setBounds(190, 0, 400, 400);
		
		timer = new Timer((1000/y) * 60, this);
		timer.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(counter < wordList.size())
		{
			currentWord.setText(wordList.get(counter++));
		}
		else
		{
			System.exit(0);
		}
	}
}
