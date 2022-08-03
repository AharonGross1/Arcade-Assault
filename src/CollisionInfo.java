// 209345180 Aharon Gross

/**
 * This class represents the info of an object that has been collided with.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * Constructor for the class.
     *
     * @param collisionPoint - The point of collision.
     * @param collidable - The collidable the collision has occurred with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * Getter for the point where the collision occurred.
     *
     * @return The collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Getter for the collidable object involved in the collision.
     *
     * @return collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}
