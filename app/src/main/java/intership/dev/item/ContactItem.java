package intership.dev.item;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by hoangthuan on 7/21/2015.
 */
public class ContactItem implements Serializable {
    private int mId;
    private String mUsername;
    private String mDecreption;
    private int    mAvata;

    /**
     *
     * @param mUsername
     * @param mDecreption
     * @param mAvata
     */
    public ContactItem (int mId,String mUsername,String mDecreption,int mAvata){
        this.mId = mId;
        this.mUsername = mUsername;
        this.mDecreption = mDecreption;
        this.mAvata = mAvata;

    }
    public  ContactItem (){

    }

    /**
     *
     * @return mAvata
     */

    public int getmAvata() {
        return mAvata;
    }

    public String getmDecreption() {
        return mDecreption;
    }

    public int getmId() {
        return mId;
    }

    public String getmUsername() {
        return mUsername;
    }
    public  void setmUsername(String mUsername) {
        this.mUsername=mUsername;
    }
    public  void setmDecreption(String mDecreption){
        this.mDecreption = mDecreption;
    }
}
