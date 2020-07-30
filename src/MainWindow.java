import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class MainWindow{

	private JFrame frame;

	MainWindow(){

		initialize();
	}

	void initialize(){

		frame = new JFrame();
		frame.setBounds(0 , 0 , 1000 , 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(800 , 100 , 100 , 50);
		btnPlay.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){

				Play play = new Play();
				play.frame.setVisible(true);
				frame.dispose();
			}

		});
		frame.getContentPane().add(btnPlay);

		JButton btnLeaderBoard = new JButton("Leaderboard");
		btnLeaderBoard.setBounds(800 , 200 , 100 , 50);
		btnLeaderBoard.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){

				LeaderBoard leaderboard = new LeaderBoard();
				leaderboard.initialize();
				leaderboard.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(btnLeaderBoard);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(800 , 300 , 100 , 50);
		frame.getContentPane().add(btnExit);
		btnExit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){

				frame.dispose();
			}
		});

		JLabel sideWall = new JLabel();
		sideWall.setIcon(new ImageIcon("../Images/hangmanMain.jpg"));
		sideWall.setBounds(100 , 50 , 600 , 500);
		frame.getContentPane().add(sideWall);

		JLabel background = new JLabel();
		background.setIcon(new ImageIcon("../Images/background.jpg"));
		background.setBounds(0 , 0 , 1000 , 650);
		frame.getContentPane().add(background);

	}

	public static void main(String[] args){

		MainWindow mainWindow = new MainWindow();
		mainWindow.frame.setVisible(true);
	}
}