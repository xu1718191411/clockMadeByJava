package shape;

import java.awt.Graphics;

public abstract class PositionType {
	public  double r; //半径
	public  double deg; //角度
	protected double rV;

	protected int xCoordinate;
	protected int yCoordinate;

	protected int xLatestCoordinate = 0;
	protected int yLatestCoordinate = 0;

	public void setxLatestCoordinate(int xLatestCoordinate) {
		this.xLatestCoordinate = xLatestCoordinate;
	}

	public void setyLatestCoordinate(int yLatestCoordinate) {
		this.yLatestCoordinate = yLatestCoordinate;
	}

	public int getxLatestCoordinate() {

		return xLatestCoordinate;
	}

	public int getyLatestCoordinate() {
		return yLatestCoordinate;
	}



	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public int getxCoordinate() {

		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public  double getR() {
		return r;
	}

	public  void setR(double r) {
		this.r = r;
	}

	public double getrV() {
		return rV;
	}

	public void setrV(double rV) {
		this.rV = rV;
	}

	public  double getDeg() {
		return deg;
	}

	public  void setDeg(double deg) {
		this.deg = deg;
	}


	public double calcXPos() {
		double rad = deg2rad();
		return this.r*Math.cos(rad);
	}

	public double calcYPos() {
		double rad = deg2rad();
		return this.r*Math.sin(rad);
	}

	public double deg2rad() {
		return Math.toRadians(this.deg);
	}

	public abstract void paintShape(Graphics screen,int xStartPoint,int yStartPoint);

	public void draw(Graphics screen,long initialTime,long currentTime,boolean isPressed,long releaseTime,boolean startAnimation){

		int xStartPoint = (int)this.calcXPos();
		int yStartPoint = (int)this.calcYPos();

		paintShape(screen,xStartPoint,yStartPoint);

		if(isPressed){
			if(210 < Math.abs(this.getR())){
				this.setrV(-1*this.getrV()*1.01);

				this.setxCoordinate(this.getxLatestCoordinate());
				this.setyLatestCoordinate(this.getyLatestCoordinate());
			}

			if(this.rV < 0 && Math.abs(this.getR()) < 0.5){
				this.setrV(this.getrV()*0.6);
			}

			this.setR(this.getR() + this.getrV());

		}else{

			if(!startAnimation) return;
			if((currentTime - releaseTime) <= 3000){
				this.setrV(this.getrV()*(-1));
				this.setR(this.getR() + this.getrV());
			}else{
				double time = 3.5;
				if(Math.abs(this.getR()) > 0){
					this.setrV(-1*(this.getR()/time));
					this.setR(this.getR() + this.getrV());
				}else{
					this.setrV(0);
				}
			}

		}

	}
}
