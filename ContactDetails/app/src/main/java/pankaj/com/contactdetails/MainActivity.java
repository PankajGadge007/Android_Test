package pankaj.com.contactdetails;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> contactsName;
    private ArrayList<Integer> contactsIds;
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listContacts);
        btnTest=(Button)findViewById(R.id.buttonTest);
        contactsName = new ArrayList<>();
        contactsIds = new ArrayList<>();

        displayContactNames();

        listView.setOnItemClickListener(this);
        btnTest.setOnClickListener(this);

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

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        int cont_id = contactsIds.get(position);
//        Toast.makeText(getBaseContext(),"ID="+cont_id,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ContactDetails.class);
        intent.putExtra("strP", cont_id);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Testing Task");
        builder.setMessage("There is nothing for testing now!");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Testing Done...",Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
