import java.util.*;

public class Dictionary{

	private String word;
	private int frequency;

	Dictionary(String word , int frequency){

		this.word = word;
		this.frequency = frequency;
	}

	public String getWord(){
		return word;
	}

	public int getFrequency(){

		return frequency;
	}
}