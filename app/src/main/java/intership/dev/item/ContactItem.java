package intership.dev.item;

import android.content.Context;

/**
 * Created by hoangthuan on 7/21/2015.
 */
public class ContactItem {
    private String mUsername;
    private String mDecreption;
    private int    mAvata;

    /**
     *
     * @param mUsername
     * @param mDecreption
     * @param mAvata
     */
    public ContactItem (String mUsername,String mDecreption,int mAvata){
        this.mUsername = mUsername;
        this.mDecreption = mDecreption;
        this.mAvata = mAvata;

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

    public String getmUsername() {
        return mUsername;
    }
}
