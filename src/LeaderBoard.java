import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class Pair<first , second>{

	private first x;
	private second y;

	Pair(first x , second y){

		this.x = x;
		this.y = y;
	}

	public first getFirst(){

		return this.x;
	}
	public second getSecond(){
		return this.y;
	}

	public void setFirst(first x){

		this.x = x;
	}
	public void setSecond(second y){

		this.y = y;
	}

}

class SortByScore implements Comparator<Pair>{

	public int compare(Pair a , Pair b){

		int x = (int)a.getFirst();
		int y = (int)b.getFirst();
		return y - x;
	}
}

public class LeaderBoard{

	public JFrame frame;
	private JTable table;
	LeaderBoard(){
 
	}

	public void initialize(){

		java.util.List<Pair<Integer , String>> list = new ArrayList<Pair<Integer , String>>();

		try{

			FileInputStream file = new FileInputStream("../Files/leaderboard.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(file));

			String line;
			line = reader.readLine();
			while(line!=null){

				String data[] = line.split(",");
				list.add(new Pair(Integer.parseInt(data[1]) , data[0]));
				line = reader.readLine();
			}

			file.close();
			reader.close();
		}catch(Exception e){

			System.out.println(e);
		}

		frame = new JFrame();
		frame.setBounds(0 , 0 , 1000 , 650);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblLeaderboard = new JLabel("LeaderBoard");
		lblLeaderboard.setBounds(228 , 11 , 135 ,34);
		frame.getContentPane().add(lblLeaderboard);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49 ,74 , 495 , 247);
		frame.getContentPane().add(scrollPane);

		String[] head = {"Name" , "Score"};
		String content[][] = new String[list.size()][2];
		Collections.sort(list , new SortByScore());
		for(int i = 0 ; i < list.size() ; ++i){

			content[i][0] = list.get(i).getSecond();
			content[i][1] = Integer.toString(list.get(i).getFirst());
		}
		table = new JTable(content , head);
		scrollPane.setViewportView(table);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(245, 332, 89, 23);
		frame.getContentPane().add(btnExit);
		btnExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

	public static void updateScore(Player player , int score){

		String name = player.getPlayerName();
		java.util.List<String> list = new ArrayList<String>();
		try{

			FileInputStream file = new FileInputStream("../Files/leaderboard.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(file));
			String line = reader.readLine();
			while(line!=null){

				list.add(line);
				line = reader.readLine();
			}
			file.close();
			reader.close();
			File fl = new File("../Files/leaderboard.txt");
			FileOutputStream output = new FileOutputStream(fl);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
			boolean done = false;
			for(int i = 0 ; i < list.size() ; ++i){

				String data[] = list.get(i).split(",");
				if(data[0].equals(name)){

					score = Math.max(score , Integer.parseInt(data[1]));
					done = true;
					writer.write(name + "," + Integer.toString(score));
				}
				else writer.write(list.get(i));

				writer.newLine();
			}
			if(!done){
				writer.write(name + "," + Integer.toString(score));
				writer.newLine();
			}
			writer.close();
			output.close();
			
		}catch(Exception e){

			System.out.println(e);
			System.out.println("Something went wrong while updating LeaderBoard");
		}
	}

	public static void main(String[] args){

		Player player = new Player("Brijesh");
		LeaderBoard leaderboard = new LeaderBoard();
		leaderboard.initialize();
		leaderboard.frame.setVisible(true);

	}
}