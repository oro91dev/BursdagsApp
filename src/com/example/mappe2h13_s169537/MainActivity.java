package com.example.mappe2h13_s169537;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	DBAdapter db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.birthdayreminder1);
		setContentView(R.layout.activity_main);

		db = new DBAdapter(this);
		db.open();
		ListView lv = (ListView) findViewById(R.id.listView1);
		Cursor c = db.FindAll();
		Contact[] cont = new Contact[c.getCount()];
		if (c.moveToFirst()) {
			int j = 0;
			do {

				cont[j] = new Contact(Integer.parseInt(c.getString(0)),
						c.getString(1), c.getString(2), c.getString(3),
						c.getString(4), c.getString(5));
				j++;
				// show(c.getString(0));
			} while (c.moveToNext());
			// show( c.getString( 2 ) + " " + c.getString( 3 ) );
		}
		lv.setAdapter(new CustomBaseAdapter(this, cont));
		db.close();
		c.close();

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				db.open();
				ListView lv = (ListView) findViewById(R.id.listView1);
				Contact ct = (Contact) parent.getItemAtPosition(position);
				Cursor c = db.FindUser(ct.getPhonenumber());

				if (c.moveToFirst()) {
					do {
						String[] intentExtra = { c.getString(0),
								c.getString(1), c.getString(2), c.getString(3),
								c.getString(4), c.getString(5) };

						Intent intent = new Intent(getBaseContext(),
								Editcontact.class);
						intent.putExtra("contactId", intentExtra);
						startActivity(intent);

					} while (c.moveToNext());
				}
				c.close();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showAddContact(View view) {
		Intent theIntent = new Intent(this, Addcontact.class);
		startActivity(theIntent);

	}

}
