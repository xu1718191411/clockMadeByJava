package pin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Calendar;

import shape.CircleObj;

public abstract class PinType  {
	private double xPosition;
	private double yPosition;
	private double r = 210.0;
	private double hourLen = 120.0;
	private double minLen = 155.0;
	private double secLen = 190.0;
	private double xCoordinate = 250;
	private double yCoordinate = 250;
	private CircleObj objOfClock = new CircleObj();
	protected int hour, min, sec;
	protected String type;

	PinType(Calendar nowTime){
		this.hour = nowTime.get(Calendar.HOUR);
		this.min = nowTime.get(Calendar.MINUTE);
		this.sec = nowTime.get(Calendar.SECOND);
	}

	public CircleObj getCircle() {
		return this.objOfClock;
	}

	public void drawLength(Graphics screen) {

		if(this.type == "hour"){
			screen.setColor(Color.white);
			this.calculatePosition(this.getHourLen());
		}else if(this.type == "minute"){
			screen.setColor(Color.white);
			this.calculatePosition(this.getMinLen());
		}else if(this.type =="second"){
			this.calculatePosition(this.getSecLen());
			screen.setColor(new Color(255,255,0));
		}else{
			this.calculatePosition(this.getSecLen());
			screen.setColor(new Color(155,155,155));
		}

		double xCoordinate = this.getxCoordinate();
		double yCoordinate = this.getyCoordinate();

		Graphics2D g2 = (Graphics2D) screen;
        g2.setStroke(new BasicStroke(3));
        g2.draw(new Line2D.Float((int)xCoordinate,(int)yCoordinate, (int)this.getxPosition(), (int)this.getyPosition()));


		//screen.drawLine((int)xCoordinate,(int)yCoordinate, (int)this.getxPosition(), (int)this.getyPosition());
	}

	public void calculatePosition(double length) {

		double angle = calculateAngle();
		double xCoordinate = this.getxCoordinate();
		double yCoordinate = this.getyCoordinate();
		CircleObj objOfClock = this.getCircle();
		objOfClock.setR(length);
		objOfClock.setDeg(angle);
		double xPosition = objOfClock.calcXPos() + (int)xCoordinate;
		double yPosition = objOfClock.calcYPos() + (int)yCoordinate;

		this.setxPosition(xPosition);
		this.setyPosition(yPosition);


	}

	public abstract double calculateAngle();

	public double getxPosition() {
		return xPosition;
	}

	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}

	public double getyPosition() {
		return yPosition;
	}

	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}


	public double getR() {
		return this.r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getHourLen() {
		return this.hourLen;
	}

	public void setHourLen(double hourLen) {
		this.hourLen = hourLen;
	}

	public double getMinLen() {
		return this.minLen;
	}

	public void setMinLen(double minLen) {
		this.minLen = minLen;
	}

	public double getSecLen() {
		return this.secLen;
	}

	public void setSecLen(double secLen) {
		this.secLen = secLen;
	}


	public double getxCoordinate() {
		return this.xCoordinate;
	}

	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public double getyCoordinate() {
		return this.yCoordinate;
	}

	public  void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

}
