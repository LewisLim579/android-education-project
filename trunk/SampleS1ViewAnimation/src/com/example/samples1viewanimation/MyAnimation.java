package com.example.samples1viewanimation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MyAnimation extends Animation {
	int mWidth;
	int mHeight;
	
	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mWidth = parentWidth - width;
		mHeight = parentHeight - height;
	}
	
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		super.applyTransformation(interpolatedTime, t);
		
		float dx = interpolatedTime * mWidth;
		float dy = interpolatedTime * interpolatedTime * mHeight;
		t.getMatrix().setTranslate(dx, dy);
	}
}
