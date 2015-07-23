package intership.dev.pageadapter;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import intership.dev.contact.R;
import intership.dev.customeview.CustomeCycleImageView;
import intership.dev.fragment.ContactFragment;
import intership.dev.fragment.ContactsFragment;
import intership.dev.item.ContactItem;
import intership.dev.sqlite.ContactSqlite;

/**
 * Created by hoangthuan on 7/21/2015.
 */
public class ContactsPageAdapter extends BaseAdapter {

    private ArrayList<ContactItem> mContacts;
    private LayoutInflater mInflater;
    private SQLiteDatabase mDb;
    private ContactSqlite mContactSqlite;
    private Context mContext;

    /**
     *
     * @param mContext
     * @param mContacts
     */
    public ContactsPageAdapter(Context mContext ,ArrayList<ContactItem> mContacts){
        this.mContacts = mContacts;
        this.mInflater = LayoutInflater.from(mContext);
        this.mContactSqlite = new ContactSqlite(mContext);
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
    public View getView(final int position, View converView, ViewGroup viewGroup) {
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
        holder.imgEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                ContactItem contactItem = mContacts.get(position);
                Fragment fragment = new ContactFragment();
                FragmentManager fragmentManager = ((FragmentActivity) ContactsFragment.GET_ACTIVITY).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putSerializable("SEND_DATA",contactItem);
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.flFragmentPlace, fragment);
                fragmentTransaction.commit();
            }
        });
        holder.imgDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(ContactsFragment.GET_ACTIVITY,position);
            }
        });


        return view;
    }

    class Holder
    {
        CustomeCycleImageView imgAvata;
        TextView tvNameContact;
        ImageView imgEdit;
        ImageView imgDelete;
    }

    /**
     * methor create dialog
     * @param context
     * @param postion of list contact
     */
    private void showPopup( Activity context, final int postion) {
        int popupWidth = 400;
        int popupHeight = 200;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.llDialog);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.custome_dialog, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 30;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // Getting a reference to Close button, and close the popup when clicked.
        TextView tvNotice = (TextView)layout.findViewById(R.id.tvNotice);
        String htmltext = "Are you sure you want to </br> delete <b>" +mContacts.get(postion).getmUsername()+ " ? </b>";
        Spanned sp = Html.fromHtml(htmltext);
        tvNotice.setText(sp);
        LinearLayout llCannel = (LinearLayout) layout.findViewById(R.id.llCannel);
        llCannel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();

            }
        });
        LinearLayout llOk = (LinearLayout) layout.findViewById(R.id.llOk);
        llOk.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mDb= mContactSqlite.getWritableDatabase();
                if(mContactSqlite.deleteContact(mContacts.get(postion).getmId())){
                    Toast.makeText(ContactsFragment.GET_ACTIVITY,R.string.messages_noti_remove_success,Toast.LENGTH_LONG).show();
                    mContacts.remove(postion);
                    notifyDataSetChanged();
                    popup.dismiss();
                }


            }
        });
    }

}
