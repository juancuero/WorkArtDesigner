/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAO.ShapesDAO;
import Models.Circle;
import Models.Ellipse;
import Models.Line;
import Models.Rectangle;
import Models.Shape;
import Models.Square;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author juanc
 */
public class Helper {
    Graphics graphics;

    public Helper(Graphics graphics) {
        this.graphics = graphics;
    }
    
    public void drawImage(Image image) {
        graphics.drawImage(image,0,0,null);
    }
    
    public void draw(ShapesDAO shapes) {
        for (Square shape : shapes.getSquares()) {
            drawSimple(shape);
        }
        for (Rectangle shape : shapes.getRectangles()) {
            drawSimple(shape);
        }
        for (Ellipse shape : shapes.getEllipses()) {
            drawSimple(shape);
        }
        for (Circle shape : shapes.getCircles()) {
            drawSimple(shape);
        }
        for (Line shape : shapes.getLines()) {
            drawSimple(shape);
        }
    }
    
    public void drawSimple(Shape shape) {
        graphics.setColor(shape.getColor());
        if (shape.getClass() == Rectangle.class) {
            Rectangle rec = (Rectangle)shape;
            graphics.fillRect(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight());
        }
        if (shape.getClass() == Square.class) {
            Square square = (Square)shape;
            graphics.fillRect(square.getX(), square.getY(), square.getSide(), square.getSide());
        }
        if (shape.getClass() == Ellipse.class) {
            Ellipse ellip = (Ellipse)shape;
            graphics.fillOval(ellip.getX(), ellip.getY(), ellip.getRatioUp(), ellip.getRatioDown());
        }
        if (shape.getClass() == Circle.class) {
            Circle circle = (Circle)shape;
            graphics.fillOval(circle.getX(), circle.getY(), circle.getRatio(), circle.getRatio());
        }
        if (shape.getClass() == Line.class) {
            Line line = (Line)shape;
            graphics.drawLine(line.getX(),line.getY(), line.getXlarge(), line.getYlarge());
        }
    }
}
