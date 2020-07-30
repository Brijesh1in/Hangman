import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Button{

	private JButton button;
	Button(char btnName , int x1 , int y1){
		button = new JButton();
		String toLoad = btnName + ".png"; 
		//System.out.println(toLoad);
		button.setIcon(new ImageIcon("../Images/" + toLoad));
		button.setBounds(x1 , y1 , 38 , 38);
		// button.addActionListener(new ActionListener(){

		// 	public void actionPerformed(ActionEvent e){

		// 		if(pressed==true)
		// 			return;
		// 		pressed = true;
		// 		System.out.print(btnName);
		// 	}
		// });
		button.setActionCommand(Character.toString(btnName));
	}
	public JButton getButton(){

		return button;
	}
}