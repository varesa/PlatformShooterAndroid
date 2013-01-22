/**
 * 
 */
package fi.dy.esav.GameEngineAndroid;

public class Rectangle {

	public double x, y;
	public double width, height;

	public Rectangle() {
	}

	public Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean intersects(Rectangle rect2) {
		return this.x < rect2.x+rect2.width && this.x+this.width > rect2.x &&
			    this.y < rect2.y+rect2.height && this.y+this.height > rect2.y;
	}
}
