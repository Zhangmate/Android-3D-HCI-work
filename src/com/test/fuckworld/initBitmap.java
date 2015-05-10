package com.test.fuckworld;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class initBitmap {

	public static Bitmap bitmap;
	
	public static void init(Resources res){
		bitmap = BitmapFactory.decodeResource(res, R.drawable.wall3) ;
	}
}

