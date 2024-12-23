/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.main;

import java.awt.BorderLayout;
import todolist.customframe.MyFrame;
import todolist.threads.TimeThread;
import todolist.threads.GarbageCollectionThread;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

/***
 * This is the main class.contains the main method.
 * @version 1.6
 * @author Siyam
 */
public class ToDoList {

    public static void main(String[] args) {
        // TODO code application logic here
        // Creates the main window
        final MyFrame tdl = new MyFrame();
        tdl.setSize(850, 480);
        tdl.setMinimumSize(new Dimension(600, 510));
        //  mmrs.setResizable(false);
        
        tdl.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dim.getWidth() - tdl.getWidth()) / 2);
        int y = 100;
        tdl.setLocation(x, y);
        tdl.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    
        // new TimeThread(mmrs);
        Timer timer1 = new Timer();
        TimeThread timerthreadob = new TimeThread(tdl);    // to update the time in the bottom panel after each 1 second
        timer1.schedule(timerthreadob, 2000, 1000);

        //   new  garbagecollectionthread();
    }
}
