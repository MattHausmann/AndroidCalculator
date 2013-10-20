package haus.matt.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	private final OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			System.out.println("THERE HAS BEEN A CLICK AT " + position);
		}
	};

	private final static String[] buttons = 
		{"C", "/", "X", "<-", 
		"7", "8", "9", "-", 
		"4", "5", "6", "+",
		"1", "2", "3", "( )",
		"0", ".", "+/-", "="};
	private final static String[] buttonClasses = {
		"clear", "divide", "multiply", "back",
		"number", "number", "number", "minus",
		"number", "number", "number", "plus",
		"number", "number", "number", "paren",
		"zero", "dot", "sign", "eval"
	};

	int[] a = new int[0];
	String expression = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButtonGridView buttons = (ButtonGridView) findViewById(R.id.buttons);
		buttons.setOnItemClickListener(listener);
		CalculatorButton[] calculatorButtons = new CalculatorButton[MainActivity.buttons.length];
		for(int i = 0; i < calculatorButtons.length; i++) {
			calculatorButtons[i] = new CalculatorButton(this, this.getResources().getLayout(R.layout.button));
			calculatorButtons[i].setText(MainActivity.buttons[i]);
			calculatorButtons[i].setType(MainActivity.buttonClasses[i]);
			System.out.println("calculatorButtons[" + i + "]: " + calculatorButtons[i]);
		}
		ArrayAdapter<String> buttonAdapter = new ArrayAdapter<String>(this, 
				R.layout.button, 
				MainActivity.buttons);
		buttons.setAdapter(buttonAdapter);
		System.out.println("buttonAdapter.getCount():" + buttonAdapter.getCount());
		TextView tv = (TextView) findViewById(R.id.expr);
		tv.setText("buttonAdapter.getCount(): " + buttonAdapter.getCount());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void whoooo(View v) {
		CalculatorButton cb = (CalculatorButton) v;
		TextView tv = (TextView) findViewById(R.id.expr);
		System.out.println("a");
		GridView gr = (GridView) findViewById(R.id.buttons);
		System.out.println("b");
		Integer i = gr.indexOfChild(v);
		tv.setText(MainActivity.buttonClasses[i]);
		System.out.println(v);
	}
	
	public void a(View v) {
		System.out.println("I am inside the a method");
	}
	
	
}
