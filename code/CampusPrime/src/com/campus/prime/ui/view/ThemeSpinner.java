package com.campus.prime.ui.view;



import com.campus.prime.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ThemeSpinner extends Spinner
{
		
	private static Boolean isDark=true;
	
	private static final int [] light_mode = {R.attr.state_light}; 
	private static final int [] dark_mode = {R.attr.state_dark};
	int textColor = R.color.text;
	int textSize = R.dimen.form_text_size;
	

	public ThemeSpinner(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	public ThemeSpinner(Context context,AttributeSet attrs)
	{
		super(context,attrs);
		// TODO Auto-generated constructor stub	
		
		
		
	}
	public ThemeSpinner(Context context, AttributeSet attrs,int defStyle)
	{
		super(context,attrs,defStyle);
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	@Override
	protected int[] onCreateDrawableState(int extraSpace) 
	{
	    final int[] drawableState = super.onCreateDrawableState(extraSpace + 2);
	    if(isDark == true)
	    	mergeDrawableStates(drawableState,dark_mode);
	    else
	    	mergeDrawableStates(drawableState,light_mode);
	    return drawableState;
	}
	
	
	
	    

	

}
	




