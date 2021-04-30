/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Circle;
import Models.Ellipse;
import Models.Line;
import Models.Rectangle;
import Models.Square;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 *
 * @author juanc
 */
public class ShapesDAO{
    @SerializedName("square")
    ArrayList<Square> squares;
    ArrayList<Rectangle> rectangles;
    ArrayList<Ellipse> ellipses;
    ArrayList<Circle> circles;
    ArrayList<Line> lines;

    public ShapesDAO() {
        squares = new ArrayList<Square>();
        rectangles = new ArrayList<Rectangle>();
        ellipses = new ArrayList<Ellipse>();
        circles = new ArrayList<Circle>();
        lines = new ArrayList<Line>();
    }
    
    public void addRectangle(Rectangle r) {
        rectangles.add(r);
    }
    
    public void addSquare(Square s) {
        squares.add(s);
    }
    
    public void addEllipse(Ellipse e) {
        ellipses.add(e);
    }
    
    public void addCircle(Circle c) {
        circles.add(c);
    }
    
    public void addLine(Line l) {
        lines.add(l);
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    public ArrayList<Ellipse> getEllipses() {
        return ellipses;
    }

    public ArrayList<Circle> getCircles() {
        return circles;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }
    
    public void clean() {
        squares = new ArrayList<Square>();
        rectangles = new ArrayList<Rectangle>();
        ellipses = new ArrayList<Ellipse>();
        circles = new ArrayList<Circle>();
        lines = new ArrayList<Line>();
    }
}
