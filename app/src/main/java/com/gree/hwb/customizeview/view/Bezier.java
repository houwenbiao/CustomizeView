package com.gree.hwb.customizeview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Bezier extends View
{
	private Paint mPaint;//画笔
	private boolean mode = true;
	private int centerX, centerY;//屏幕中心坐标
	private PointF startPointF, endPointF, controlPointF, controlPointF2;//四个点坐标
	public Bezier(Context context)
	{
		this(context, null);
	}

	public Bezier(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public Bezier(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		mPaint.setStrokeWidth(8);//设置空心线宽
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setTextSize(60);
		startPointF = new PointF(0, 0);
		endPointF = new PointF(0, 0);
		controlPointF = new PointF(0, 0);
		controlPointF2 = new PointF(0, 0);
	}

	public void setMode(boolean mode)
	{
		this.mode = mode;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		centerX = w / 2;
		centerY = h / 2;
		//初始化数据点和控制点的位置
		startPointF.x = centerX - 200;
		startPointF.y = centerY;
		endPointF.x = centerX + 200;
		endPointF.y = centerY;
		controlPointF.x = centerX;
		controlPointF.y = centerY - 100;
		controlPointF2.x = centerX;
		controlPointF2.y = centerY - 100;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		//根据触摸位置更新控制点，并重绘
		if(mode)
		{
			controlPointF2.x = event.getX();
			controlPointF2.y = event.getY();
		}
		else
		{
			controlPointF.x = event.getX();
			controlPointF.y = event.getY();
		}
		invalidate();
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(20);
		canvas.drawPoint(startPointF.x, startPointF.y, mPaint);//画点
		canvas.drawPoint(endPointF.x, endPointF.y, mPaint);
		canvas.drawPoint(controlPointF.x, controlPointF.y, mPaint);

		//绘制辅助线
		mPaint.setStrokeWidth(4);
		canvas.drawLine(startPointF.x, startPointF.y, controlPointF.x, controlPointF.y, mPaint);
		canvas.drawLine(controlPointF.x, controlPointF.y, controlPointF2.x, controlPointF2.y, mPaint);
		canvas.drawLine(endPointF.x, endPointF.y, controlPointF2.x, controlPointF2.y, mPaint);

		//绘制贝塞尔曲线
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(8);
		Path path = new Path();
		path.moveTo(startPointF.x, startPointF.y);
		path.cubicTo(controlPointF.x, controlPointF.y, controlPointF2.x, controlPointF2.y, endPointF.x, endPointF.y);
		canvas.drawPath(path, mPaint);
	}
}
