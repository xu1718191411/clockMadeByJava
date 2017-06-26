package pin;

import java.util.Calendar;

public class HourPin extends PinType {


	public HourPin(Calendar nowTime){
		super(nowTime);
		this.type = "hour";
	}

	@Override
	public double calculateAngle() {

		double rad = ((double)(hour%12)/12)*360 + ((double)min/(12*60))*360 + ((double)sec/(12*60*60))*360 - 90;
		return rad;
	}


}
