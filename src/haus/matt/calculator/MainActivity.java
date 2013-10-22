package haus.matt.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {
	int nestingLevel = 0;
	GridView buttons;
	TextView display;
	boolean lastNumHasDecimal = false;
	boolean lastNumIsNegative = false;
	private final static String[] buttonTexts = 
		{"C", "/", "X", "del", 
		"7", "8", "9", "-", 
		"4", "5", "6", "+",
		"1", "2", "3", "( )",
		"0", ".", "+/-", "="};
	private final static String[] buttonClasses = {
		"clear", "func", "func", "back",
		"number", "number", "number", "func",
		"number", "number", "number", "func",
		"number", "number", "number", "paren",
		"zero", "dot", "sign", "eval"
	};

	int[] a = new int[0];
	String expression = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buttons = (GridView) findViewById(R.id.buttons);
		display = (TextView) findViewById(R.id.expr);
		ArrayAdapter<String> buttonAdapter = new ArrayAdapter<String>(this, 
				R.layout.button, 
				MainActivity.buttonTexts);
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
		int i = buttons.indexOfChild(v);
		String buttonClass = buttonClasses[i];
		String expr = display.getText().toString();
		if(buttonClass.equals("eval")) {
			eval();
		} else if(buttonClass.equals("clear")) {
			clear();
		} else if(buttonClass.equals("back")) {
			back();
		} else if(buttonClass.equals("paren")) {
			paren();
		} else if(buttonClass.equals("func")) {
			func((String) b.getText());
		} else if(buttonClass.equals("dot")) {
			dot();
		} else if(buttonClass.equals("sign")) {
			sign();
		}
		else {
			if(expr.endsWith(")")) {
				display.setText((String) expr + 'X' + b.getText());
			} else {
				display.setText(expr + b.getText());	
			}
		}
	}
	//TODO this
	private void sign() {
		String expr = display.getText().toString();
		if(expr.length() == 0) {
			display.setText("(-");
			return;
		}
		if(expr.endsWith("(-")) {
			display.setText(expr.substring(0, expr.length() - 2));
			return;
		}
		//now we know we're displaying at least one character
		char c = expr.charAt(expr.length() - 1);
		if(c == ')') {
			display.setText(expr + "X(-");
			return;
		}
		if(isNumericCharacter(c)) {
			int indexOfFinalNonNumericCharacter = expr.length() - 1;
			while(indexOfFinalNonNumericCharacter >= 0) {
				c = expr.charAt(indexOfFinalNonNumericCharacter);
				if(!isNumericCharacter(c)) {//if we've found a non-number...
					if(c == '-') {//is this a negative number?
						if(expr.charAt(indexOfFinalNonNumericCharacter-1) == '(') {
							//delete (-
							display.setText(expr.substring(0, indexOfFinalNonNumericCharacter-1) + expr.substring(indexOfFinalNonNumericCharacter));
						} else {
							display.setText(expr.substring(0, indexOfFinalNonNumericCharacter) + "(-" + expr.substring(indexOfFinalNonNumericCharacter));
						}
						return;
					} else {
						
					}
				}
				indexOfFinalNonNumericCharacter--;
			}
		} else {
			display.setText(expr + ("(-"));
		}
	}

	private void func(String function) {
		String expr = display.getText().toString();
		if(expr.length() > 0) {
			char c = expr.charAt(expr.length() - 1); 
			if(c == ')' || ('0' <= c && c <= '9')) {
				display.setText(expr + function);
			} else if (c == 'X' || c == '/' || c == '+' || c == '-')  {
				display.setText(expr.substring(0, expr.length()-1) + function);
			}
		}
	}

	private void eval() {
		
	}
	private void dot() {
		String expr = display.getText().toString();
		if(expr.length() == 0) {
			display.setText("0.");
			return;
		}
		int index = expr.length() - 1;
		char lastChar = expr.charAt(index);
		if(expr.endsWith(".")) {
			return;
		}
		if(expr.endsWith(")")) {
			display.setText(expr + "X0.");
			return;
		}
		if(isFunc(lastChar)) {
			display.setText(expr + "0.");
			return;
		}
		boolean canAppendDot = true;
		char c = lastChar;
		//search backwards through the expression and see if I can append a dot or not
		while(index >= 0 && isNumericCharacter(c) && canAppendDot) {
			c = expr.charAt(index);
			if(c == '.') {
				canAppendDot = false;
			}
			index--;
		}
		if(canAppendDot) {
			display.setText(expr + ".");
		}
	}
	private void clear() {
		display.setText("");
	}
	private void back() {
		String text = display.getText().toString();
		if(text.length() == 0) {
			return;
		}
		display.setText(text.substring(0, text.length() - 1));
	}
	private void paren() {
		String text = display.getText().toString();
		if(text.length() == 0) {
			display.setText("(");
		}
		else {
			char c = text.charAt(text.length()-1);
			if(c == ')' || ('0' <= c && c <= '9')) {
				display.setText(text + ")");
			} else {
				display.setText(text + "(");
			}
		}
	}
	private boolean isFunc(char c) {
		return c == '+' || c == '-' || c == 'X' || c == '/';
	}
	private boolean isNumericCharacter(char c) {
		return c == '.' || isDigit(c);
	}
	private boolean isDigit(char c) {
		return '0' <= c && c <= '9';
	}
}
