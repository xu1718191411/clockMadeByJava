package jpanel;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pin.HourPin;
import pin.MinutePin;
import pin.PinType;
import pin.SecondPin;
import shape.ArrowObj;
import shape.CircleObj;
import shape.RectObj;
import shape.TriangleObj;
import shape.ArrowObj;

public class ClockFieldObj  extends JPanel  {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;
	private  double r = 210.0;
	private int xCoordinate = 250;
	private int yCoordinate = 250;
	private int xFocusCoordinate;
	private int yFocusCoordinate;


	public void setxFocusCoordinate(int xFocusCoordinate) {
		this.xFocusCoordinate = xFocusCoordinate;
	}

	public void setyFocusCoordinate(int yFocusCoordinate) {
		this.yFocusCoordinate = yFocusCoordinate;
	}

	public int getyFocusCoordinate() {
		return yFocusCoordinate;
	}

	public int getxFocusCoordinate() {
		return xFocusCoordinate;
	}

	private Image image;
	private BufferedImage originalImage;
	private ArrayList<CircleObj> randomDots = new ArrayList<CircleObj>();
	private ArrayList<TriangleObj> randomTriangles = new ArrayList<TriangleObj>();
	private ArrayList<RectObj> randomRectangles = new ArrayList<RectObj>();
	private ArrayList<ArrowObj> randomArrows = new ArrayList<>();

	private long initialTime;
	public int turn = 0;
	public boolean startAnimation = false;
	private boolean isPressed = false;
	private long releaseTime;

	private boolean stopWatch = false;

	private CircleObj stopWatchCircle;

	public long getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(long releaseTime) {
		this.releaseTime = releaseTime;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	public ClockFieldObj() {

		this.setBackground(new Color(0,0,0));
		this.xCoordinate = 250;
		this.yCoordinate = 250;
		this.xFocusCoordinate = this.xCoordinate;
		this.yFocusCoordinate = this.yCoordinate;

		try {
			this.image = ImageIO.read(new File("./img/onePiece.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}


		this.initialTime = System.currentTimeMillis();
		this.stopWatchCircle = new CircleObj(r*0.15 , 0 , (int) (this.xCoordinate + 100), (int) (this.yCoordinate + 80));

	}

	@Override
	public void paintComponent(Graphics screen){
		screen.clearRect (0, 0, this.getWidth(), this.getHeight());
		super.paintComponent(screen);

        screen.setClip(new java.awt.geom.Ellipse2D.Float((float)(this.xCoordinate-r), (float)(this.yCoordinate-r), (float)r*2, (float)r*2));
        screen.drawImage(this.image, 0, 0, this);

		paintFrame(screen);
		paintPin(screen);
		//painTimeString(screen);
		drawRandomDots(screen);
	}

	public void paintFrame(Graphics screen) {
		int j;
		for (int i = 0; i < 360; i += 6) {
			if (i % 5 == 0) {
				j = 15;
			} else {
				j = 10;
			}

			CircleObj c1 = new CircleObj(r - j, i,this.xCoordinate,this.yCoordinate);
			double x1 = c1.calcXPos();
			double y1 = c1.calcYPos();

			CircleObj c2 = new CircleObj(r, i,this.xCoordinate,this.yCoordinate);
			double x2 = c2.calcXPos();
			double y2 = c2.calcYPos();

			screen.setColor(new Color(102, 255, 255));
			screen.drawLine((int) (x1 + this.xCoordinate), (int) (y1 + this.yCoordinate), (int) (x2 + this.xCoordinate),
					(int) (y2 + this.yCoordinate));
		}

		for(int i=0;i<12;i++){
			double angle = 30*(i+1) - 90;
			CircleObj angleString = new CircleObj(r*0.8,angle,this.xCoordinate,this.yCoordinate);
			int _x = (int)angleString.calcXPos() + this.xCoordinate;
			int _y = (int)angleString.calcYPos() + this.yCoordinate;

			Font font = new Font("serif",Font.ITALIC,25);
			screen.setFont(font);
			screen.drawString(new Integer(i+1).toString(), _x, _y);
		}

		Graphics2D g2 = (Graphics2D) screen;

		int k;
		for (int i = 0; i < 360; i += 30) {
			if (i % 30 == 0) {
				k = 8;
			} else {
				k = 12;
			}

			CircleObj c1 = new CircleObj(r*0.2 - k, i,this.xCoordinate,this.yCoordinate);
			double x1 = c1.calcXPos();
			double y1 = c1.calcYPos();

			CircleObj c2 = new CircleObj(r*0.2, i,this.xCoordinate,this.yCoordinate);
			double x2 = c2.calcXPos();
			double y2 = c2.calcYPos();

			screen.setColor(Color.BLUE);


			g2.setStroke(new BasicStroke(3));
			g2.draw(new Line2D.Float((int) (x1 + this.xCoordinate + 100), (int) (y1 + this.yCoordinate + 80), (int) (x2 + this.xCoordinate + 100),
					(int) (y2 + this.yCoordinate + 80)));
		}

		if(this.stopWatch){
			this.stopWatchCircle.setDeg((this.stopWatchCircle.getDeg()+2)%360);
		}

		g2.setStroke(new BasicStroke(3));
		g2.draw(new Line2D.Float(this.xCoordinate + 100,this.yCoordinate + 80,(int)(this.xCoordinate + 100 + this.stopWatchCircle.calcXPos()),(int)(this.yCoordinate + 80 + this.stopWatchCircle.calcYPos())));

	}


	public void paintPin(Graphics screen) {
		ArrayList<PinType> list = new ArrayList<>();
		Calendar nowTime = Calendar.getInstance();
		list.add(new SecondPin(nowTime));
		list.add(new MinutePin(nowTime));
		list.add(new HourPin(nowTime));
		for (PinType pinType : list) {
			pinType.drawLength(screen);
		}

		if(this.turn % 2 == 1){
			painTimeString(screen);
		}
	}


	public void painTimeString(Graphics screen) {

		Font font = new Font("Verdana", Font.CENTER_BASELINE,28);
	    screen.setFont(font);
	    screen.setColor(Color.WHITE);

		Calendar cal = Calendar.getInstance();
	    Date date = new Date();
	  	cal.setTime(date);

	    SimpleDateFormat sdf_now = new SimpleDateFormat("yyyy/MM/dd");
	    String date_string = (sdf_now.format(cal.getTime()));
		screen.drawString(date_string, (int)(this.getWidth()/2-r*0.4), (int)(this.getHeight()/2+r*0.3));

	  	// hh:mm aaa
	  	SimpleDateFormat sdf_now_time = new SimpleDateFormat(" hh:mm:ss");
	  	String time_string = (sdf_now_time.format(cal.getTime()));
	  	screen.drawString(time_string, (int)(this.getWidth()/2-r*0.3), (int)(this.getHeight()/2+r*0.5));

	}


	public void initRandomDots(){
		randomDots.clear();
		for(int i=0;i<20;i++){
			double randomDeg = Math.random()*360+1;
			CircleObj objs = new CircleObj(r*Math.random(),randomDeg,this.xFocusCoordinate,this.yFocusCoordinate);
			randomDots.add(objs);
		}

		randomTriangles.clear();
		for(int i=0;i<30;i++){
			double randomDeg = Math.random()*360+1;
			TriangleObj objs = new TriangleObj(r*Math.random(),randomDeg,10,this.xFocusCoordinate,this.yFocusCoordinate);
			randomTriangles.add(objs);
		}

		randomRectangles.clear();
		for(int i=0;i<30;i++){
			double randomDeg = Math.random()*360+1;
			RectObj objs = new RectObj(r*Math.random(),randomDeg,3,13,this.xFocusCoordinate,this.yFocusCoordinate);
			randomRectangles.add(objs);
		}

		randomArrows.clear();
		for(int i=0;i<7;i++){
			ArrowObj objs = new ArrowObj(r);
			this.randomArrows.add(objs);
		}

	}


	public void changeRandomDotsCoordinate(int x,int y){
		for(int i=0;i<this.randomDots.size();i++){
//			this.randomDots.get(i).setxCoordinate(x);
//			this.randomDots.get(i).setyCoordinate(y);

			this.randomDots.get(i).setxLatestCoordinate(x);
			this.randomDots.get(i).setyLatestCoordinate(y);
		}

		for(int i=0;i<this.randomTriangles.size();i++){
//			this.randomTriangles.get(i).setxCoordinate(x);
//			this.randomTriangles.get(i).setyCoordinate(y);

			this.randomTriangles.get(i).setxLatestCoordinate(x);
			this.randomTriangles.get(i).setyLatestCoordinate(y);
		}

		for(int i=0;i<this.randomRectangles.size();i++){
//			this.randomRectangles.get(i).setxCoordinate(x);
//			this.randomRectangles.get(i).setyCoordinate(y);

			this.randomRectangles.get(i).setxLatestCoordinate(x);
			this.randomRectangles.get(i).setyLatestCoordinate(y);
		}


	}


	private void drawRandomDots(Graphics screen){
		long currentTime = System.currentTimeMillis();
		for(CircleObj circle : randomDots){
			circle.draw(screen,this.initialTime,currentTime,this.isPressed,this.releaseTime,this.startAnimation);
		}

		for(TriangleObj triangle:randomTriangles){
			triangle.draw(screen,this.initialTime,currentTime,this.isPressed,this.releaseTime,this.startAnimation);
		}

		for(RectObj rectangle:randomRectangles){
			rectangle.draw(screen, currentTime, currentTime, this.isPressed, this.releaseTime, this.startAnimation);
		}

		System.out.println("------------");
		for(ArrowObj arrow:randomArrows){

			double degree = arrow.deg2rad();
			arrow.setxCoordinate(arrow.getxCoordinate()+(int)(3*Math.cos(degree)));
			arrow.setyCoordinate(arrow.getyCoordinate()-(int)(3*Math.sin(degree)));


			arrow.paintShape(screen,this.xCoordinate,this.yCoordinate);
		}
		System.out.println("-------------");
	}

	public boolean isStartAnimation() {
		return startAnimation;
	}

	public void setStartAnimation(boolean startAnimation) {
		this.startAnimation = startAnimation;
	}

	public double getR() {
		return this.r;
	}

	public  void setR(double r) {
		this.r = r;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public void setStopWatch(boolean stopWatch) {
		this.stopWatch = stopWatch;
	}

	public boolean isStopWatch() {

		return stopWatch;
	}


}



