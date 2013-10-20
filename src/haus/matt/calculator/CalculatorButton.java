package haus.matt.calculator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class CalculatorButton extends Button {
	private String type;

	public CalculatorButton(Context context) {
		super(context);
		type="";
	}
	
	public CalculatorButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		type="";
	}

	public CalculatorButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public CalculatorButton(Context context, String type) {
		super(context);
		this.type = type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		GridView g = (GridView) getParent();
		return "text: " + this.getText() + "; type: " + this.type;
	}
}
