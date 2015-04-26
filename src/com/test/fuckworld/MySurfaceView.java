package com.test.fuckworld;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

public class MySurfaceView extends GLSurfaceView{
private MyRenderer mrr;
public final float X_AXIS=1.0f;
public final float Y_AXIS=2.0f;
public final float Z_AXIS=3.0f;
public final float MISS=100.f;
public final float MIN=0.4f;
public final float MIN_ANG=0.1f;
public final float INF=Float.MAX_VALUE;
float buffer_sx,buffer_sy,buffer_sz;
float buffer_tx,buffer_ty,buffer_tz;
float buffer_rx,buffer_ry,buffer_rz;
float tx,ty,tz,sx,sy,sz,rx,ry,rz;//rotate scale translate of xyz axis
float ax,ay,az;//angle of xyz axis on the screen
float axis=0.0f;
//float trans_scale,scale_rot;
float delta_angle,delta_length,transl_length,old_angle;
private static float old_x0,old_x1,old_y0,old_y1,new_x0,new_x1,new_y0,new_y1;
boolean isrotate=false,istransl=false,isscale=false;
int count=0;
float firClick=0,secClick=0;
public MySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
		mrr=new MyRenderer(); 
        setRenderer(mrr); 
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); 
        
        	
		
	}
	public void init(){
		buffer_sx=buffer_sy=buffer_sz=1;
		buffer_tx=buffer_ty=buffer_tz=0;
		buffer_rx=buffer_ry=buffer_rz=0;
		tx=ty=tz=0;
		sx=sy=sz=1;
		rx=ry=rz=0;
		ax=0;
		ay=INF;
		az=-1;
		//trans_scale=0;
		//scale_rot=0;
	}
	public void DisplayToast(String str)
    {
	
    	Toast.makeText(this.getContext(), str, Toast.LENGTH_SHORT).show();
    }
	public void updata(){
		buffer_sx=sx;
    	buffer_sy=sy;
    	buffer_sz=sz;
    	buffer_tx=tx;
    	buffer_ty=ty;
    	buffer_tz=tz;
    	buffer_rx=rx;
    	buffer_ry=ry;
    	buffer_rz=rz;
    	
    	 
	}
	@Override
	public boolean onTouchEvent(MotionEvent event)
    {
	  	int pointerCount = event.getPointerCount(); 
	  	if(pointerCount==3){
	  		axis=0.0f;
            mrr.x_line=1.0f;
        	mrr.y_line=1.0f;
        	mrr.z_line=1.0f;
        	isrotate=false;
        	istransl=false;
        	isscale=false;
            DisplayToast("clear" );
	  	}
	  	else if(event.getAction()==MotionEvent.ACTION_UP){
	  		isrotate=false;
        	istransl=false;
        	isscale=false;

	    	updata();
	    	
	  	}
	  	/*
	  	else if(MotionEvent.ACTION_DOWN == event.getAction()){  
	            
	  		 count++;  
	            if(count == 1){  
	                firClick = System.currentTimeMillis();  
	                  
	            } else if (count == 2){  
	                secClick = System.currentTimeMillis();  
	                if(secClick - firClick < 100){  
	                    //Ë«»÷ÊÂ¼þ  
	                      
	                      
	                }  
	                
	                count = 0;  
	                firClick = 0;  
	                secClick = 0;  
	                  
	            }  
	            
	        } 
	        */
	  	 
	  	else if(axis!=0&&istransl&&!isscale&&!isrotate&&event.getAction()==MotionEvent.ACTION_MOVE){
	  		new_x0=event.getX(0);
	  		new_y0=event.getY(0);
	  		
	  		transl_length=((float)Math.sqrt((new_y0-old_y0)*(new_y0-old_y0)+
					(new_x0-old_x0)*(new_x0-old_x0)) ) ;
	  		
	  		 
	  		if(axis==X_AXIS){
	  			if(new_x0>old_x0 )
	  				tx=transl_length+buffer_tx;
	  			else{
	  				tx=-transl_length+buffer_tx;
	  			}
	  			
	  		}
	  		else if(axis==Y_AXIS){
	  			if(new_y0>old_y0)
	  				ty=transl_length+buffer_ty;
	  			else{
	  				ty=-transl_length+buffer_ty;
	  			}
	  		}
	  		else if(axis==Z_AXIS){
	  			if(new_x0<old_x0)
	  			
	  				tz=transl_length+buffer_tz;
	  			else{
	  				tz=-transl_length+buffer_tz;
	  			}
	  		}
	  		
	  	}
	  	else if(!istransl&&event.getAction()==MotionEvent.ACTION_DOWN&&pointerCount==1){
	  		istransl=true;
	  		 
	  		old_x0=event.getX(0);
	  		old_y0=event.getY(0);
	  		 
	  		 
	  	}
	  	else if(axis==0&& pointerCount==2&&event.getAction()==MotionEvent.ACTION_MOVE){
	  		istransl=false;
		  	 
		  		float xlength,ylength;
				old_x0=event.getX(0);
				old_x1=event.getX(1);
				old_y0=event.getY(0);
				old_y1=event.getY(1);
				xlength=old_x1-old_x0;
		        ylength=old_y1-old_y0;
		        
		        if(xlength>-MISS&&xlength<MISS){
		        	axis= Y_AXIS;
		        	mrr.y_line=5.0f;
		        }
		       
		        else  if(ylength*xlength>0){
		        	axis= X_AXIS;
		        	mrr.x_line=5.0f;
		        }
		         
		        else{
		        	axis= Z_AXIS;
		        	mrr.z_line=5.0f;
		        }
		         
		  		 
	  	}
	  	else if(axis!=0 &&!isrotate&&!isscale&&pointerCount==2)
		{
	  				istransl=false;
	  				
	  				old_x0=event.getX(0);
					old_x1=event.getX(1);
					old_y0=event.getY(0);
					old_y1=event.getY(1);
	  				
					old_angle=(float) Math.toDegrees(Math.atan2(old_y1-old_y0, old_x1-old_x0));
					
					if(axis==X_AXIS){
			        	if((old_angle<65&&old_angle> 25)||(old_angle<-115&&old_angle>-155))
			        	{
			        		isscale=true;
		    				isrotate=false;
		    			}
		    			else{
		    				isscale=false;
		    				isrotate=true;
		    		 
		    			}
			    	}
			        	
			    		if(axis==Y_AXIS){
			    			if((old_angle>70&&old_angle<110)||(old_angle<-70&&old_angle>-110)){
			    				isscale=true;
			    				isrotate=false;
			    			}
			    			else{
			    				isscale=false;
			    				isrotate=true;
			    			}
			    			 
			    		}
			    	
			    		if(axis==Z_AXIS){
			    			if((old_angle>115&&old_angle<135)||(old_angle<-25&&old_angle>-65)){
			    				isscale=true;
			    				isrotate=false;
			    			}
			    			else{
			    				isscale=false;
			    				isrotate=true;
			    			}
			    			 
			    		}
		}
	  	else if(axis!=0&&(isscale||isrotate)&&event.getAction()==MotionEvent.ACTION_MOVE&&pointerCount==2){
	  		
	  		new_x0=event.getX(0);
			new_x1=event.getX(1);
			new_y0=event.getY(0);
			new_y1=event.getY(1);
		    
			
			
			delta_angle= (float) Math.toDegrees(Math.atan2(new_y1-new_y0, new_x1-new_x0))-old_angle;
			 
			delta_length=(float)Math.sqrt((new_y1-new_y0)*(new_y1-new_y0)+
					(new_x1-new_x0)*(new_x1-new_x0))/(float)Math.sqrt((old_y1-old_y0)*(old_y1-old_y0)+
							(old_x1-old_x0)*(old_x1-old_x0));
	  		
	  		if(axis==X_AXIS){
	        	 
	        	if(isscale){
	        		  
	        				sx=delta_length*buffer_sx;
	        			 
	        	}
	        		
	        	else{
	        		 
        				rx=   delta_angle +buffer_rx;
				 
	        	}
	        	
	    	}
	        	
	    		if(axis==Y_AXIS){
	    			 
	    			if(isscale){
	            		  
	            				sy=delta_length*buffer_sy;
	            		 
	            	}
	            		
	            	else{
	            		 
	            		ry=delta_angle+buffer_ry;
	    				 
	            	}
	    		}
	    	
	    		if(axis==Z_AXIS){
	    			 
	    			if(isscale){
	            	 	 
	            				sz=delta_length*buffer_sz;
	            		 
	            	}
	            		
	            	else{
	            		 
	            		rz=delta_angle+buffer_rz;
	    				
	            	}	
	    		}
	    		 
	  	}
	  	
	        	
		  	
	  	mrr.mytransl(tx, X_AXIS);
  		mrr.myscale(sx, X_AXIS);
  		mrr.myrotate(rx, X_AXIS);
  	 
    
  	 
  		mrr.mytransl(ty, Y_AXIS);
  		mrr.myscale(sy, Y_AXIS);
  		mrr.myrotate(ry, Y_AXIS);
  	 
  	  
  		mrr.mytransl(tz, Z_AXIS);         
  		mrr.myscale(sz, Z_AXIS);
  		mrr.myrotate(rz, Z_AXIS);
  	 
  	
   // DisplayToast("count"+" "+String.valueOf(axis));
    
  		this.requestRender();
  		return true;
    }
	
	
}
