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
public class Ellipse extends Shape{
    int ratioUp, ratioDown;

    public Ellipse(int ratioUp, int ratioDown, int x, int y, Color color) {
        super(x, y, color);
        this.ratioUp = ratioUp;
        this.ratioDown = ratioDown;
    }

    public int getRatioUp() {
        return ratioUp;
    }

    public void setRatioUp(int ratioUp) {
        this.ratioUp = ratioUp;
    }

    public int getRatioDown() {
        return ratioDown;
    }

    public void setRatioDown(int ratioDown) {
        this.ratioDown = ratioDown;
    }
    
}
