package com.test.fuckworld;

import android.app.Activity;
 
 
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;

																																																																							
public class Geometry extends Activity  {
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	initBitmap.init(this.getResources());
	    	MySurfaceView renderer = new MySurfaceView(this);
	        
	   
	        
	        setContentView(renderer); 
	    	renderer.requestFocus();
	    	renderer.setFocusableInTouchMode(true);
	    	
	    	
	    	
	       
	    }
	 
	  
	  public void DisplayToast(String str)
	    {
		
	    	Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	    }

 
}
