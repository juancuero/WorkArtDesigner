/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.ShapesDAO;
import Models.Circle;
import Models.Ellipse;
import Models.Line;
import Models.Rectangle;
import Models.Shape;
import Models.Square;
import Views.MainWindow;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *
 * @author juanc
 */
public class Controller {
    ShapesDAO shapes;
    MainWindow window;
    MouseListener mouseListener; 
    MouseMotionListener motionListener;
    ActionListener  colorListener;
    ActionListener loadImage, saveShapes, loadShapes;
    ActionListener selectFile, selectImage, selectSave;
    String tipo, color;
    int pointx1, pointy1;

    public Controller() {
        shapes = new ShapesDAO();
        window = new MainWindow(shapes); 
        window.setPanel();
        window.setFilterFile();
        window.setVisible(true);
        tipo = "";
        color = "";
    }
    
    public void run(){

       window.gettipoFigura().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                tipo =   window.gettipoFigura().getSelectedItem().toString();
            }
        }); 
       
        window.gettipoColor().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                color =   window.gettipoColor().getSelectedItem().toString();
            }
        }); 
       
   
        
        colorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                color = button.getText();
            }
        };
         
        
        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (validate()) {
                    pointx1 = e.getX();
                    pointy1 = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (validate()) {
                    int x1 = pointx1;
                    int y1 = pointy1;
                    int x2 = e.getX();
                    int y2 = e.getY();

                    if (x2 < x1) {
                        x1 = x2;
                        x2 = pointx1;
                    }
                    if (y2 < y1) {
                        y1 = y2;
                        y2 = pointy1;
                    }

                    Color clr = null;
                    if (color.equals("Verde"))
                        clr = Color.GREEN;
                    if (color.equals("Azul"))
                        clr = Color.BLUE;
                    if (color.equals("Amarillo")) 
                        clr = Color.YELLOW;
                    if (color.equals("Rojo"))
                        clr = Color.RED;


                    if (tipo.equals("Rectangulo")) {
                        Rectangle r = new Rectangle(x2-x1, y2-y1, x1, y1, clr);
                        shapes.addRectangle(r);                    
                    }
                    if (tipo.equals("Cuadrado")) {
                        int side = y2 - y1 > x2 - x1? y2 - y1: x2 - x1;
                        Square s = new Square(side, x1, y1, clr);
                        shapes.addSquare(s);
                    }
                    if (tipo.equals("Elipse")) {
                        Ellipse el = new Ellipse(x2-x1, y2-y1, x1, y1, clr);
                        shapes.addEllipse(el);                    
                    }
                    if (tipo.equals("Circulo")) {
                        int ratio = y2 - y1 > x2 - x1? y2 - y1: x2 - x1;
                        Circle c = new Circle(ratio, x1, y1, clr);
                        shapes.addCircle(c);                    
                    }
                    if (tipo.equals("Linea")) {
                        x1 = pointx1;
                        y1 = pointy1;
                        x2 = e.getX();
                        y2 = e.getY();
                        Line l = new Line(x2, y2, x1, y1, clr);
                        shapes.addLine(l);                    
                    }

                    window.getPanel().setMode(0);
                    window.getPanel().repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
        
        window.getPanel().addMouseListener(mouseListener);
        
        motionListener = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (validate()) {
                    int x1 = pointx1;
                    int y1 = pointy1;
                    int x2 = e.getX();
                    int y2 = e.getY();

                    if (x2 < x1) {
                        x1 = x2;
                        x2 = pointx1;
                    }
                    if (y2 < y1) {
                        y1 = y2;
                        y2 = pointy1;
                    }

                    Color clr = null;
                    if (color.equals("Verde"))
                        clr = Color.GREEN;
                    if (color.equals("Azul"))
                        clr = Color.BLUE;
                    if (color.equals("Amarillo")) 
                        clr = Color.YELLOW;
                    if (color.equals("Rojo"))
                        clr = Color.RED;

                    Shape shape = null;
                    if (tipo.equals("Rectangulo")) {
                        shape = new Rectangle(x2-x1, y2-y1, x1, y1, clr);          
                    }
                    if (tipo.equals("Cuadrado")) {
                        int side = y2 - y1 > x2 - x1? y2 - y1: x2 - x1;
                        shape = new Square(side, x1, y1, clr);
                    }
                    if (tipo.equals("Elipse")) {
                        shape = new Ellipse(x2-x1, y2-y1, x1, y1, clr);
                    }
                    if (tipo.equals("Circulo")) {
                        int ratio = y2 - y1 > x2 - x1? y2 - y1: x2 - x1;
                        shape = new Circle(ratio, x1, y1, clr);              
                    }
                    if (tipo.equals("Linea")) {
                        x1 = pointx1;
                        y1 = pointy1;
                        x2 = e.getX();
                        y2 = e.getY();
                        shape = new Line(x2, y2, x1, y1, clr);         
                    }
                    window.getPanel().setMode(1);
                    window.getPanel().setShape(shape);
                    window.getPanel().repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
        
        window.getPanel().addMouseMotionListener(motionListener);
        
        selectImage = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File image = window.getjFileChooser1().getSelectedFile();
                if (image != null) {
                    ImageIcon icon = new ImageIcon(image.getPath());
                    window.getPanel().setImage(icon.getImage());
                    window.getPanel().repaint();
                }
            }
        };
        
        window.getjFileChooser1().addActionListener(selectImage);
        
        loadImage = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.getjFileChooser1().showOpenDialog(null);
            }
        };
        
        window.getjButton10().addActionListener(loadImage);
        
        selectSave = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File save = window.getjFileChooser3().getSelectedFile();
                if (save != null) {
                    Gson json = new GsonBuilder().setPrettyPrinting().create();
                    String serialized = json.toJson(shapes);
                    if (!save.getAbsolutePath().toLowerCase().endsWith(".json"))
                        save = new File(save.getAbsolutePath()+".json");
                    try (FileOutputStream out = new FileOutputStream(save.getAbsolutePath())) {
                        out.write(serialized.getBytes());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        
        window.getjFileChooser3().addActionListener(selectSave);
        
        saveShapes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.getjFileChooser3().showSaveDialog(null);
            }
        };
        
        window.getjButton11().addActionListener(saveShapes);
        
        selectFile = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = window.getjFileChooser2().getSelectedFile();
                if (file != null) {
                    String serialized = "";
                    try (FileReader fileReader = new FileReader(file)) {
                        BufferedReader br = new BufferedReader(fileReader);
                        String line;
                        while ((line=br.readLine())!=null) {                        
                            serialized += line;
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Gson json = new GsonBuilder().setPrettyPrinting().create();
                    ShapesDAO shapesDAO = json.fromJson(serialized, ShapesDAO.class);
                    shapes.clean();
                    for (Rectangle rectangle : shapesDAO.getRectangles()) {
                        shapes.addRectangle(rectangle);
                    }
                    for (Square square : shapesDAO.getSquares()) {
                        shapes.addSquare(square);
                    }
                    for (Ellipse ellipse : shapesDAO.getEllipses()) {
                        shapes.addEllipse(ellipse);
                    }
                    for (Circle circle : shapesDAO.getCircles()) {
                        shapes.addCircle(circle);
                    }
                    for (Line line : shapesDAO.getLines()) {
                        shapes.addLine(line);
                    }

                    window.getPanel().setMode(0);
                    window.getPanel().repaint();
                }
            }
        };
        
        window.getjFileChooser2().addActionListener(selectFile);
        
        loadShapes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.getjFileChooser2().showOpenDialog(null);
            }
        };
        
        window.getjButton12().addActionListener(loadShapes);
    }
    
    private boolean validate() {
        boolean val = !color.equals("") && !tipo.equals("");
        if (!val) {
        }
        return val;
    }
}
