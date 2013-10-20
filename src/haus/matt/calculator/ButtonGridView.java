package haus.matt.calculator;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

public class ButtonGridView extends GridView {


	public void setAdapter(ButtonAdapter b) {
		super.setAdapter(b);
	}
	public ButtonGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public void a(View v) {
		System.out.println("I'm inside ButtonGridView's a method");
	}
	
	
	

}
