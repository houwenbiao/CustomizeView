package com.gree.hwb.customizeview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/11.
 */
public class MyView extends View
{
	private Paint mPaint;
	public MyView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		mPaint.setColor(Color.RED);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		mPaint.setColor(Color.BLUE);
		mPaint.setTextSize(20);
		String text = "你好，这是自己的绘制";
		canvas.drawText(text, 0, getHeight() / 2, mPaint);
	}
}
