import javax.swing.*;
import java.awt.*;
public class KeyField{

	private JTextField textField;

	KeyField(int x1 , int y1 , int x2 , int y2){

		textField = new JTextField();
		textField.setFont(new Font("Tahoma" , Font.BOLD , 22));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(x1 , y1 , x2 , y2);
		textField.setVisible(true);
	}
	public JTextField getField(){
		return textField;
	}


}