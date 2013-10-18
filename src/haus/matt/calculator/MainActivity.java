package haus.matt.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity {

	private final static String[] buttons = 
		{"C", "/", "X", "<-", 
		"7", "8", "9", "-", 
		"4", "5", "6", "+",
		"1", "2", "3", "( )",
		"0", ".", "+/-", "="};
	String expression = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GridView buttons = (GridView) findViewById(R.id.buttons);
		ArrayAdapter<String> buttonAdapter = new ArrayAdapter<String>(this, R.layout.button, MainActivity.buttons);
		buttons.setAdapter(buttonAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void whoooo(View v) {
		Button b = (Button) v;
		String s = (String) b.getText();
		TextView t = (TextView) findViewById(R.id.expr);
		t.setText(s);
	}
	
	
	
	
}
