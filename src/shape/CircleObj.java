package shape;

import java.awt.Color;
import java.awt.Graphics;

public class CircleObj extends PositionType {

	private double radius = 10;
	private int xCoordinate;
	private int yCoordinate;

	public double getRadius() {
		return radius;
	}
	public void setRadius() {
		this.radius = radius;
	}


	public CircleObj(){
		this(1.0,1.0,250,250);
	}

	public CircleObj(double r){
		this.r = r;
		this.rV = 3; //speed when transform
	}

	public CircleObj(double r,double d,int _x,int _y){
		this.r = r;
		this.deg = d;
		this.rV = 3;
		this.xCoordinate = _x;
		this.yCoordinate = _y;
	}

	public double calcArea() {
		double area = this.r*this.r*Math.PI;
		return area;
	}

	public double calcCircumference() {
		double circumference = this.r*2*Math.PI;
		return circumference;
	}



	@Override
	public void paintShape(Graphics screen, int xStartPoint, int yStartPoint) {
		// TODO 自動生成されたメソッド・スタブ
		int radius = 10;
		screen.setColor(Color.white);
		screen.fillOval((int)(xStartPoint + radius/2)+this.xCoordinate, (int)(yStartPoint + radius/2)+this.yCoordinate, (int)radius, (int)radius);
	}

}
