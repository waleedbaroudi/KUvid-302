package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void rotateVector() {
        double epsilon = 0.001;

        Vector vector = new Vector(new Coordinates(0,0), new Coordinates(2,2));

        Vector rotatedVector = new Vector(new Coordinates(0,0), new Coordinates(-2,2));

        vector.rotateVector(90);
        assertTrue(vector.getX() - rotatedVector.getX() <= epsilon && vector.getY() - rotatedVector.getY() <= epsilon);

        rotatedVector = new Vector(new Coordinates(0,0), new Coordinates(2,2));
        vector.rotateVector(-90);
        assertTrue(vector.getX() - rotatedVector.getX() <= epsilon && vector.getY() - rotatedVector.getY() <= epsilon);
    }
}