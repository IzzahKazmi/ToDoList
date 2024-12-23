/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.icon;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * *
 * this class creates different icons and sets them to the corresponding buttons.
 * @author Moshiur
 */
public class MyIconPack {

    public final Icon addicon;
    public final Icon deleteicon;
    public final Icon deleteallicon;
    public final Icon saveicon;
    public final Icon exiticon;
    public final Icon abouticon;

    {
        addicon = new ImageIcon(getClass().getResource("add.png"));
        deleteicon = new ImageIcon(getClass().getResource("delete.png"));
        deleteallicon = new ImageIcon(getClass().getResource("deleteall.png"));
        saveicon = new ImageIcon(getClass().getResource("save.png"));
        exiticon = new ImageIcon(getClass().getResource("exit.png"));
        abouticon = new ImageIcon(getClass().getResource("about.png"));
    }

}
