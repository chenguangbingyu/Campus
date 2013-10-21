package com.campus.prime.ui.view;



import com.campus.prime.R;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

public class LabelEditText extends ThemeEditText
{
	
	private static Boolean isDark = false;
	private static final int [] light_mode = {R.attr.state_light};
	private static final int [] dark_mode = {R.attr.state_dark};

	private String label = "Label";
	
	private Paint paint;
	private int paddingLeft;
	private int paddingTop;
	private int paddingRight;
	private int paddingBottom;
	
	private int finalPaddingTop;
	private int labelLocation;
	
	
	public LabelEditText(Context context)
	{
		super(context);
		this.initPadding();
		//TODO Auto-generated constructor stub
	}
	public LabelEditText(Context context,AttributeSet attrs)
	{
		super(context,attrs);
		this.initPadding();
		//TODO Auto-generated constructor stub
	}
	public LabelEditText(Context context, AttributeSet attrs, int defStyle) 
	{
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	

	public int[] onCreateDrawableState(int extraSpace)
	{
		int[] drawableState = super.onCreateDrawableState(extraSpace + 2);
	    if(isDark == true)
	    	mergeDrawableStates(drawableState,dark_mode);
	    else
	    	mergeDrawableStates(drawableState,light_mode);
	    
	    return drawableState;
	}
	protected void onDraw(Canvas canvas)
	{
		Paint paint = new Paint();
		this.initPaint(paint);			
		this.setPadding(paddingLeft, finalPaddingTop, paddingRight, paddingBottom);
		canvas.drawText(label+":", paddingLeft, labelLocation, paint);	
        super.onDraw(canvas);
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	public void initPaint(Paint paint)
	{
		int color = this.getResources().getColor(R.color.pocket_blue);
		paint.setColor(color);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setTextSize(this.getResources().getDimension(R.dimen.form_header_text_size));
		paint.setAntiAlias(true);
		paint.setTypeface(getTypeface());
	}
	
	public void initPadding()
	{
		this.paddingLeft = getPaddingLeft();
		this.paddingTop = getPaddingTop();
		this.paddingRight = getPaddingRight();
		this.paddingBottom = getPaddingBottom();
		finalPaddingTop = (int) this.getResources().getDimension(R.dimen.form_header_text_size)+paddingTop;
		labelLocation = (int) this.getResources().getDimension(R.dimen.form_header_text_size);	
	}

}
