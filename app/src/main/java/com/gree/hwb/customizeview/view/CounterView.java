package com.gree.hwb.customizeview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.audiofx.Visualizer;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/11.
 */
public class CounterView extends View implements View.OnClickListener
{
	private Paint mPaint;
	private Rect mBounds;
	private int mCount;

	public CounterView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBounds = new Rect();
		setOnClickListener(this);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		mPaint.setColor(Color.BLUE);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		mPaint.setColor(Color.YELLOW);
		mPaint.setTextSize(30);
		String text = String.valueOf(mCount);
		//计算文字所在矩形，可以得到宽高
		mPaint.getTextBounds(text, 0, text.length(), mBounds);
		float textWith = mBounds.width();
		float textHeight = mBounds.height();
		canvas.drawText(text,getWidth() / 2 - textWith / 2, getHeight() / 2 + textHeight / 4, mPaint);

	}

	@Override
	public void onClick(View view)
	{
		mCount ++ ;
		invalidate();
	}
}
