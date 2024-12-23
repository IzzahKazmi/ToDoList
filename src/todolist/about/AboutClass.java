/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.about;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

/***
 * this class Creates the about window when the button about
 * is pressed..
 * @author Moshiur
 */

public class AboutClass extends JFrame{

    private JLabel aboutlabel;
    public AboutClass(){
        
        super("About");
        setLayout(new GridLayout(7,1));
        Icon myicon = new ImageIcon(getClass().getResource("me.jpg"));
        aboutlabel = new JLabel();
        aboutlabel.setIcon(myicon);
        Font ff = new Font("Trebuchet MS",Font.BOLD,14);
        aboutlabel.setFont(ff);
        aboutlabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        aboutlabel.setHorizontalTextPosition(SwingConstants.CENTER);
    //   add(aboutlabel);
        add(new JLabel(" Creators : MAZI")).setFont(ff);
        add(new JLabel(" Course : BESE-28 ")).setFont(ff);
        add(new JLabel(" Contact : mazi@gmail.com ")).setFont(ff);
        add(new JLabel(" Software Construction ")).setFont(ff);
        add(new JLabel(" (Open-Ended Lab) ")).setFont(ff);
        add(new JLabel(" MCS ")).setFont(ff);
        add(new JLabel(" NUST ")).setFont(ff);
        
    } 
    
//    public static void main(String args[]){
//        AboutClass ab= new AboutClass();
//        ab.setSize(300, 400);
//        ab.setVisible(true);
//    }
}
