import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Play{

	JFrame frame;
	final String[] TYPE = {"Easy" , "Hard"};
	Play(){

		initialize();
	}

	private void initialize(){

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(0 , 0 , 1000 , 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel enterUserName = new JLabel("Enter Username : ");
		enterUserName.setBounds(200 , 200 , 150 , 40);
		enterUserName.setFont(new Font("Serif" , Font.BOLD , 15));
		enterUserName.setForeground(Color.WHITE);
		frame.getContentPane().add(enterUserName);

		JTextField userName = new JTextField();
		userName.setBounds(350 , 200 , 200 , 40);
		frame.getContentPane().add(userName);

		JLabel difficultyLevel = new JLabel("Difficulty Level : ");
		difficultyLevel.setBounds(200 , 300 , 150 , 40);
		difficultyLevel.setFont(new Font("Serif" , Font.BOLD , 15));
		difficultyLevel.setForeground(Color.WHITE);
		frame.getContentPane().add(difficultyLevel);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(TYPE));
		comboBox.setBounds(350, 293, 310, 50);
		comboBox.setSelectedIndex(0);
		frame.getContentPane().add(comboBox);

		JButton btnStart = new JButton("Start");
		btnStart.setBounds(450 , 400 , 100 , 50);
		frame.getContentPane().add(btnStart);

		btnStart.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){

				String name = userName.getText();
				if(name.length() == 0){

					JOptionPane.showMessageDialog(null ,"Username can't be empty .");
				}
				else{

					Player player = new Player(name);
					String difficulty = (String)comboBox.getSelectedItem();
					Start start = new Start(player , difficulty);
					start.initialize();
					start.frame.setVisible(true);
					frame.dispose();
				}
			}

		});

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(450 , 500 , 100 , 50);
		btnExit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){

				frame.dispose();
			}
		});
		frame.getContentPane().add(btnExit);

		JLabel background = new JLabel();
		background.setIcon(new ImageIcon("../Images/background.jpg"));
		background.setBounds(0 , 0 , 1000 , 650);
		frame.getContentPane().add(background);
		
	}

	public static void main(String[] args){

		Play play = new Play();
		play.frame.setVisible(true);
	}
}