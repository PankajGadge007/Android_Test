package pankaj.com.tabsdemo;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ContactsDetails extends Activity {
    private ImageView imageView;
    private TextView txt_DisplayName;
    private ListView listView_Phone, listView_Email;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> arrayList_Emails, arrayList_Phones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_details);

        imageView = (ImageView) findViewById(R.id.ic_cont);
        txt_DisplayName = (TextView) findViewById(R.id.lblcont_name);
        listView_Email = (ListView) findViewById(R.id.listEmails);
        listView_Phone = (ListView) findViewById(R.id.listMobNum);

        arrayList_Emails = new ArrayList<>();
        arrayList_Phones = new ArrayList<>();

//  here get Contact_Id
        Bundle bundle = getIntent().getExtras();
        int contactid = bundle.getInt("strP");

        setContactPhoto(contactid);
        setName(contactid);
        setEmails(contactid);
        setMobNumbers(contactid);

// to solve ListView in ScrolView problem following method is used
        setListViewHeightBasedOnChildren(listView_Phone);
        setListViewHeightBasedOnChildren(listView_Email);

    }

    private void setMobNumbers(int contactid) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String idColumn = ContactsContract.CommonDataKinds.Phone._ID;
        String idCont_IDColumn = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String phoneColumn = ContactsContract.CommonDataKinds.Phone.DATA;

        Cursor cursor = getContentResolver().query(
                uri,
                new String[]{idColumn, phoneColumn},
                idCont_IDColumn + "=?",
                new String[]{String.valueOf(contactid)},
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String emails = cursor.getString(cursor.getColumnIndex(phoneColumn));
                arrayList_Phones.add(emails);
            } while (cursor.moveToNext());
        }
        arrayAdapter=new ArrayAdapter(getBaseContext(),android.R.layout.simple_list_item_1,arrayList_Phones);
        listView_Phone.setAdapter(arrayAdapter);

    }

    private void setEmails(int contactid) {
        Uri uri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String idColumn = ContactsContract.CommonDataKinds.Email._ID;
        String idCont_IDColumn = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String emailColumn = ContactsContract.CommonDataKinds.Email.DATA;

        Cursor cursor = getContentResolver().query(
                uri,
                new String[]{idColumn, emailColumn},
                idCont_IDColumn + "=?",
                new String[]{String.valueOf(contactid)},
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String emails = cursor.getString(cursor.getColumnIndex(emailColumn));
                arrayList_Emails.add(emails);
//                Toast.makeText(getBaseContext(), "=" + emails, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        arrayAdapter=new ArrayAdapter(getBaseContext(),android.R.layout.simple_list_item_1,arrayList_Emails);
        listView_Email.setAdapter(arrayAdapter);

    }

    private void setName(int contactid) {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String idColumn = ContactsContract.Contacts._ID;
        String nameColumn = ContactsContract.Contacts.DISPLAY_NAME;

        Cursor cursor = getContentResolver().query(
                uri,
                new String[]{idColumn, nameColumn},
                idColumn + "=?",
                new String[]{String.valueOf(contactid)},
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String displayName = cursor.getString(cursor.getColumnIndex(nameColumn));
                txt_DisplayName.setText(displayName);
            } while (cursor.moveToNext());
        }
    }

    private void setContactPhoto(int contactid) {
        Uri my_contact_Uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(contactid));
        InputStream photo_stream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), my_contact_Uri);

        BufferedInputStream buf = new BufferedInputStream(photo_stream);
        Bitmap my_btmp = BitmapFactory.decodeStream(buf);
        imageView.setImageBitmap(my_btmp);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ActionBar.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
