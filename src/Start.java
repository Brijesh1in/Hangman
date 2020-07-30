import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Start implements ActionListener{

	public JFrame frame;
	private static Player player;
	private int chancesLeft;
	final String keyBoard;
	final String word;
	final int correctAnsScore;
	private StringBuilder guess;
	private int score;
	private String difficulty;
	private JLabel curScoreLable;
	private JLabel chanceLabel;
	private JTextField field[];
	private ImageHandler imageHandler;
	JLabel hangmanImage;
	Start(Player player , String difficulty){

		this.player = player;
		this.chancesLeft = 10;
		this.difficulty = difficulty;
		this.keyBoard = "QWERTYUIOPASDFGHJKLZXCVBNM";
		if(difficulty=="Hard")
			this.correctAnsScore = 5;
		else this.correctAnsScore = 3;
		this.word = new Word(difficulty).getWord().toUpperCase();
		this.guess = new StringBuilder(word.length());
		for(int i = 0 ; i < word.length() ; ++i)
			guess.append('?');
		this.score = 0;
		imageHandler = new ImageHandler();
	}

	public void initialize(){

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(0 , 0 , 950 , 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		hangmanImage = new JLabel();
		hangmanImage.setIcon(new ImageIcon("../Images/hangman.jpg"));
		hangmanImage.setBounds(10 , 0 , 160 , 137);
		frame.getContentPane().add(hangmanImage);

		JLabel chancesLabel = new JLabel("Chances : ");
		chancesLabel.setBounds(700 , 80, 100 , 40);
		chancesLabel.setFont(new Font("Serif" , Font.BOLD , 18));
		chancesLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(chancesLabel);

		chanceLabel = new JLabel(Integer.toString(this.chancesLeft));
		chanceLabel.setBounds(780 , 80 , 50 , 40);
		chanceLabel.setFont(new Font("Serif" , Font.BOLD , 18));
		chanceLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(chanceLabel);

		JLabel scoreLabel  = new JLabel("Score : ");
		scoreLabel.setBounds(700 , 40 , 100 , 40);
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setFont(new Font("Serif" , Font.BOLD , 18));
		frame.getContentPane().add(scoreLabel);

		curScoreLable = new JLabel(Integer.toString(this.score));
		curScoreLable.setBounds(780 , 40 , 50 , 40);
		curScoreLable.setFont(new Font("Serif" , Font.BOLD , 18));
		curScoreLable.setForeground(Color.WHITE);
		frame.getContentPane().add(curScoreLable);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(400 , 500 , 100 , 50);
		btnExit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){

				LeaderBoard.updateScore(player ,score);
				System.out.println("Hell");
				frame.dispose();
			}

		});
		frame.getContentPane().add(btnExit);

		field = new JTextField[word.length()];
		for(int i = 0 , start = 150 ; i < word.length() ; ++i){

			KeyField keyField = new KeyField(start , 170 , 40, 40);
			frame.getContentPane().add(keyField.getField());
			field[i] = keyField.getField();
			start += 60;
		}
		for(int i = 0 , X = 140 , Y = 300 ; i < keyBoard.length() ; ++i){
			char c = keyBoard.charAt(i);
			JButton btn = new Button(c , X , Y).getButton();
			btn.addActionListener(this);
			//btns.add(btn);
			frame.getContentPane().add(btn);
			X += 60;
			if(c=='P'){
				Y += 60; 
				X = 170;
			}
			if(c=='L'){
				Y += 60; 
				X = 220;
			}
		}
        JLabel backGroundImage = new JLabel();
		backGroundImage.setIcon(new ImageIcon("../Images/background.jpg"));
		backGroundImage.setBounds(0 , 0 , 950 , 630);
		frame.getContentPane().add(backGroundImage);
	}

	public void actionPerformed(ActionEvent evnt){

		JButton btnPressed = (JButton)evnt.getSource();
		String key = btnPressed.getActionCommand();
		boolean found = false;
		for(int i = 0 ; i < word.length() ; ++i){

			if(word.charAt(i)==key.charAt(0)){

				guess.setCharAt(i,word.charAt(i));
				field[i].setText(Character.toString(word.charAt(i)));
				found = true;

			}
		}
		if(!found){

			this.chancesLeft--;
			hangmanImage.setIcon(imageHandler.getNextImage());
			chanceLabel.setText(Integer.toString(this.chancesLeft));
			checkLost();
		}
		checkWin();
		btnPressed.setEnabled(false);
	}

	public void checkWin(){

		if(this.guess.toString().equals(this.word)){

			JOptionPane.showMessageDialog(null ,"You won!");

			Start start = new Start(this.player , this.difficulty);
			start.score = this.score + this.correctAnsScore;
			start.initialize();
			start.frame.setVisible(true);
			frame.dispose();
		}
	}

	public void checkLost(){

		if(this.chancesLeft==0){

			JOptionPane.showMessageDialog(null , "You Lost!\n The correct word is " + this.word);
			LeaderBoard.updateScore(this.player , this.score);
			Play play = new Play();
			play.frame.setVisible(true);
			frame.dispose();
		}
	}

	public static void main(String[] args){

		player = new Player("Brijesh");
		Start start = new Start(player , "Hard");
		start.initialize();
		start.frame.setVisible(true);
	}

}