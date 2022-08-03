// 209345180 Aharon Gross

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the game environment of a game.
 */
public class GameEnvironment {
    private List<Collidable> collidableList;

    /**
     * Constructor for the class.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * Getter for the class's collidable list.
     *
     * @return - The class's collidable list.
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c - The collidable to add to the environment.
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            this.collidableList.add(c);
        }
    }

    /**
     * Remove the given collidable to the environment.
     *
     * @param c - The collidable to be removed from the environment.
     */
    public void removeCollidable(Collidable c) {
        if (c != null) {
            this.collidableList.remove(c);
        }
    }

    /**
     * Assume an object moving from line.start() to line.end(). If this object will not
     * collide with any of the collidables in this collection, return null. Else, return
     * the information about the closest collision that is going to occur.
     *
     * @param trajectory - The object's trajectory, it's next step.
     * @return the collisionInfo of the closest collision.
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collisionPoint = null;
        Collidable collidable = null;
        // Iterate through the collidables
        for (Collidable c : this.collidableList) {
            List<Point> collisionList = c.getCollisionRectangle().intersectionPoints(trajectory);
            // If there is at least 1 collision
            if (collisionList.size() != 0) {
                // The first collision in the list, not necessarily the closest one.
                if (collisionPoint == null) {
                    collisionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                    collidable = c;
                } else {
                    Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                    if (p != null) {
                        // Check which of the collision points and objects is closer to the trajectory's start
                        if (GeneralCalcs.isEqual(trajectory.start().distance(collisionPoint),
                                trajectory.start().distance(p))) {
                            if (GeneralCalcs.isBigger(trajectory.start().distance(collidable.getCollisionRectangle()
                                            .getFrame().bottomOfFrameLine().middle()),
                                    trajectory.start().distance(c.getCollisionRectangle()
                                            .getFrame().bottomOfFrameLine().middle()))) {
                                collidable = c;
                            }
                        }
                        // If the last collision point found is farther away than this one, switch info to the
                        // closest one
                        if (GeneralCalcs.isBigger(trajectory.start().distance(collisionPoint),
                                trajectory.start().distance(p))) {
                            collisionPoint = p;
                            collidable = c;
                        }
                    }
                }
            }
        }
        return new CollisionInfo(collisionPoint, collidable);
    }
}
