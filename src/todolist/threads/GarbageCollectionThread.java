/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.threads;

/**
 *
 * @author Siyam
 */


/*
used firstly when TimeThread was creating manually to prevent
memory leackage..
but calling System.gc() is a bad practice..
*/
public class GarbageCollectionThread implements Runnable {

    public GarbageCollectionThread() {

        Thread t = new Thread(this, "garbage collection thread");
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100000);    // 1 minutes
                System.gc();
            } catch (InterruptedException ex) {

            }
        }

    }

}
