package swing;

import java.awt.Color;

public class MyBox{
	
	protected MyPoint sPos = null;
	protected MyPoint ePos = null;
	protected Color rectColor = null;
	protected int width = 0;
	protected int height = 0;
	protected boolean filled = false;
	
	MyBox(){
		sPos = new MyPoint();	
		ePos = new MyPoint();
	}
	
	MyBox(int iniX, int iniY, int aftX, int aftY){
		sPos = new MyPoint(iniX, iniY);	
		ePos = new MyPoint(aftX, aftY);
		rectUpdate();

	}
	
	MyBox(MyPoint s, MyPoint e){
		sPos = s; 	
		ePos = e; 
		rectUpdate();
	}
	
	MyBox(MyBox other) { //copy constructor
        this.sPos = new MyPoint(other.sPos.x, other.sPos.y);
        this.ePos = new MyPoint(other.ePos.x, other.ePos.y);
        this.width = other.width;
        this.height = other.height;
        this.rectColor = other.rectColor;
    }

	public void setS(int x, int y) {
		this.sPos.x = x;
		this.sPos.y = y;
		rectUpdate();
	}
	public void setE(int x, int y) {
		this.ePos.x = x;
		this.ePos.y = y;
		rectUpdate();
	}
	public void setColor(Color c) {
		this.rectColor = c;
	}
	
	public void rectUpdate(){
		width = ePos.x - sPos.x;
		height = ePos.y - sPos.y;	
	}
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
	
}
