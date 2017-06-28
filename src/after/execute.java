package after;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import frame.JFrameObj;
import jpanel.ClockDriver;
import jpanel.ClockFieldObj;

public class execute {
	public static void main(String[] args){
		JFrame frm = new JFrameObj("work 3");
		ClockFieldObj cf = new ClockFieldObj();
		JButton button = new JButton("start storm");

		frm.add(cf,BorderLayout.CENTER);

		button.addMouseListener(new CustomiseMouseListener(cf));

		cf.addMouseListener(new CustomiseMouseListener(cf));
		cf.addMouseMotionListener(new MyMouseMotionListener(cf));

		frm.add(button,BorderLayout.SOUTH);

		frm.setSize(500, 550);
		frm.setVisible(true);
		Thread t = new Thread(new ClockDriver(cf));
		t.start();
	}
}


class myActionListener implements  ActionListener{
	ClockFieldObj obj;
	myActionListener(ClockFieldObj _obj){
		this.obj = _obj;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("now is clicking....");
		this.obj.turn = this.obj.turn + 1;
	}

}

class BtnModelListener implements ChangeListener {
    private boolean pressed = false;  // holds the last pressed state of the button

	@Override
	public void stateChanged(ChangeEvent e) {
        ButtonModel model = (ButtonModel) e.getSource();
        // if the current state differs from the previous state
        if (model.isPressed() != pressed) {
            String text = "Button pressed: " + model.isPressed() + "\n";
            pressed = model.isPressed();
            System.out.println(text);
        }
	}
}


class CustomiseMouseListener implements MouseListener{
	ClockFieldObj obj;
	CustomiseMouseListener(ClockFieldObj _obj){
		this.obj = _obj;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		this.obj.setStartAnimation(true);
		this.obj.setPressed(true);
		this.obj.initRandomDots();
		this.obj.setStopWatch(true);
		int x=e.getX();
		int y=e.getY();
		this.obj.setxFocusCoordinate(x);
		this.obj.setyFocusCoordinate(y);
		this.obj.initRandomDots();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		this.obj.setPressed(false);
		this.obj.setReleaseTime(System.currentTimeMillis());
		this.obj.setStopWatch(false);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}


}


class MyMouseMotionListener implements MouseMotionListener {

	ClockFieldObj obj;

	MyMouseMotionListener(ClockFieldObj _obj){
		this.obj = _obj;
	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		//do something

		if(this.obj.isStartAnimation()){
			int x=e.getX();
			int y=e.getY();



			this.obj.changeRandomDotsCoordinate(x,y);
		}

	}

}
