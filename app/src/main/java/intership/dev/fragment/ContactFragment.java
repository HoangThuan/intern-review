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
    private CustomeCycleImageView mCustomeCycleImageView;
    private TextView mTvNameContact;
    private EditText mEdtNameContact;
    private EditText mEdtDescription;
    private ContactSqlite mContactSqlite;
    private LinearLayout mLlCannel;
    private LinearLayout mLlSave;
    private SQLiteDatabase mDb;
    private ContactItem mContactItem;
    private LinearLayout mLlBack;
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
        mCustomeCycleImageView = (CustomeCycleImageView)view.findViewById(R.id.custom_ImageView);
        mTvNameContact = (TextView)view.findViewById(R.id.tvNameContact);
        mEdtDescription = (EditText)view.findViewById(R.id.edtDeseription);
        mEdtNameContact = (EditText)view.findViewById(R.id.edtNameContact);
        mLlCannel = (LinearLayout)view.findViewById(R.id.llCannel);
        mLlCannel.setOnClickListener(this);
        mLlSave = (LinearLayout)view.findViewById(R.id.llSave);
        mLlSave.setOnClickListener(this);
        mLlBack = (LinearLayout)view.findViewById(R.id.llBack);
        mLlBack.setOnClickListener(this);
    }

    /**
     * display data into layout
     */
    public  void display(){
        mCustomeCycleImageView.setImageResource(mContactItem.getmAvata());
        mTvNameContact.setText(mContactItem.getmUsername());
        mEdtNameContact.setText(mContactItem.getmUsername());
        mEdtDescription.setText(mContactItem.getmDecreption());
    }

    @Override
    public void onClick(View view) {
        if(view == mLlCannel){
            this.getActivity().onBackPressed();
        }
        if(view == mLlSave){
         mDb = mContactSqlite.getWritableDatabase();
            if(mContactSqlite.updateContact(mContactItem.getmId(),mEdtNameContact.getText().toString(),mEdtDescription.getText().toString())){
                Toast.makeText(getActivity(),R.string.messages_noti_Contact_update_success,Toast.LENGTH_LONG).show();
                this.getActivity().onBackPressed();
            }
            else {
                Toast.makeText(getActivity(),R.string.messages_noti_Contact_dont_update,Toast.LENGTH_LONG).show();
            }
        }
        if(view == mLlBack){
            this.getActivity().onBackPressed();
        }

    }
}
