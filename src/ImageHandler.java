import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class ImageHandler{

	private int index;
	private String [] files;
	ImageHandler(){

		files = new String[11];
		for(int i = 1 ; i <= 10 ; ++i)
			files[i] = "Hang" + Integer.toString(i) + ".jpg";
		this.index = 1;
	}

	public ImageIcon getNextImage(){

		++index;
		return new ImageIcon("../Images/" + files[index-1]);
	}


}