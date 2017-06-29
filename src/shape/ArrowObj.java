package shape;

import java.awt.*;

/**
 * Created by xuzhongwei on 2017/06/29.
 */
public class ArrowObj extends PositionType{
    double R;
    double l,s;

    public ArrowObj(double r){
        this.r = r;
        this.rV = 3; //speed when transform
        //this.R = this.r*((Math.random()*5+3)/10);
        this.R = this.r * 0.7;

        this.deg = Math.random()*360+1;
        this.s = this.R * 0.6;
        this.l = this.R * 0.2;

        this.xCoordinate = 250;
        this.yCoordinate = 250;

    }

    @Override
    public void paintShape(Graphics screen,int x,int y){

        double degree = this.deg2rad();

        double Ox = 250;
        double Oy = 250;
        double OG = R;
        double AB = 0.1*R;
        double AC = 0.6*R;
        double BD = AC;
        double EC = AB;
        double DF = AB;
        double OA = AB;
        double OB = AB;

        double Ax = Math.cos(degree+Math.PI/6)*OA;
        double Ay = Math.sin(degree+Math.PI/6)*OA;

        double Bx = Math.cos(degree-Math.PI/6)*OB;
        double By = Math.sin(degree-Math.PI/6)*OB;

        double Cx = Ax + Math.cos(degree)*AC;
        double Cy = Ay + Math.sin(degree)*AC;

        double Dx = Bx + Math.cos(degree)*BD;
        double Dy = By + Math.sin(degree)*BD;

        double Ex = Cx + Math.cos(degree+Math.PI/2)*EC;
        double Ey = Cy + Math.sin(degree+Math.PI/2)*EC;

        double Fx = Dx + Math.cos(degree+1.5*Math.PI)*DF;
        double Fy = Dy + Math.sin(degree+1.5*Math.PI)*DF;
        double Gx = R*Math.cos(degree);
        double Gy = R*Math.sin(degree);



        int xArrs[] = new int[7];
        int yArrs[] = new int[7];


        int xStartPoint = this.xCoordinate;
        int yStartPoint = this.yCoordinate;
        xArrs[0] = (int)Ax + xStartPoint;
        xArrs[1] = (int)Bx + xStartPoint;
        xArrs[2] = (int)Dx + xStartPoint;
        xArrs[3] = (int)Fx + xStartPoint;
        xArrs[4] = (int)Gx + xStartPoint;
        xArrs[5] = (int)Ex + xStartPoint;
        xArrs[6] = (int)Cx + xStartPoint;


        yArrs[0] = -(int)Ay + yStartPoint;
        yArrs[1] = -(int)By + yStartPoint;
        yArrs[2] = -(int)Dy + yStartPoint;
        yArrs[3] = -(int)Fy + yStartPoint;
        yArrs[4] = -(int)Gy + yStartPoint;
        yArrs[5] = -(int)Ey + yStartPoint;
        yArrs[6] = -(int)Cy + yStartPoint;

        screen.setColor(Color.yellow);
        screen.fillPolygon(xArrs,yArrs,7);




    }


}
