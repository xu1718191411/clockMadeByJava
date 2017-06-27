package shape;

import java.awt.Graphics;
import java.util.ArrayList;

public class RectObj extends PositionType  {

	private double l = 10;
	private double h = 6;
	private double angle = 0.0;
	private double thete,gama;
	private double rectR;

	public RectObj(double randomR,double d,double length,double height,int _x,int _y){
		this.l = length;
		this.h = height;
		this.r = randomR;
		this.deg = d;
		this.rV = 3;
		this.xCoordinate = _x;
		this.yCoordinate = _y;

		double thete = Math.toDegrees(Math.atan(length/height));
		double gama =  Math.toDegrees(Math.atan(height/length));
		this.thete = thete;
		this.gama = gama;
		this.rectR = Math.sqrt(length*length + height*height);

		this.angle = Math.random()*360+1;

	}

	private void calculateCoordinate(Graphics screen, int xStartPoint, int yStartPoint){
		this.angle = this.angle + 1;
		this.angle = this.angle + Math.random() * 10;

		double Aangle = Math.toRadians((2*this.thete + this.gama + this.angle) % 360);
		double Bangle = Math.toRadians((2*this.thete + this.gama * 3 + this.angle) % 360);
		double Cangle = Math.toRadians((4*this.thete + this.gama * 3 + this.angle) % 360);
		double Dangle = Math.toRadians((this.gama + this.angle) % 360);


//		System.out.println((2*this.thete + this.gama + this.angle) % 360);
//		System.out.println((2*this.thete + this.gama * 3 + RectObj.angle));
//		System.out.println((4*this.thete + this.gama * 3 + RectObj.angle));
//		System.out.println((this.gama + RectObj.angle));
//		System.out.println("-------------");

		TriangleCoordinate objA = new TriangleCoordinate(this.rectR*Math.cos(Aangle),this.rectR*Math.sin(Aangle));
		TriangleCoordinate objB = new TriangleCoordinate(this.rectR*Math.cos(Bangle),this.rectR*Math.sin(Bangle));
		TriangleCoordinate objC = new TriangleCoordinate(this.rectR*Math.cos(Cangle),this.rectR*Math.sin(Cangle));
		TriangleCoordinate objD = new TriangleCoordinate(this.rectR*Math.cos(Dangle),this.rectR*Math.sin(Dangle));

		ArrayList<TriangleCoordinate> rectanglesArr = new ArrayList<TriangleCoordinate> ();
		rectanglesArr.add(objA);
		rectanglesArr.add(objB);
		rectanglesArr.add(objC);
		rectanglesArr.add(objD);

		int xPoints[] = new int[rectanglesArr.size()];
		int yPoints[] = new int[rectanglesArr.size()];

 		for(int i=0;i<rectanglesArr.size();i++){
 			TriangleCoordinate obj = rectanglesArr.get(i);
 			xPoints[i] = (int)(obj.getX() + this.xCoordinate + xStartPoint );
 			yPoints[i] = (int)(obj.getY() + this.yCoordinate + yStartPoint );
 		}

 		screen.fillPolygon(xPoints, yPoints, 4);
	}

	@Override
	public void paintShape(Graphics screen, int xStartPoint, int yStartPoint) {
		// TODO 自動生成されたメソッド・スタブ
		//screen.draw3DRect(xStartPoint, xStartPoint, (int)this.l, (int)this.h, true);
		//screen.fillRect((int)(xStartPoint + l/2)+this.xCoordinate, (int)(yStartPoint + l/2)+this.yCoordinate,(int)this.l, (int)this.h );
		calculateCoordinate(screen,xStartPoint,yStartPoint);
	}

}
