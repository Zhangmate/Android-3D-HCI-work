package com.test.fuckworld;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;


public class MyRenderer implements Renderer {
	int one=0x10000;
	//************************//
	 private Bitmap bitmap= initBitmap.bitmap; 
	 private int[] textureids = new int[1];
	//纹理点  
	 public Context context;
	   
     // 实例化bitmap  
     
	 private int[] texCoords = {    
			 one,0,0,0,0,one,one,one,
				0,0,0,one,one,one,one,0,
				one,one,one,0,0,0,0,one,
				0,one,one,one,one,0,0,0,
				0,0,0,one,one,one,one,0,
				one,0,0,0,0,one,one,one
	             
	    };
	
	//************************//
	public final float X_AXIS=1.0f;
	public final float Y_AXIS=2.0f;
	public final float Z_AXIS=3.0f;
float r=90,theta=0;
float tx,ty;
static float x_line=1.0f,y_line=1.0f,z_line=1.0f;
float wide,heigh;
float xscale=1.0f,yscale=1.0f,zscale=1.0f,xtransl ,ytransl ,ztransl ,xrotate ,yrotate ,zrotate ;
static float old_xscale=1.0f,old_yscale=1.0f,old_zscale=1.0f,old_xtransl,old_ytransl,old_ztransl,old_xrotate,old_yrotate,old_zrotate;
float[] color=new float[]{
	1.0f,0.0f,0.0f,0.0f,//red
    0.0f,1.0f,0.0f,0.0f,//green
	0.0f,0.0f,1.0f,0.0f,//blue
	//1.0f,1.0f,0.0f,0.0f,//yellow
};
byte[] face=new byte[]{
	0,1,2,
	0,2,3,
	2,3,7,
	2,7,6,
	2,6,1,
	1,6,5,
	1,5,0,
	5,0,4,
	5,4,6,
	4,6,7,
	4,7,3,
	4,3,0
};
float[] vertex=new float[]{
		/**/
		0.5f,0.5f,0.5f,
		0.5f,0.5f,-0.5f,
		0.5f,-0.5f,-0.5f,
		0.5f,-0.5f,0.5f,
		
		-0.5f,0.5f,0.5f,
		-0.5f,0.5f,-0.5f,
		-0.5f,-0.5f,-0.5f,
		-0.5f,-0.5f,0.5f,
		
		 
};

float[][] line=new float[][]{{0,0,0,3.0f,0,0},
	{0,0,0,0,3.0f,0},
	{0,0,0,0,0,3.0f}};
float[][] linecolor=new float[][]{
		{1.0f,0,0},
		{0,1.0f,0},
		{0,0,1.0f}
};
FloatBuffer vertexbuffer,colorbuffer,linebuffer;
ByteBuffer facebuffer;
private IntBuffer texBuffer;
public MyRenderer(Context context){
	this.context = context;  
	vertexbuffer=floatbuffer(vertex);
	facebuffer=ByteBuffer.wrap(face);
	colorbuffer=floatbuffer(color);
	 
	 ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4  );  
     tbb.order(ByteOrder.nativeOrder());  
     texBuffer = tbb.asIntBuffer();  
    
     texBuffer.put(texCoords);
     texBuffer.position(0); 
	 
	
	 
	
	
	
	
}
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		//------------first 3D object---------------------

		gl.glLoadIdentity();
		
		GLU.gluLookAt(gl, 3, 3, 3,0, 0, 0, 0, 1, 0);
		
		gl.glPushMatrix( ); 
		   
		gl.glLineWidth(10.0f);
		gl.glTranslatef(xtransl, -ytransl, ztransl);
		gl.glScalef(xscale, yscale, zscale);
		gl.glRotatef(xrotate, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(yrotate, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(zrotate, 0.0f, 0.0f, 1.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexbuffer); 
		gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, texBuffer); 
		//colorbuffer=floatbuffer(color);
		//gl.glColorPointer(4, GL10.GL_FIXED, 0, colorbuffer);
		 gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, facebuffer.remaining(), GL10.GL_UNSIGNED_BYTE, facebuffer);
		/*
		for (int i = 0; i < 6; i++) {
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, i * 4, 4);
        } */
        
		gl.glPopMatrix( );
		 
		
		gl.glPushMatrix( ); 
		gl.glLineWidth(x_line);
		 
	    linebuffer=floatbuffer( line[0] );
	    gl.glVertexPointer(3,GL10.GL_FLOAT,0, linebuffer);
	    colorbuffer=floatbuffer(linecolor[0] );
	     gl.glColorPointer(4, GL10.GL_FIXED, 0, colorbuffer);
	    gl.glDrawArrays(GL10.GL_LINES,0, 2);
	    
	    gl.glLineWidth(y_line);
	    linebuffer=floatbuffer( line[1] );
	    gl.glVertexPointer(3,GL10.GL_FLOAT,0, linebuffer);
	    colorbuffer=floatbuffer(linecolor[1]);
	     gl.glColorPointer(4, GL10.GL_FIXED, 0, colorbuffer);
	    gl.glDrawArrays(GL10.GL_LINES,0, 2);
	    
	    gl.glLineWidth(z_line);
	    linebuffer=floatbuffer( line[2] );
	    gl.glVertexPointer(3,GL10.GL_FLOAT,0, linebuffer);
	    colorbuffer=floatbuffer(linecolor[2] );
	     gl.glColorPointer(4, GL10.GL_FIXED, 0, colorbuffer);
	    gl.glDrawArrays(GL10.GL_LINES,0, 2);
	    
	    gl.glPopMatrix( );
	     
	    
		gl.glFinish();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		 gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
    public void myscale(float scale,float axis){
    	if(axis==X_AXIS){
    		xscale =scale ;
    	}
    	if(axis==Y_AXIS){
    		yscale =scale ;
    	}
    	if(axis==Z_AXIS){
    		zscale =scale ;
    		
    	}
    }
    public void mytransl(float transl,float axis){
    	if(axis==X_AXIS){
    		xtransl =transl/wide ;
    	}
    	if(axis==Y_AXIS){
    		ytransl =transl/heigh ;
    	}
    	if(axis==Z_AXIS){
    		ztransl =(float) (transl/(Math.sqrt(wide*wide+heigh*heigh))) ;
    		
    	}
    }
    public void myrotate(float rotate,float axis){
    	
    	if(axis==X_AXIS){
    		xrotate =rotate  ;
    	}
    	if(axis==Y_AXIS){
    		yrotate =rotate  ;
    	}
    	if(axis==Z_AXIS){
    		zrotate =rotate  ;
    		
    	}
    	
    }
    public void keep_state(){
    	old_xscale=xscale;
    	old_yscale=yscale;
    	old_zscale=zscale;
    	old_xtransl=xtransl;
    	old_ytransl=ytransl;
    	old_ztransl=ztransl;
    	old_xrotate=xrotate;
    	old_yrotate=yrotate;
    	old_zrotate=zrotate;
    }
	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		// TODO Auto-generated method stub
		wide=w;
		heigh=h;
		gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        float ratio = (float)w/h;
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
        
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		// TODO Auto-generated method stub
		
	    
		gl.glDisable(GL10.GL_DITHER);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0.8f, 0.8f, 0.8f, 0);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);  
        // 创建纹理  
        gl.glGenTextures(1, textureids, 0);  
        // 绑定要使用的纹理  
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureids[0]);  
        // 生成纹理  
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);  
        // 线性滤波  
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,  
                GL10.GL_LINEAR);  
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,  
                GL10.GL_LINEAR);  
		
		
		
	}

	private FloatBuffer floatbuffer(float[] arr){
		FloatBuffer mybuffer;
		ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length*4);
		qbb.order(ByteOrder.nativeOrder());
		mybuffer = qbb.asFloatBuffer();
		mybuffer.put(arr);
		mybuffer.position(0);
		return mybuffer;
		
	}
}
