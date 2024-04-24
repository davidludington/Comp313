package edu.luc.etl.cs313.android.shapes.model;

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
public class BoundingBox implements Visitor<Location> {

    // TODO entirely your job (except onCircle)
    //Done

    @Override
    public Location onCircle(final Circle c) {
        final int radius = c.getRadius();
        return new Location(-radius, -radius, new Rectangle(2 * radius, 2 * radius));
    }

    @Override
    public Location onFill(final Fill f) {

        return f.getShape().accept(this);
    }

    @Override
    //this location method should hold the coordinates and the bounding box
    public Location onGroup(final Group g) {
        int xMin = 0;
        int xMax = 0;
        int yMin = 0;
        int yMax = 0;

        //for loop is created for the shapes to be connected with bounding box


        for(int i =0; i<g.getShapes().size(); i++){
            Shape shape = g.getShapes().get(i);
            Location loc= shape.accept(this);
            Rectangle r = (Rectangle) loc.getShape();

            if(i == 0){
                xMin = loc.getX();
                yMin = loc.getY();
                xMax= loc.getX() + r.getWidth();
                yMax = loc.getY() + r.getHeight();
                continue;
            }
            xMin = Math.min(xMin, loc.getX());
            yMin = Math.min(yMin, loc.getY());
            xMax = Math.max(xMax, loc.getX() + r.getWidth());
            yMax = Math.max(yMax, loc.getY() + r.getHeight());


        }

        //subtracting the max from the min should help calculate the width and height
        int bboxWidth = xMax - xMin;
        int bboxHeight = yMax - yMin;

        //a location will return of the bounding box
        return new Location(xMin, yMin, new Rectangle(bboxWidth, bboxHeight));

        //return null;
    }

    @Override
    public Location onLocation(final Location l) {
        //location and coordinates will calculate the shape of the bounding box
        Location bBox = l.getShape().accept(this);
        return new Location(l.getX() + bBox.getX() , l.getY() +bBox.getY(), bBox.getShape());

       // return null;
    }

    @Override
    public Location onRectangle(final Rectangle r) {
     //rectangle is initialized for bounding box
       return new Location(0, 0, r);
        // return null;
    }

    @Override
    public Location onStrokeColor(final StrokeColor c) {

        return c.getShape().accept(this);
    }

    @Override
    public Location onOutline(final Outline o) {

        return o.getShape().accept(this);
    }

    @Override
    public Location onPolygon(final Polygon s) {
      //initialized to 0
        int xMin= 0;
        int yMin= 0;
        int xMax= 0;
        int yMax= 0;

        //the for loop will help go through each point to create a bounding box
        for(int a =0; a<s.getPoints().size(); a++) {
            Point point = s.getPoints().get(a);
            if(a ==0){
                xMin=point.getX();
                yMin=point.getY();
                xMax=point.getX();
                yMax= point.getY();
                continue;
            }
            xMin = Math.min(xMin, point.getX());
            yMin = Math.min(yMin, point.getY());
            xMax = Math.max(xMax, point.getX());
            yMax = Math.max(yMax, point.getY());

        }
        //subtracting the max from the min should help calculate the width and height as well as return the new location
        int bboxWidth = xMax - xMin;
        int bboxHeight= yMax - yMin;
        return new Location(xMin, yMin, new Rectangle(bboxWidth, bboxHeight));

    }
}
