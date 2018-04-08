package com.example.mappe2h13_s169537;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
	Activity contactcontext;
	Contact[] contacts;

	public CustomBaseAdapter(Activity contactcontext, Contact[] contacts) {
		this.contactcontext = contactcontext;
		this.contacts = contacts;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contacts.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return contacts[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (view == null) {
			view = contactcontext.getLayoutInflater().inflate(
					android.R.layout.simple_list_item_1, null);
		}
		TextView textview = (TextView) view.findViewById(android.R.id.text1);

		if (contacts[position] != null) {
			String text1 = contacts[position].getLastname();
			String text2 = contacts[position].getFirstname();
			String text3 = contacts[position].getBirthday();

			textview.setText(text3 + "  -  " + text1 + ", " + text2);

		}
		return view;
	}

}
