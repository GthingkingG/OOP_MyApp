package swing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


public class DrawPaneBuilder extends JPanel {
	private Vector<MyBox> rectList;
	private Vector<MyBox> circleList;
	private Vector<PencilDraw> pencilList;
	private Vector<EraserDraw> eraserList;
	MyPoint start = null; 
	MyPoint end  = null; 
	private int selectedShape;
    private Color currentColor;
    private PencilDraw currentPencilDraw;
    private EraserDraw currentEraserDraw;
    private boolean filled = false;
    
    public static final int RECTANGLE = 0;
    public static final int CIRCLE = 1;
    public static final int FILLED_RECTANGLE = 2;
    public static final int FILLED_CIRCLE = 3;
    public static final int PENCIL = 4;
    public static final int ERASER = 5;
    
    private static final int ERASER_SIZE = 10;
	
	public DrawPaneBuilder(){
		rectList = new Vector<MyBox>();
		circleList = new Vector<MyBox>();
		pencilList = new Vector<PencilDraw>();
		eraserList = new Vector<EraserDraw>();
		start = new MyPoint();
		end = new MyPoint();
		selectedShape = RECTANGLE;
        currentColor = Color.BLACK;
		
	   	addMouseListener(new MouseAdapter(){
    		public void mousePressed(MouseEvent e) {
    			System.out.println(selectedShape);
    			start.x = e.getX();
    			start.y = e.getY();
    			 if (selectedShape == PENCIL) {
                     currentPencilDraw = new PencilDraw();
    			 currentPencilDraw.setColor(currentColor);
                 currentPencilDraw.addPoint(start.x, start.y);
             } else if (selectedShape == ERASER) {
                 currentEraserDraw = new EraserDraw(ERASER_SIZE);
                 currentEraserDraw.addPoint(start.x, start.y);
             }
         }
    		public void mouseReleased(MouseEvent e) {
    			System.out.println(selectedShape);
    			end.x = e.getX();
    			end.y = e.getY();
    			
        	   	if (selectedShape == RECTANGLE || selectedShape == FILLED_RECTANGLE) {
        	   		MyPoint tempStart = new MyPoint(start);
        	   		MyPoint tempEnd = new MyPoint(end);
        	   		MyBox rect = new MyBox(tempStart, tempEnd);
        			rect.setColor(currentColor);
        			rect.setFilled(selectedShape == FILLED_RECTANGLE);
        			rectList.add(rect);
        		}else if(selectedShape == CIRCLE || selectedShape == FILLED_CIRCLE) {
        	   		MyPoint tempStart = new MyPoint(start);
        	   		MyPoint tempEnd = new MyPoint(end);
        	   		MyBox cirBox = new MyBox(tempStart, tempEnd);
        	   		cirBox.setColor(currentColor);
        	   		cirBox.setFilled(selectedShape == FILLED_CIRCLE);
        	   		circleList.add(cirBox);
        		}else if (selectedShape == PENCIL) {
                    pencilList.add(currentPencilDraw);
                    currentPencilDraw = null;
                }else if (selectedShape == ERASER) {
                    eraserList.add(currentEraserDraw);
                    currentEraserDraw = null;
                }
        	   	repaint();
    		}
    		
	   	});	
	   	addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                end.x = e.getX();
                end.y = e.getY();
                if (selectedShape == PENCIL && currentPencilDraw != null) {
                    currentPencilDraw.addPoint(end.x, end.y);
                } else if (selectedShape == ERASER && currentEraserDraw != null) {
                    currentEraserDraw.addPoint(end.x, end.y);
                }
                repaint();
            }
        });

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		System.out.println(rectList.size());
		System.out.println(circleList.size());
		
		 for (MyBox rect : rectList) {
	            g.setColor(rect.rectColor);
	            if (rect.filled) {
	                g.fillRect(rect.sPos.x, rect.sPos.y, rect.width, rect.height);
	            } else {
	                g.drawRect(rect.sPos.x, rect.sPos.y, rect.width, rect.height);
	            }
				System.out.println((rect.filled ? "Filled Rectangle" : "Rectangle") + 
				                   " - x: " + rect.sPos.x + ", y: " + rect.sPos.y + 
		                           ", width: " + rect.width + ", height: " + rect.height);
	        }

        for (MyBox circle : circleList) {
	            g.setColor(circle.rectColor);
	            if (circle.filled) {
	                g.fillOval(circle.sPos.x, circle.sPos.y, circle.width, circle.height);
	            } else {
	                g.drawOval(circle.sPos.x, circle.sPos.y, circle.width, circle.height);
	            }
				System.out.println((circle.filled ? "Filled Circle" : "Circle") + 
				                   " - x: " + circle.sPos.x + ", y: " + circle.sPos.y + 
		                           ", width: " + circle.width + ", height: " + circle.height);
	        }
		for (PencilDraw pencil : pencilList) {
            pencil.draw(g);
        }
		for (EraserDraw eraser : eraserList) {
            eraser.draw(g);
        }
		if (currentPencilDraw != null) {
            currentPencilDraw.draw(g);
        }
		if (selectedShape != PENCIL && selectedShape != ERASER) {
            g.setColor(currentColor);
            if (selectedShape == RECTANGLE || selectedShape == FILLED_RECTANGLE) {
            	if (selectedShape == FILLED_RECTANGLE) {
                    g.fillRect(Math.min(start.x, end.x), Math.min(start.y, end.y), 
                               Math.abs(end.x - start.x), Math.abs(end.y - start.y));
                } else {
                    g.drawRect(Math.min(start.x, end.x), Math.min(start.y, end.y), 
                               Math.abs(end.x - start.x), Math.abs(end.y - start.y));
                }
            } else if (selectedShape == CIRCLE || selectedShape == FILLED_CIRCLE) {
            	if (selectedShape == FILLED_CIRCLE) {
                    g.fillOval(Math.min(start.x, end.x), Math.min(start.y, end.y), 
                               Math.abs(end.x - start.x), Math.abs(end.y - start.y));
                } else {
                    g.drawOval(Math.min(start.x, end.x), Math.min(start.y, end.y), 
                               Math.abs(end.x - start.x), Math.abs(end.y - start.y));
                }
            }
        }
	
	}
	public void setSelectedShape(int shape) {
        selectedShape = shape;
        repaint();
    }

    public void setCurrentColor(Color color) {
        currentColor = color;
 
