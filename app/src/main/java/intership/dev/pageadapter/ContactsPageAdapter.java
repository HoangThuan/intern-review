package intership.dev.pageadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.customeview.CustomeCycleImageView;
import intership.dev.item.ContactItem;

/**
 * Created by hoangthuan on 7/21/2015.
 */
public class ContactsPageAdapter extends BaseAdapter {

    private ArrayList<ContactItem> mContacts;
    private LayoutInflater mInflater;
    private Context mContext;

    /**
     *
     * @param mContext
     * @param mContacts
     */
    public ContactsPageAdapter(Context mContext ,ArrayList<ContactItem> mContacts){
        this.mContacts = mContacts;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mContacts.size();
    }

    @Override
    public ContactItem getItem(int position) {
        return mContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup viewGroup) {
        Holder holder = null;
        View view = converView;
        if(view == null ){
            holder = new Holder();
            view = mInflater.inflate(R.layout.item_list_contact,viewGroup,false);
            holder.imgAvata = (CustomeCycleImageView)view.findViewById(R.id.imgCustomeCycleImage);
            holder.tvNameContact = (TextView)view.findViewById(R.id.tvNameContact);
            holder.imgEdit = (ImageView)view.findViewById(R.id.imgEdit);
            holder.imgDelete = (ImageView)view.findViewById(R.id.imgDelete);
            view.setTag(holder);
        }
        else{
            holder = (Holder)view.getTag();
        }
        holder.imgAvata.setImageResource(mContacts.get(position).getmAvata());
        holder.tvNameContact.setText(mContacts.get(position).getmUsername());

        return view;
    }

    class Holder
    {
        CustomeCycleImageView imgAvata;
        TextView tvNameContact;
        ImageView imgEdit;
        ImageView imgDelete;
    }

}
