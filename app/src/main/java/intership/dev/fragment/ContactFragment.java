package intership.dev.fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import intership.dev.contact.MainActivity;
import intership.dev.contact.R;
import intership.dev.customeview.CustomeCycleImageView;
import intership.dev.item.ContactItem;
import intership.dev.sqlite.ContactSqlite;

/**
 * Created by hoangthuan on 7/22/2015.
 */
public class ContactFragment extends Fragment implements View.OnClickListener{
    private CustomeCycleImageView customeCycleImageView;
    private TextView tvNameContact;
    private EditText edtNameContact;
    private EditText edtDescription;
    private ContactSqlite mContactSqlite;
    private LinearLayout llCannel;
    private LinearLayout llSave;
    private SQLiteDatabase mDb;
    private ContactItem mContactItem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact,container,false);
        init(view);
        display();
        return  view;
    }

    /**
     * get id for component from file xml
     * @param view
     */

    public void init(View view){
        mContactItem = (ContactItem)getArguments().getSerializable("SEND_DATA");
        mContactSqlite = new ContactSqlite(getActivity());
        customeCycleImageView = (CustomeCycleImageView)view.findViewById(R.id.custom_ImageView);
        tvNameContact = (TextView)view.findViewById(R.id.tvNameContact);
        edtDescription = (EditText)view.findViewById(R.id.edtDeseription);
        edtNameContact = (EditText)view.findViewById(R.id.edtNameContact);
        llCannel = (LinearLayout)view.findViewById(R.id.llCannel);
        llCannel.setOnClickListener(this);
        llSave = (LinearLayout)view.findViewById(R.id.llSave);
        llSave.setOnClickListener(this);
    }

    /**
     * display data into layout
     */
    public  void display(){
        customeCycleImageView.setImageResource(mContactItem.getmAvata());
        tvNameContact.setText(mContactItem.getmUsername());
        edtNameContact.setText(mContactItem.getmUsername());
        edtDescription.setText(mContactItem.getmDecreption());
    }

    @Override
    public void onClick(View view) {
        if(view == llCannel){
            this.getActivity().onBackPressed();
        }
        if(view == llSave){
         mDb = mContactSqlite.getWritableDatabase();
            if(mContactSqlite.updateContact(mContactItem.getmId(),edtNameContact.getText().toString(),edtDescription.getText().toString())){
                Toast.makeText(getActivity(),R.string.messages_noti_update_success,Toast.LENGTH_LONG).show();
                this.getActivity().onBackPressed();
            }
            else {
                Toast.makeText(getActivity(),R.string.messages_noti_update_error,Toast.LENGTH_LONG).show();
            }
        }

    }
}
