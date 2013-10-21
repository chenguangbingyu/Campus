package com.campus.prime.ui.view;



import com.campus.prime.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class Header extends ThemeTextView
{
	
	private static Boolean isDark = true;
	private static final int [] light_mode = {R.attr.state_light};
	private static final int [] dark_mode = {R.attr.state_dark};
	Paint paint = new Paint();
	float lineWidth=1.0f;
	
	public Header(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	public Header(Context context,AttributeSet attrs)
	{
		super(context,attrs);
		// TODO Auto - generated constructor stub
	}
	public Header(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		initPaint(paint);
		
		canvas.drawRect(0.0f, getHeight()-2, getWidth(), getHeight(), paint);
	}
	
	public void initPaint(Paint paint)
	{
		int color = this.getResources().getColor(R.color.sel_header);
		
		paint.setColor(color);
	}
	@Override
	public int[] onCreateDrawableState(int extraSpace)
	{
		int[] drawableState = super.onCreateDrawableState(extraSpace + 2);
	    if(isDark == true)
	    	mergeDrawableStates(drawableState,dark_mode);
	    else
	    	mergeDrawableStates(drawableState,light_mode);
	    
	    return drawableState;
	}
	
	

}
