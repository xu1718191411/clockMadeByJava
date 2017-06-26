package pin;

import java.util.Calendar;

public class MinutePin extends PinType {

	public MinutePin(Calendar nowTime){
		super(nowTime);
		this.type = "minute";
	}

	@Override
	public double calculateAngle() {
		double rad =  ((double)min/60)*360 + ((double)sec/(60*60))*360 - 90;
		return rad;
	}


}
