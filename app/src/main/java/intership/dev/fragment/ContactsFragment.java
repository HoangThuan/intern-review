package intership.dev.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.item.ContactItem;
import intership.dev.pageadapter.ContactsPageAdapter;

/**
 * Created by hoangthuan on 7/21/2015.
 */
public class ContactsFragment extends Fragment {
    private ListView lvContacts;
    private ArrayList <ContactItem> mContacts;
    private ContactsPageAdapter mContactsPageAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts,container,false);
        init(view);
        getData();
        display();
        return view;
    }


    public void init(View view){
        lvContacts = (ListView) view.findViewById(R.id.lvContacts);
        mContacts = new ArrayList<ContactItem>();

    }

    public  void getData(){
        for(int i=0;i<5;i++){
            mContacts.add(new ContactItem("Hoang thuan","noi em den xa lam",R.mipmap.img_contact1));
            mContacts.add(new ContactItem("Vui Vui","noi em den xa lam",R.mipmap.img_contact2));
        }

    }
    public  void display(){
        mContactsPageAdapter = new ContactsPageAdapter(getActivity(),mContacts);
        lvContacts.setAdapter(mContactsPageAdapter);
    }


}
