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
public class Circle extends Shape {
    int ratio;

    public Circle(int ratio, int x, int y, Color color) {
        super(x, y, color);
        this.ratio = ratio;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }
    
}
