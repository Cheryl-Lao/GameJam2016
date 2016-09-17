package gamejam;

import java.awt.Image;

public class Bubble {
	public int bubX, bubY;
	public String bubType; //"food" or "predator"
	public Image bubPic;
	public Image foodPic;
	public Bubble(int bX, int bY, String t, Image imag, Image fP){
		bubX = bX;
		bubY = bY;
		bubType = t;
		bubPic = imag;
		foodPic = fP;
	}

}
