package com.campus.prime.ui.view;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

public class ThemeDialog extends Dialog
{

	public ThemeDialog(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}
	protected ThemeDialog(Context context, boolean cancelable,OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}
	public ThemeDialog(Context context , int theme)
	{
		super(context,theme);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
