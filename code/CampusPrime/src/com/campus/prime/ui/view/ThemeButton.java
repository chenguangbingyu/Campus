package com.campus.prime.ui.view;


import com.campus.prime.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

public class ThemeButton extends Button
{
	private static Boolean isDark = false;
	
	private static final int [] light_mode = {R.attr.state_light};
	private static final int [] dark_mode = {R.attr.state_dark};
	
	public ThemeButton(Context context)
	{
		super(context);
		//TODO Auto-generated constructor stub
		
	}
	public ThemeButton(Context context,AttributeSet attrs)
	{
		super(context,attrs);
		//TODO Auto-generated constructor stub
	}
	public ThemeButton(Context context, AttributeSet attrs, int defStyle) 
	{
		super(context, attrs, defStyle);
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
