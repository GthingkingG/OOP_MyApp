package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.*;


public class MyApp extends JFrame {
	private DrawPaneBuilder drawPane;
	
	public MyApp() {
		setTitle("Editor");
		setSize(1260,900);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
        c.setLayout(new BorderLayout());
		
		drawPane = new DrawPaneBuilder();
		drawPane.setBackground(Color.WHITE);
		c.add(drawPane, BorderLayout.CENTER);
		
		MenuBarBuilder menu = new MenuBarBuilder();
		menu.bulidmenu(this, drawPane);
		
		BottomPaneBuilder bottompane = new BottomPaneBuilder();
		bottompane.buildbottompane(this);
		
		TreePaneBuilder treepane = new TreePaneBuilder();
		treepane.buildtreepane(this);
		
		ListPaneBuilder listpane = new ListPaneBuilder();
		listpane.buildlistpane(this);
		
		setVisible(true);
		
		


	}


	public static void main(String[] args) {
		MyApp mf = new MyApp();
	}

}
