package com.example.samples1customview;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

	GestureDetector mDetector;
	public MyView(Context context) {
		super(context);
		init();
	}
	
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
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
		
		mDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onDown(MotionEvent e) {
				return true;
			}
			
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				Log.i("MyView", "SingleTapUp...");
				return true;
			}
			
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				Log.i("MyView", "fling...");
				return true;
			}
		});
	}
	
	
	Bitmap mBitmap;
	
	private void makeBitmap() {
		InputStream is = getContext().getResources().openRawResource(R.drawable.photo1);

		mBitmap = BitmapFactory.decodeStream(is);
	}
	
	public void setBitmap(Bitmap bitmap) {
		mBitmap = bitmap;
//		invalidate();
		requestLayout();
	}
	
	Path mPath, mTextPath, mLinePath;
	
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
		
		mLinePath = new Path();
		mLinePath.moveTo(0, 0);
		mLinePath.lineTo(-5, -5);
		mLinePath.lineTo(0, -5);
		mLinePath.lineTo(5, 0);
		mLinePath.lineTo(0, 5);
		mLinePath.lineTo(-5, 5);
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
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width, height;
		width = getPaddingLeft() + getPaddingRight();
		if (mBitmap != null) {
			width += mBitmap.getWidth();
		}
		height = getPaddingTop() + getPaddingBottom();
		if (mBitmap != null) {
			height += mBitmap.getHeight();
		}
		
		width = resolveSize(width, widthMeasureSpec);
		height = resolveSize(height, heightMeasureSpec);
		
		setMeasuredDimension(width, height);
	}

	int imageX, imageY;
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mBitmap != null) {
			imageX = (right - left) / 2 - mBitmap.getWidth() / 2;
			imageY = (bottom - top) / 2 - mBitmap.getHeight() / 2;
		} else {
			imageX = getPaddingLeft();
			imageY = getPaddingTop();
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean isProcessed = mDetector.onTouchEvent(event);
		return isProcessed || super.onTouchEvent(event);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawColor(Color.WHITE);
		
		drawColorFilter(canvas);
//		drawShader(canvas);
//		drawPathEffect(canvas);
//		drawBitmapMesh(canvas);
//		drawBitmap(canvas);
//		drawText(canvas);
//		drawPath(canvas);
//		drawCircle(canvas);
//		drawRect(canvas);
		
//		drawLine(canvas);
	}
	
	private void drawColorFilter(Canvas canvas) {
		
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorFilter filter = new ColorMatrixColorFilter(cm);
		mPaint.setColorFilter(filter);
		canvas.drawBitmap(mBitmap, imageX, imageY, mPaint);
	}
	
	private void drawShader(Canvas canvas) {
		int[] colors = {Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED};
		float[] position = { 0, 0.2f, 0.5f, 1};
		Shader shader = new LinearGradient(100, 100, 400, 400, colors,position, TileMode.CLAMP);
		mPaint.setShader(shader);
//		mPaint.setColor(Color.BLUE);
		RectF rect = new RectF(100,100,400,400);
		canvas.drawRoundRect(rect, 50, 50, mPaint);
	}
	
	private void drawPathEffect(Canvas canvas) {
		
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(10);
		mPaint.setColor(Color.RED);		
		mPaint.setAntiAlias(true);
		
		float[] intervals = {10, 10, 20, 10};
		float phase = 10;
//		PathEffect effect = new DashPathEffect(intervals, phase);
		PathEffect effect = new PathDashPathEffect(mLinePath, 20, 0, PathDashPathEffect.Style.ROTATE);
		
		mPaint.setPathEffect(effect);
		
		canvas.drawCircle(300, 300, 200, mPaint);
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
