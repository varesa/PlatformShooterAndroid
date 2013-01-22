package fi.dy.esav.GameEngineAndroid;

import android.graphics.Point;

public class Utils {

	/**
	 * Calculates the distance between the centerpoints of objects
	 * 
	 * @return The distance between centerpoints
	 */
	public static double getDistanceCenters(Entity ent1, Entity ent2) {
		return Math.sqrt(Math.pow((ent2.x + (ent2.width / 2))
				- (ent1.x + (ent1.width / 2)), 2)
				+ Math.pow(
						(ent2.y + (ent2.height / 2))
								- (ent1.y + (ent1.height / 2)), 2));
	}


	/**
	 * Calculates the distance between the centerpoints of objects on the X axis
	 * 
	 * @return The distance between centerpoints on the X axis
	 */
	public static double getXDistanceCenters(Entity ent1, Entity ent2) {
		return Math.abs((ent1.x + (ent1.width / 2))
				- (ent2.x + (ent2.width / 2)));
	}

	/**
	 * Calculates the distance between the closest corners of objects on the X
	 * axis
	 * 
	 * @return The distance between closest corners on the X axis
	 */
	public static double getYDistanceCorners(Entity ent1, Entity ent2) {
		throw new UnsupportedOperationException(
				"This feature is not yet implemented"); // TODO: Implement!
														// [inmylyn]
	}

	/**
	 * Calculates the distance between the centerpoints of objects on the Y axis
	 * 
	 * @return The distance between centerpoints on the Y axis
	 */
	public static double getYDistanceCenters(Entity ent1, Entity ent2) {
		return Math.abs((ent1.y + (ent1.height / 2))
				- (ent2.y + (ent2.height / 2)));
	}

	/**
	 * A method that checks if the bounding rectangles of two entities are
	 * colliding The entity must override and implement width and
	 * height
	 * 
	 * @param ent1
	 *            One of the two entities to be tested
	 * @param ent2
	 *            Other one of the two entities to be tested
	 * @return Boolean value on wether the rectangles collide or not
	 */
	public static boolean simpleHitTest(Entity ent1, Entity ent2) {
		Rectangle rect1 = new Rectangle((int) ent1.x, (int) ent1.y,
				ent1.width, ent1.height);
		Rectangle rect2 = new Rectangle((int) ent2.x, (int) ent2.y,
				ent2.width, ent2.height);

		return rect1.intersects(rect2);
	}
}
