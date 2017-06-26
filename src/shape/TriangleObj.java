package shape;

import java.awt.Graphics;
import java.util.ArrayList;

public class TriangleObj extends PositionType{
	private int cornerNum;
	private static double angle = 0.0;
	private double length;
	private  static final double OFFSETPAD = - Math.PI/2;
	private ArrayList<TriangleCoordinate> coordinates;
	private int xCoordinate;
	private int yCoordinate;

	public TriangleObj(double randomR,double deg,double length,int _x,int _y){
		this.r = randomR;
		this.deg = deg;

		this.length = length;
		this.cornerNum = 5;
		this.coordinates = new ArrayList<TriangleCoordinate>(cornerNum);
		this.xCoordinate = _x;
		this.yCoordinate = _y;
		this.rV = 3; //speed when transform
	}

	public ArrayList<TriangleCoordinate> calculateCoordinate(){
		ArrayList<Double> degArrs = new ArrayList<Double>(this.cornerNum);
		ArrayList<TriangleCoordinate> coorArrs = new ArrayList<TriangleCoordinate>(this.cornerNum * 2);

		for(int i=0;i<5;i++){
			double x1 = Math.cos(((18.0 + angle)+i*72.0)/180*Math.PI)*this.length;
			double y1 = -Math.sin(((18.0 + angle)+i*72.0)/180*Math.PI)*this.length;
			double x2 = Math.cos(((54.0 + angle)+i*72.0)/180*Math.PI)*this.length*0.4;
			double y2 = -Math.sin(((54.0 + angle)+i*72.0)/180*Math.PI)*this.length*0.4;

			TriangleCoordinate coor = new TriangleCoordinate(x1,y1);
			TriangleCoordinate coor2 = new TriangleCoordinate(x2,y2);
			coorArrs.add(coor);
			coorArrs.add(coor2);
		}

		TriangleObj.angle = TriangleObj.angle+0.3;

		return coorArrs;
	}



	@Override
	public void paintShape(Graphics screen, int xStartPoint, int yStartPoint) {
		ArrayList<TriangleCoordinate> trianglesArr =  this.calculateCoordinate();


		int xPoints[] = new int[trianglesArr.size()];
		int yPoints[] = new int[trianglesArr.size()];

 		for(int i=0;i<trianglesArr.size();i++){
 			TriangleCoordinate obj = trianglesArr.get(i);
 			xPoints[i] = (int)(obj.getX() + this.xCoordinate + xStartPoint + 10 );
 			yPoints[i] = (int)(obj.getY() + this.yCoordinate + yStartPoint + 10 );
 		}

		screen.fillPolygon(xPoints, yPoints, 10);
	}

}

