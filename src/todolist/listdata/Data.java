/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.listdata;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/***
 * This class manages the data.how to set or get information.
 *
 * @author Siyam
 */
public class Data implements Serializable {

    private boolean flag;
    private String title = "";
    private Date time;
    private String details = "";

    /***
     * this constructor takes title,time and details as parameter.. and set the
     * details. initilally the task is asumed un done.so flag = false.
     *
     * @param title title of the task
     * @param time  adding time of the task
     * @param details details of the task
     */
    public Data(String title, Date time, String details) {

        this.title = title;
        this.time = time;
        this.details = details;
        flag = false;
    }

    /***
     *this method returns the tile of the task..
     *@return title of the task
    
     */
    public String getTitle() {
        return title;
    }
   /***
    * this method sets the title of the task..
    * @param  title title of the task
    */

    public void setTitle(String title) {
        this.title = title;
    }
    /***
     this method returns the time of adding the task..
     @return Date object of java.util.Date class..
  
     */

    public Date getTime() {
        return time;
    }
    /***
     this method returns the details of the task..
     @return details task details.
    
     */

    public String getDetails() {
        return details;
    }
    /***
     *this method sets the details of the task..
     *@param details details of the task
     */

    public void setDetails(String details) {
        this.details = details;
    }

    /***
     this method sets the status of the task..
     @param flag status of the task weather it is done or not.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /***
     this method returns the status of the task..
     @return true if done else false..
     */
    public boolean getFlag() {
        return flag;
    }
    /***
     *this method returns a new object of this class with the informations.
     *public Object[] getData() :
     *returns full information of task.will be used in the loadlist() in MyFrame class to entry the infomation in the table in the start up
     *of the program.
     *@return object with the info of title,time,details,task status.
     */

    public Object[] getData() {

        String time_str = "    " + new SimpleDateFormat("MMM dd, hh:mm aa, zzz, yyyy").format(time);
        return new Object[]{flag, title, time_str, details};

    }

    /***
     *this method is
     *checking the date of adding the task the passed or not.
     *if passed and task is done( flag = true)
     *then task will not added to the list.
     *@return true if done, else false.
     */
    
    @SuppressWarnings("deprecation")
    public boolean isPast() {
        if (!flag) {
            return false;
        }
        Date now = new Date();
        if (now.before(time)) {
            return false;
        }
        return (now.getDate() != time.getDate());

    }

//    public boolean  isPast(){
//        return false;
//    }
}
