package com.example.mappe2h13_s169537;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Editcontact extends Activity {
	DBAdapter db;
	EditText firstName;
	EditText lastName;
	EditText phoneNumber;
	EditText birthday;
	EditText message;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editcontact);
		db = new DBAdapter(this);
		db.open();
		firstName = (EditText) findViewById(R.id.firstName);
		lastName = (EditText) findViewById(R.id.lastName);
		phoneNumber = (EditText) findViewById(R.id.phoneNumber);
		birthday = (EditText) findViewById(R.id.birthday);
		message = (EditText) findViewById(R.id.message);

		Intent theIntent = getIntent();

		String[] contactId = theIntent.getStringArrayExtra("contactId");
		firstName.setText(contactId[1]);
		lastName.setText(contactId[2]);
		phoneNumber.setText(contactId[3]);
		birthday.setText(contactId[4]);
		message.setText(contactId[5]);
	}

	public void editContact(View v) {
		if (valider()) {
			String firstname = firstName.getText().toString();
			String lastname = lastName.getText().toString();
			String phone = phoneNumber.getText().toString();
			String birthDay = birthday.getText().toString();
			String messages = message.getText().toString();
			db.update(firstname, lastname, phone, birthDay, messages);
			this.callMainActivity(v);
			Toast.makeText(this, R.string.edited, Toast.LENGTH_LONG).show();
		}
	}

	public void removeContact(View v) {
		String phone = phoneNumber.getText().toString();
		db.delete(phone);
		this.callMainActivity(v);
	}

	public void callMainActivity(View v) {
		Intent theIntent = new Intent(this, MainActivity.class);
		startActivity(theIntent);
	}

	public boolean valider() {
		StringBuilder builder = new StringBuilder();

		if (firstName.getText().toString().matches("")) {
			builder.append(getString(R.string.regexemptyfirstname1) + "\n");
		}

		else if (!firstName.getText().toString().matches("[A-Z][a-zA-Z]*")) {
			builder.append(getString(R.string.regexemptyfirstname2));
		}

		if (lastName.getText().toString().matches("")) {
			builder.append(getString(R.string.regexemptylastname1) + "\n");

		} else if (!lastName.getText().toString().matches("[A-Z][a-zA-Z]*")) {
			builder.append(getString(R.string.regexemptylastname2) + "\n");
		}

		if (phoneNumber.getText().toString().matches("")) {
			builder.append(getString(R.string.regexemptyphonenumber1) + "\n");

		} else if (!phoneNumber.getText().toString().matches("\\d{8}")) {
			builder.append(getString(R.string.regexemptyphonenumber2) + "\n");
		}

		if (birthday.getText().toString().matches("")) {
			builder.append(getString(R.string.regexemptybirthday1) + "\n");

		}

		if (message.getText().toString().matches("")) {
			builder.append(getString(R.string.regexemptymessage) + "\n");

		}

		if (builder.toString().equals("")) {
			return true;
		} else {
			Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
		}
		return false;
	}

}
