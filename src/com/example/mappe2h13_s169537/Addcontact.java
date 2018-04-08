package com.example.mappe2h13_s169537;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Addcontact extends Activity {

	DBAdapter db;
	EditText firstName;
	EditText lastName;
	EditText phoneNumber;
	EditText birthday;
	EditText message;

	Calendar myCalendar = Calendar.getInstance();

	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			updateLabel();
		}

	};

	private void updateLabel() {

		String myFormat = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat,
				Locale.getDefault());

		birthday.setText(sdf.format(myCalendar.getTime()));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcontact);
		db = new DBAdapter(this);
		db.open();

		firstName = (EditText) findViewById(R.id.firstName);
		lastName = (EditText) findViewById(R.id.lastName);
		phoneNumber = (EditText) findViewById(R.id.phoneNumber);
		birthday = (EditText) findViewById(R.id.birthday);
		message = (EditText) findViewById(R.id.message);

		birthday.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(Addcontact.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
	}

	public void addNewContact(View v) {
		if (valider()) {
			String firstname = firstName.getText().toString();
			String lastname = lastName.getText().toString();
			String phone = phoneNumber.getText().toString();
			String birthDay = birthday.getText().toString();
			String messages = message.getText().toString();

			ContentValues cv = new ContentValues(5);
			cv.put(db.FIRSTNAME, firstname);
			cv.put(db.LASTNAME, lastname);
			cv.put(db.PHONE, phone);
			cv.put(db.BIRTHDAY, birthDay);
			cv.put(db.MESSAGE, messages);
			db.insert(cv);
			this.callMainActivity(v);
		}
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
