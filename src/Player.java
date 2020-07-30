import java.util.*;

public class Player{

	private String playerName;
	private int score;

	Player(String name){
		this.playerName = name;
		this.score = 0;
	}
	public String getPlayerName(){

		return this.playerName;
	}
	public void setPlayerName(String name){

		this.playerName = name;
	}
	public void addScore(int score){

		this.score += score;
	}
}