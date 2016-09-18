package gamejam;

import java.awt.Image;

public class Bubble {
	public int bubX, bubY;
	public String bubType; //"food" or "predator"
	public Image bubPic;
	public Image foodPic;
	
	//New things I added to try to get each bubble to move slightly differently
	public int bubAmplitude;
	
	
	public Bubble(int bX, int bY, String t, Image imag, Image fP, int amplitude){
		bubX = bX;
		bubY = bY;
		bubType = t;
		bubPic = imag;
		foodPic = fP;
		bubAmplitude = amplitude;
	}

}
