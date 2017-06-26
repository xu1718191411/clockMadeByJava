package jpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
import shape.CircleObj;
import shape.RectObj;
import shape.TriangleObj;

public class ClockFieldObj  extends JPanel  {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;
	private  double r = 210.0;
	private int xCoordinate;
	private int yCoordinate;
	private Image image;
	private BufferedImage originalImage;
	private ArrayList<CircleObj> randomDots = new ArrayList<CircleObj>();
	private ArrayList<TriangleObj> randomTriangles = new ArrayList<TriangleObj>();
	private ArrayList<RectObj> randomRectangles = new ArrayList<RectObj>();

	private long initialTime;
	public int turn = 0;
	public boolean startAnimation = false;
	private boolean isPressed = false;
	private long releaseTime;
	private BufferedImage pokemonBall;

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

		try {
			this.image = ImageIO.read(new File("./img/onePiece.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		try {
			this.pokemonBall = ImageIO.read(new File("./img/pokemonBall.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		;

		this.initialTime = System.currentTimeMillis();

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

		screen.drawImage(this.pokemonBall, 250, 250, this);
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
			CircleObj objs = new CircleObj(r*Math.random(),randomDeg,this.xCoordinate,this.yCoordinate);
			randomDots.add(objs);
		}

		randomTriangles.clear();
		for(int i=0;i<30;i++){
			double randomDeg = Math.random()*360+1;
			TriangleObj objs = new TriangleObj(r*Math.random(),randomDeg,10,this.xCoordinate,this.yCoordinate);
			randomTriangles.add(objs);
		}

		randomRectangles.clear();
		for(int i=0;i<30;i++){
			double randomDeg = Math.random()*360+1;
			RectObj objs = new RectObj(r*Math.random(),randomDeg,3,13,this.xCoordinate,this.yCoordinate);
			randomRectangles.add(objs);
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


}



