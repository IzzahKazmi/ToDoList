/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.threads;

import java.util.Date;
import java.util.TimerTask;
import todolist.customframe.MyFrame;

/***
 * this class updates the time in the bottom panel.
 * @author Siyam
 */
public class TimeThread extends TimerTask{

    MyFrame temp;

    //Calendar mydate;
    public TimeThread(MyFrame recv) {
     //   Thread t = new Thread(this, "time showing thread");
        temp = recv;
      //  t.start();
    }

    /***
     * this method changes the text after every 1 second.
     */
    @Override
    public void run() {

        temp.getTimeLabel().setText("ToDoList :: SystemTime: " + new Date());
        
        
        /* firstly used thread..to update the time..
        but there is memory leakage problem..
        */

//        while (true) {
//            temp.gettimelabel().setText("ToDoList :: SystemTime: " + new Date());
//            try {
//                Thread.sleep(1000);
//                // System.gc();
//            } catch (InterruptedException ex) {
//
//            }
//        }

    }

}
