package intership.dev.fragment;

import android.app.Activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.item.ContactItem;
import intership.dev.pageadapter.ContactPageAdapter;
import intership.dev.sqlite.ContactSqlite;

/**
 * Created by hoangthuan on 7/21/2015.
 */
public class ContactsFragment extends Fragment {
    private ListView mLvContacts;
    private ContactPageAdapter mContactsPageAdapter;
    private ArrayList <ContactItem> mContacts;
    private ProgressBar mProgressBar;
    public static  Activity sActivity;
    private SQLiteDatabase mDb;
    private ContactSqlite mContactSqlite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts,container,false);
        init(view);
        setDataForSqlite();
        new getData().execute();
        return view;
    }

    /**
     * get id for component from file xml
     * @param view
     */
    public void init(View view){
        mContactSqlite = new ContactSqlite(this.getActivity());
        sActivity=this.getActivity();
        mLvContacts = (ListView) view.findViewById(R.id.lvContacts);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(getView().VISIBLE);

    }

    /**
     * insert data into table in sqlite for fisrt start
     */
    public void setDataForSqlite(){
        for(int i = 0 ;i<10;i++){
            mDb = mContactSqlite.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ContactSqlite.C_USERNAME,"Hoang Thuan");
            values.put(ContactSqlite.C_DESCRIPTION,"duoc di hoc la mot vinh du lon");
            values.put(ContactSqlite.C_AVATA,R.mipmap.img_contact1);
            mDb.insert(ContactSqlite.TABLE, null, values);
            mDb.close();
            mDb = mContactSqlite.getWritableDatabase();
            ContentValues values1 = new ContentValues();
            values1.put(ContactSqlite.C_USERNAME,"Hoang Ngoc");
            values1.put(ContactSqlite.C_DESCRIPTION,"noi em den ngoai dao xa nen em den la bien xa");
            values1.put(ContactSqlite.C_AVATA,R.mipmap.img_contact2);
            mDb.insert(ContactSqlite.TABLE,null,values1);
            mDb.close();
        }

    }

    /**
     * class get data from table in Sqlite with limit equas ten
     */

    private class getData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
               mContacts = new ArrayList<ContactItem>();
               int count = mLvContacts.getCount();
               count += 10;
               mContacts = mContactSqlite.getAllContact(""+count);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mContactsPageAdapter = new ContactPageAdapter(getActivity(),mContacts);
            mLvContacts.setAdapter(mContactsPageAdapter);
            mProgressBar.setVisibility(getView().GONE);
            mLvContacts.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {
                    int count = mLvContacts.getCount();

                    if(i==SCROLL_STATE_IDLE){
                        if(mLvContacts.getLastVisiblePosition()>=count-1){
                            new LoadMoreDataTask().execute();
                        }
                    }
                }
                @Override
                public void onScroll(AbsListView absListView, int i, int i1, int i2) {

                }
            });

        }
    }

    /**
     * class load more data , add data into listview when
     * listview execute event onScrollStateChanged
     */
    private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(getView().VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            mContacts = new ArrayList<ContactItem>();
            int count = mLvContacts.getCount();
            count+=10;
            mContacts=mContactSqlite.getAllContact(""+count);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            int position = mLvContacts.getLastVisiblePosition();
            mContactsPageAdapter = new ContactPageAdapter(getActivity(),mContacts);
            mLvContacts.setAdapter(mContactsPageAdapter);
            mLvContacts.setSelectionFromTop(position, 0);
            mProgressBar.setVisibility(getView().GONE);
        }
    }

}



