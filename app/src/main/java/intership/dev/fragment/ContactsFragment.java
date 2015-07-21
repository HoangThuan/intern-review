package intership.dev.fragment;

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
import intership.dev.pageadapter.ContactsPageAdapter;

/**
 * Created by hoangthuan on 7/21/2015.
 */
public class ContactsFragment extends Fragment {
    private ListView lvContacts;
    private ContactsPageAdapter mContactsPageAdapter;
    private ArrayList <ContactItem> mContacts;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts,container,false);
        init(view);
        new  getData().execute();
        return view;
    }


    public void init(View view){
        lvContacts = (ListView) view.findViewById(R.id.lvContacts);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        progressBar.setVisibility(getView().VISIBLE);

    }



    private class getData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
               mContacts = new ArrayList<ContactItem>();;
            for(int i=0;i<5;i++){
                mContacts.add(new ContactItem("Hoang thuan","noi em den xa lam",R.mipmap.img_contact1));
                mContacts.add(new ContactItem("Vui Vui","noi em den xa lam",R.mipmap.img_contact2));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mContactsPageAdapter = new ContactsPageAdapter(getActivity(),mContacts);
            lvContacts.setAdapter(mContactsPageAdapter);
            lvContacts.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {
                    int count=lvContacts.getCount();

                    if(i==SCROLL_STATE_IDLE){
                        if(lvContacts.getLastVisiblePosition()>=count-1){
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
    private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(getView().VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            if(mContacts.size()<40) {
                for (int i = 0; i < 5; i++) {
                    mContacts.add(new ContactItem("Hoang thuan", "noi em den xa lam", R.mipmap.img_contact1));
                    mContacts.add(new ContactItem("Vui Vui", "noi em den xa lam", R.mipmap.img_contact2));
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            int position = lvContacts.getLastVisiblePosition();
            mContactsPageAdapter = new ContactsPageAdapter(getActivity(),mContacts);
            lvContacts.setAdapter(mContactsPageAdapter);

            lvContacts.setSelectionFromTop(position, 0);
            progressBar.setVisibility(getView().GONE);
        }
    }

}



