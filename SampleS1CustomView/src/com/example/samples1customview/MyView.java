package com.example.samples1customview;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.view.View;

public class MyView extends View {

	public MyView(Context context) {
		super(context);
		init();
	}
	
	private static final int LENGTH = 300;
	private static final float DELTA = 10;
	private static final int COUNT = (int)(LENGTH / DELTA) + 1;
	private float[] points = new float[COUNT * 4];
	Paint mPaint = new Paint();
	private void init() {
		makeLine();
		makePath();
		makeBitmap();
	}
	
	
	Bitmap mBitmap;
	
	private void makeBitmap() {
		InputStream is = getContext().getResources().openRawResource(R.drawable.photo1);

		mBitmap = BitmapFactory.decodeStream(is);
	}
	
	Path mPath, mTextPath;
	
	private void makePath() {
		mPath = new Path();
		
		mPath.moveTo(200, 200);
		mPath.lineTo(100, 100);
		mPath.lineTo(300, 100);
		mPath.lineTo(400, 200);
		mPath.lineTo(300, 300);
		mPath.lineTo(100, 300);
		
		mTextPath = new Path();
		mTextPath.addCircle(300, 300, 200, Direction.CW);
	}
	
	private void makeLine() {
		for (int i = 0; i < COUNT; i++) {
			points[i * 4 + 0] = 0;
			points[i * 4 + 1] = i * DELTA;
			points[i * 4 + 2] = LENGTH - i * DELTA;
			points[i * 4] = 0;
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawColor(Color.WHITE);
		drawBitmapMesh(canvas);
//		drawBitmap(canvas);
//		drawText(canvas);
//		drawPath(canvas);
//		drawCircle(canvas);
//		drawRect(canvas);
		
//		drawLine(canvas);
	}
	float[] mesh = { 100 , 100, 150, 150, 200, 100, 250, 150, 300, 100 ,
			         100 , 400, 150, 450, 200, 400, 250, 450, 300, 400 };
	private void drawBitmapMesh(Canvas canvas) {
		
		canvas.drawBitmapMesh(mBitmap, 4, 1, mesh, 0, null, 0, mPaint);
	}
	private void drawBitmap(Canvas cavnas) {
		Matrix m = new Matrix();
		m.setScale(1, -1, mBitmap.getWidth()/2, mBitmap.getHeight()/2);
		m.postTranslate(100, 100);
		cavnas.drawBitmap(mBitmap, m, mPaint);
	}
	
	String text = "Hellog, Android! Hello, Android! Hello, Android! Hello, Android!";
	private void drawText(Canvas canvas) {
		mPaint.setColor(Color.BLUE);
		mPaint.setTextSize(30);
//		canvas.drawText(text, 0, 40, mPaint);
		canvas.drawTextOnPath(text, mTextPath, 0, 0, mPaint);
	}
	
	private void drawPath(Canvas canvas) {
		mPaint.setColor(Color.BLUE);
		canvas.drawPath(mPath, mPaint);
	}
	
	private void drawCircle(Canvas canvas) {
		mPaint.setColor(Color.BLUE);
		canvas.drawCircle(250, 250, 150, mPaint);
	}
	private void drawRect(Canvas canvas) {
		mPaint.setColor(Color.BLUE);
//		canvas.drawRect(100, 100, 400, 400, mPaint);
		RectF rect = new RectF(100, 100, 400, 400);
		canvas.drawRoundRect(rect, 50, 50, mPaint);
//		canvas.drawRoundRect(100, 100, 400, 400, 50, 50, mPaint);
	}
	
	private void drawLine(Canvas canvas) {
		canvas.save();
		
		canvas.translate(100, 100);
//		canvas.rotate(45, 100, 100);
//		canvas.scale(-1, 1, 300, 100);
		
		mPaint.setColor(Color.BLUE);
		mPaint.setStrokeWidth(2);
		canvas.drawLines(points, mPaint);

		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(5);
		canvas.drawPoints(points, mPaint);
		
		canvas.restore();
	}
	
	

}
