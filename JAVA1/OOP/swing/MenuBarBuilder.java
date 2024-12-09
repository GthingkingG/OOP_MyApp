package swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuBarBuilder {
	public void bulidmenu(JFrame frame, DrawPaneBuilder drawPane) {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu shapeMenu = new JMenu("Shape");
		JMenu drawMenu = new JMenu("Draw");
		
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem closeItem = new JMenuItem("Close");
		
		JMenuItem copyItem = new JMenuItem("Copy");
		JMenuItem cutItem = new JMenuItem("Cut");
		JMenuItem pasteItem = new JMenuItem("Paste");
		
		JMenuItem rectangle = new JMenuItem("rectangle");
		JMenuItem circle = new JMenuItem("circle");
		JMenuItem selectcolor = new JMenuItem("selectcolor");
		
		JMenuItem pencil = new JMenuItem("Pencil");
		JMenuItem eraser = new JMenuItem("Eraser");
		
		JMenuItem filledRectangle = new JMenuItem("Filled Rectangle");
		JMenuItem filledCircle = new JMenuItem("Filled Circle");
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(closeItem);
		
		editMenu.add(copyItem);
		editMenu.add(cutItem);
		editMenu.add(pasteItem);
		
		shapeMenu.add(rectangle);
		shapeMenu.add(filledRectangle);
		shapeMenu.add(circle);
		shapeMenu.add(filledCircle);
		shapeMenu.add(selectcolor);
		
		drawMenu.add(pencil);
		drawMenu.add(eraser);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(shapeMenu);
		menuBar.add(drawMenu);
		
		frame.setJMenuBar(menuBar);
		
		rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drawPane.setSelectedShape(0); //rectangle
            	
            }
        });
		
		
		circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drawPane.setSelectedShape(1); //circle
            	
            }
        });
		
		selectcolor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Color selectedColor = JColorChooser.showDialog(frame, "Select Color", Color.BLACK);
            	 if (selectedColor != null) {
                     drawPane.setCurrentColor(selectedColor);
                 }
            }
        });
		
		pencil.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        drawPane.setSelectedShape(DrawPaneBuilder.PENCIL);
		    }
		});
		
		eraser.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        drawPane.setSelectedShape(DrawPaneBuilder.ERASER);
		    }
		});
		filledRectangle.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        drawPane.setSelectedShape(DrawPaneBuilder.FILLED_RECTANGLE);
		    }
		});

		filledCircle.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        drawPane.setSelectedShape(DrawPaneBuilder.FILLED_CIRCLE);
		    }
		});
		
	}
	
	
}
