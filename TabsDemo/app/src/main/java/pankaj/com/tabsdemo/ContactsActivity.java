package pankaj.com.tabsdemo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pankaj on 2/23/2015.
 */
public class ContactsActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private TextView txtContName;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> contactsName;
    private ArrayList<Integer> contactsIds;
    private static long contact_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_layout);

        listView = (ListView) findViewById(R.id.listContacts);
        txtContName = (TextView) findViewById(R.id.txtContactName);

        contactsName = new ArrayList<>();
        contactsIds = new ArrayList<>();

        displayContactNames();

        listView.setOnItemClickListener(this);
    }

    private void displayContactNames() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String idColumn = ContactsContract.Contacts._ID;
        String nameColumn = ContactsContract.Contacts.DISPLAY_NAME;

        Cursor cursor = getContentResolver().query(uri, new String[]{idColumn, nameColumn}, null, null, nameColumn + " ASC");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                contactsName.add(cursor.getString(cursor.getColumnIndex(nameColumn)));
                contactsIds.add(cursor.getInt(cursor.getColumnIndex(idColumn)));
            } while (cursor.moveToNext());
        }
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, contactsName);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int cont_id = contactsIds.get(position);
//        Toast.makeText(getBaseContext(),"ID="+cont_id,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ContactsDetails.class);
        intent.putExtra("strP", cont_id);
        startActivity(intent);
    }
}


