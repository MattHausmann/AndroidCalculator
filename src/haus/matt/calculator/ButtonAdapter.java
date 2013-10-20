package haus.matt.calculator;


import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

public class ButtonAdapter extends ArrayAdapter<String> {
	public String[] objects;
	public String[] attributes;
	public CalculatorButton[] buttons;
	private Context context;
	int currentLength = 0;

	public ButtonAdapter(Context context, int resource, String[] objects, String[] attributes) {
		super(context, resource, objects);
		this.attributes = attributes;
		buttons = new CalculatorButton[objects.length];
		for(CalculatorButton cb : buttons) {
			cb = new CalculatorButton(context, context.getResources().getLayout(R.layout.button));
		}
	}
	@Override
	public int getCount() {
		return super.getCount();
	}

	@Override
	public String getItem(int position) {;
		return objects[position];
	}
	@Override
	public long getItemId(int arg0) {
		return super.getItemId(arg0);
	}

	@Override
	public View getView(int position,   
			View convertView, ViewGroup parent) {  
		CalculatorButton btn;  
		if (convertView == null) {    
			// if it's not recycled, initialize some attributes  
			btn = new CalculatorButton(context);   
		}   
		else {  
			btn = (CalculatorButton) convertView;  
		}  
		btn.setText(objects[position]);
		btn.setType(attributes[position]);
		btn.setId(position);

		return btn;  
	}  



}
