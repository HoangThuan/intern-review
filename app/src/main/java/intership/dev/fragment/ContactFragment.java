package intership.dev.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import intership.dev.contact.MainActivity;
import intership.dev.contact.R;
import intership.dev.customeview.CustomeCycleImageView;

/**
 * Created by hoangthuan on 7/22/2015.
 */
public class ContactFragment extends Fragment {
    private CustomeCycleImageView customeCycleImageView;
    private TextView tvNameContact;
    private EditText edtNameContact;
    private EditText edtDescription;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact,container,false);
        init(view);
        display();
        return  view;
    }

    public void init(View view){
        customeCycleImageView = (CustomeCycleImageView)view.findViewById(R.id.custom_ImageView);
        tvNameContact = (TextView)view.findViewById(R.id.tvNameContact);
        edtDescription = (EditText)view.findViewById(R.id.edtDeseription);
        edtNameContact = (EditText)view.findViewById(R.id.edtNameContact);
    }

    public  void display(){
        customeCycleImageView.setImageResource(MainActivity.CONTAC_ITEM.getmAvata());
        tvNameContact.setText(MainActivity.CONTAC_ITEM.getmUsername());
        edtNameContact.setText(MainActivity.CONTAC_ITEM.getmUsername());
        edtDescription.setText(MainActivity.CONTAC_ITEM.getmDecreption());
    }
}
