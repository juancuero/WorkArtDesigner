/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Color;

/**
 *
 * @author juanc
 */
public class Line extends Shape {
    int xlarge, ylarge;

    public Line(int xlarge, int ylarge, int x, int y, Color color) {
        super(x, y, color);
        this.xlarge = xlarge;
        this.ylarge = ylarge;
    }

    public int getXlarge() {
        return xlarge;
    }

    public void setXlarge(int xlarge) {
        this.xlarge = xlarge;
    }

    public int getYlarge() {
        return ylarge;
    }

    public void setYlarge(int ylarge) {
        this.ylarge = ylarge;
    }
}
