package gamejam;

import java.awt.Image;

public class DroppedFood {
	public Image foodPic;
	public int dfX, dfY;
	
	public DroppedFood(Image fP, int x, int y){
		foodPic = fP;
		dfX = x;
		dfY = y;
	}

}
