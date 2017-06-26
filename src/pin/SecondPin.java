package pin;

import java.util.Calendar;

public class SecondPin extends PinType{

	public SecondPin(Calendar nowTime){
		super(nowTime);
		this.type = "second";
	}

	@Override
	public double calculateAngle() {
		double rad = ((double)sec/ 60)*360 - 90;
		return rad;
	}

}
