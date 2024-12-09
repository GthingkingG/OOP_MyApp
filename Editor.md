# OOP_MyApp

package SwingTest;
import javax.swing.*;
import java.awt.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.*;

import java.util.*;
import SwingTest.MyPoint;
import SwingTest.MyBox;

import java.awt.Color;

public class EditorEx extends JFrame {
	
	private Color currentColor = null; 
	private int selectedShape = 0;
	private DrawingPanel drawPane = null;
	
    public EditorEx() {
        setTitle("Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
		JMenuBar menubar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem closeItem = new JMenuItem("Close");
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(closeItem);
		
		menubar.add(fileMenu);
		
		
		JMenu editMenu = new JMenu("Edit");
		JMenuItem copyItem = new JMenuItem("Copy");
		JMenuItem cutItem = new JMenuItem("Cut");
		JMenuItem pasteItem = new JMenuItem("Paste");
		editMenu.add(copyItem);
		editMenu.add(cutItem);
		editMenu.add(pasteItem);
		
		menubar.add(editMenu);
		
		JMenu shapes = new JMenu("Shape");
		JMenuItem rectangle = new JMenuItem("rectangle");
		JMenuItem circle = new JMenuItem("circle");
		JMenuItem colorSelect = new JMenuItem("select color");

		shapes.add(rectangle);
		shapes.add(circle);
		shapes.add(colorSelect);
		
		menubar.add(shapes);
		
		setJMenuBar(menubar);
		
		rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	selectedShape = 0; //rectangle
            	
            }
        });
		
		
		circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	selectedShape = 1; //circle
            	
            }
        });
		
		colorSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JColorChooser를 사용하여 색상 선택
            	currentColor = JColorChooser.showDialog(EditorEx.this, "Select Color", Color.BLACK);

            }
        });
        
        
        
        Container c = getContentPane();
        
        c.setLayout(new BorderLayout());
        //BorderLayout.East, West, South, North, Center

        drawPane = new DrawingPanel();
        drawPane.setBackground(Color.WHITE);
        c.add(drawPane, BorderLayout.CENTER);

        
        JTextPane consolePane = new JTextPane();
        consolePane.setText("Compiling......");
        JScrollPane consoleScrollPane = new JScrollPane(consolePane);
        consoleScrollPane.setPreferredSize(new Dimension(100, 100));
        c.add(consoleScrollPane, BorderLayout.SOUTH);
        
               
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("hello.java");
        model.addElement("world.java");
        
		JList<String> list = new JList<>(model);
		
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setPreferredSize(new Dimension(150, 100));
		
		c.add(listScrollPane, BorderLayout.EAST);
				
		  // 루트 노드 생성
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("SRC");

        // 자식 노드 추가
        DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("GUI");
        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("lib");

        root.add(child1);
        root.add(child2);

        // 자식의 자식 노드 추가
        DefaultMutableTreeNode grandchild1 = new DefaultMutableTreeNode("hello.java");
        DefaultMutableTreeNode grandchild2 = new DefaultMutableTreeNode("world.java");

        child1.add(grandchild1);
        child2.add(grandchild2);

        // JTree 생성
        JTree tree = new JTree(root);

        // JScrollPane에 JTree 추가
        JScrollPane treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setPreferredSize(new Dimension(150, 100));
        
        c.add(treeScrollPane, BorderLayout.WEST);

		pack(); 
		setSize(1024, 712);
        setVisible(true);
    }
    
    
   
    
    class DrawingPanel extends JPanel{
    	
    	private Vector<MyBox> rectList;
    	private Vector<MyBox> circleList;

    	MyPoint start = null; 
    	MyPoint end  = null; 
    	
    	DrawingPanel(){
    		
    		rectList = new Vector<MyBox>();
    		circleList = new Vector<MyBox>();
    		start = new MyPoint();
    		end = new MyPoint();
    		
    		
    	   	addMouseListener(new MouseAdapter(){
        		public void mousePressed(MouseEvent e) {
        			System.out.println(EditorEx.this.selectedShape);
        			start.x = e.getX();
        			start.y = e.getY();
        		}
        		public void mouseReleased(MouseEvent e) {
        			System.out.println(EditorEx.this.selectedShape);
        			end.x = e.getX();
        			end.y = e.getY();
        			
            	   	if (EditorEx.this.selectedShape == 0) {
            			
            	   		MyPoint tempStart = new MyPoint(start);
            	   		MyPoint tempEnd = new MyPoint(end);
            	   		MyBox rect = new MyBox(tempStart, tempEnd);
            			rect.setColor(EditorEx.this.currentColor);
            			rectList.add(rect);
            			
            			//g.setColor(EditorEx.this.currentColor);
            			//g.drawRect(rect.sPos.x, rect.sPos.y, rect.width, rect.height);
            			
            		}else if(EditorEx.this.selectedShape == 1) {
            	   		MyPoint tempStart = new MyPoint(start);
            	   		MyPoint tempEnd = new MyPoint(end);
            	   		MyBox cirBox = new MyBox(tempStart, tempEnd);
            	   		cirBox.setColor(EditorEx.this.currentColor);
            	   		circleList.add(cirBox);
            			
            		}
            	   	repaint();
        		}
        		
    	   	});	
	
    	}
    	
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);

			System.out.println(rectList.size());
			System.out.println(circleList.size());
			
			for(int i=0; i < rectList.size(); i++) {
				MyBox temp = rectList.get(i);
				g.setColor(temp.rectColor);
				g.drawRect(temp.sPos.x, temp.sPos.y, temp.width, temp.height);
				System.out.println("x: " + temp.sPos.x + ", y: " + temp.sPos.y + 
		                   ", width: " + temp.width + ", height: " + temp.height);
				   				
			}

			for(int i=0; i < circleList.size(); i++) {
				MyBox temp = circleList.get(i);
				g.setColor(temp.rectColor);
				g.drawOval(temp.sPos.x, temp.sPos.y, temp.width, temp.height);
				   				
			}
		
    	}

    }
    
    public static void main(String[] args) {
        new EditorEx();
    }
    
}
