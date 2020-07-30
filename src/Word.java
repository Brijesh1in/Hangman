import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Word{

	private String word;
	private int guessesRequired;
	final int totWords = 39635;
	Word(String difficulty){

		if(difficulty=="Hard"){

			while(guessesRequired <= 21)
				setWord();
		}
		else{
			guessesRequired = 30;
			while(guessesRequired > 21)
				setWord();
		}
	}

	private void setWord(){

		Random rand = new Random();
		int x = rand.nextInt(39000);
		try{

			FileInputStream file = new FileInputStream("../Files/dict.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(file));

			for(int i = 0 ; i < x ; ++i)
				reader.readLine();
			String line = reader.readLine();
			String data[] = line.split(",");
			guessesRequired = Integer.parseInt(data[1]);
			this.word = data[0];
		}catch(Exception e){

			//JOptionPane.showMessageDialog(null , "Something went wrong in class Word");
			System.out.println(e);
			//System.exit();
		}
	}

	public String getWord(){
		return word;
	}
	public int getLength(){
		return word.length();
	}

	public static void main(String[] args){

		Word word = new Word("Easy");
		System.out.println(word.getWord());
	}

}