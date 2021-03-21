package lab2game2d;
import java.awt.Rectangle;


public class RectangleR extends Rectangle{
    private int speed;
    public RectangleR(int x, int y, int width, int height, int speed) {
        super(x,y,width,height);
    this.speed = speed;
}
    public int getSpeed(){
        return this.speed;
    }
    
}
