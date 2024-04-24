package edu.luc.etl.cs313.android.shapes.model;

/**
 * A point, implemented as a location without a shape.
 */
public class Point extends Location {

    // TODO your job
    //done
    // HINT: use a circle with radius 0 as the shape!

    //created a circle with radius 0
    private static final Shape c = new Circle(0);

    public Point(final int x, final int y) {
        super(x, y, new Circle(0));
        assert x >= 0;
        assert y >= 0;
    }
}
