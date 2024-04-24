package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

    // TODO entirely your job (except onCircle)
    //Done
    private final Canvas canvas;

    private final Paint paint;

    public Draw(final Canvas canvas, final Paint paint) {
        this.canvas = canvas; // Fixed
        this.paint = paint; // Fixed
        paint.setStyle(Style.STROKE);
    }

    @Override
    public Void onCircle(final Circle c) {
        canvas.drawCircle(0, 0, c.getRadius(), paint);
        return null;
    }
    //all drawings need to be implemented except for onCircle
    @Override
   //drawing implemented for stroke
    public Void onStrokeColor(final StrokeColor c) {

        paint.setStyle(Style.STROKE);
        paint.setColor(c.getColor());
        c.getShape().accept(this);
        paint.setStyle(Style.STROKE);
        paint.setColor(c.getColor());
        return null;
    }

    @Override
    //fill implemented and error fixed because it should account for both Fill and Stroke
    public Void onFill(final Fill f) {
        paint.setStyle(Style.FILL_AND_STROKE);
        f.getShape().accept(this);
        return null;
    }

    @Override
    //shapes implemented for onGroup
    public Void onGroup(final Group g) {
        for (Shape shape : g.getShapes()) {
            shape.accept(this);
        }
        return null;
    }

    @Override
    //translation implemented for canvas
    public Void onLocation(final Location l) {

        canvas.translate(l.getX(), l.getY());
        l.getShape().accept(this);
        canvas.translate(-l.getX(), -l.getY());
        return null;
    }

    @Override
    //drawing implemented for onRect at position
    public Void onRectangle(final Rectangle r) {
        canvas.drawRect(0, 0, r.getWidth(), r.getHeight(), paint);
        return null;
    }

    @Override
    //style was initiated to use stroke and error fixed
    public Void onOutline(Outline o) {

        paint.setStyle(Style.STROKE);
        o.getShape().accept(this);
        return null;
    }

    @Override
    public Void onPolygon(final Polygon s) {

        //created this array to hold the points
        final float[] pts = new float[s.getPoints().size() * 4];

        int count = 0;
        //for loop will go through the points and increment to get the X and Y coordinates
        //if i == 0 to get x and y and to get the points at index 0 and 1 to account for sizes less than 2
        for(int i =0; i<s.getPoints().size(); i++){
            if(i == 0){
                //[++count] will return the incremented value of the point at i
                pts[count] = s.getPoints().get(i).getX();
                pts[++count] = s.getPoints().get(i).getY();
                pts[pts.length - 2] = pts[0];
                pts[pts.length - 1] = pts[1];
            } else{
                pts[++count] = s.getPoints().get(i).getX();
                pts[++count]= s.getPoints().get(i).getY();
                pts[++count] = s.getPoints().get(i).getX();
                pts[++count] = s.getPoints().get(i).getY();
            }
        }

        //should paint the polygon using the points
        canvas.drawLines(pts, paint);
        return null;
    }
}