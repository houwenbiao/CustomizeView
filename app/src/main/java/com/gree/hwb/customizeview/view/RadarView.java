package com.gree.hwb.customizeview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/19.
 */

public class RadarView extends View
{
	private int count = 9;//数据个数
	private float angle = (float) (Math.PI * 2 / count);//角度
	private float radius;//网格最大半径
	private int centerX;//中心X
	private int centerY;//中心Y
	private String[] titles = {"a", "b", "c", "d", "e", "f"};
	private double[] data =  {1, 0.9, 0.8, 0.7, 0.6, 0.5, 0.4, 0.3, 0.2}; //各维度分值
	private Paint mainPaint;                //雷达区画笔
	private Paint valuePaint;               //数据区画笔
	private Paint textPaint;                //文本画笔
	public RadarView(Context context)
	{
		this(context,null);
	}

	public RadarView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public RadarView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		mainPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mainPaint.setColor(Color.GREEN);
		mainPaint.setStyle(Paint.Style.STROKE);
		valuePaint = new Paint();
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		valuePaint.setColor(Color.BLUE);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{

		centerX = w / 2;
		centerY = h / 2;
		radius = Math.min(h, w) / 2 * 0.9f;
		postInvalidate();
		//invalidate()得在UI线程中被调动，
		// 在工作者线程中可以通过Handler来通知UI线程进行界面更新。
		//而postInvalidate()在工作者线程中被调用
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		drawPolygon(canvas);//绘制多边形
		drawLine(canvas);//绘制直线
		drawRegion(canvas);//绘制封闭区域
	}



	/**
	 * 绘制多边形
	 */
	private void drawPolygon(Canvas canvas)
	{
		Path path = new Path();
		float r = radius / (count - 1);//蜘蛛丝之间的距离
		for (int i = 0; i < count; i++)
		{
			float currentR = r * i;//当前半径
			path.reset();
			for (int j = 0; j < count; j++)
			{
				if(j == 0)
				{
					path.moveTo(centerX + currentR, centerY);
				}
				else
				{
					//计算每个点的坐标
					float x = (float) (centerX + currentR * Math.cos(angle * j));
					float y = (float) (centerY + currentR * Math.sin(angle * j));
					path.lineTo(x, y);
				}
			}
			path.close();//闭合路径
			canvas.drawPath(path, mainPaint);
		}
	}

	/**
	 * 绘制从中心到末端的直线
	 */
	private void drawLine(Canvas canvas)
	{
		Path path = new Path();
		for (int i = 0; i < count; i++)
		{
			path.reset();//清空
			path.moveTo(centerX, centerY);
			//计算每个点的坐标
			float x = (float) (centerX + radius * Math.cos(angle * i));
			float y = (float) (centerY + radius * Math.sin(angle * i));
			path.lineTo(x, y);
			canvas.drawPath(path, mainPaint);
		}
	}

	/**
	 * 绘制区域
	 */
	private void drawRegion(Canvas canvas)
	{
		Path path = new Path();
		valuePaint.setAlpha(255);
		for (int i = 0; i < count; i++)
		{
			float x = (float) (centerX + radius * Math.cos(angle * i) * data[i]);
			float y = (float) (centerY + radius * Math.sin(angle * i) * data[i]);
			if(i == 0)
			{
				path.moveTo(x, centerY);
			}
			else
			{
				path.lineTo(x, y);
			}
			canvas.drawCircle(x, y, 5, valuePaint);//绘制小圆点
		}
		valuePaint.setAlpha(110);
		valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
		canvas.drawPath(path, valuePaint);
	}
}
