package com.gree.hwb.customizeview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.gree.hwb.customizeview.bean.PieData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public class PieView extends View
{
	// 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
	private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
			0xFFE6B800, 0xFF7CFC00};
	private float mStartAngle = 0.0f;//饼状图初始绘制角度
	private List<PieData> mData;//数据源
	private int mWith, mHight;//宽高
	private Paint mPaint = new Paint();
	private float r;
	private Rect rect;

	public PieView(Context context)
	{
		super(context);
		rect = new Rect();
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置画笔格式
		mPaint.setAntiAlias(true);//设置画笔抗锯齿
	}

	public PieView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		rect = new Rect();
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置画笔格式
		mPaint.setAntiAlias(true);//设置画笔抗锯齿
	}

	public PieView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		rect = new Rect();
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置画笔格式
		mPaint.setAntiAlias(true);//设置画笔抗锯齿
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		mWith = w;
		mHight = h;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		if(mData == null)
		{
			return;
		}
		float currentStartAngle = mStartAngle;//当前起始角度
		canvas.translate(mWith / 2, mHight / 2);//将画布坐标移动到中心位置
		//饼状圆半径
		r = (float) (Math.min(mWith, mHight) / 2 *0.8);
		RectF rectF = new RectF(-r, -r, r, r);
		for (int i = 0; i < mData.size(); i++)
		{
			PieData data = mData.get(i);
			mPaint.setColor(data.getColor());
			mPaint.setDither(true);//防抖动
			mPaint.setAntiAlias(true);//抗锯齿
			canvas.drawArc(rectF, currentStartAngle, data.getAngle(), true, mPaint);
			currentStartAngle += data.getAngle();
		}

		currentStartAngle = mStartAngle;
		for (int i = 0; i < mData.size(); i++)
		{
			PieData data = mData.get(i);
			String text = data.getName();
			drawText(canvas, data, currentStartAngle, text);
			currentStartAngle += data.getAngle();
		}

	}

	private void drawText(Canvas canvas, PieData data, float currentStartAngle, String text)
	{
		int textPathX = (int) ((Math.cos(Math.toRadians(currentStartAngle + data.getAngle() / 2))) * (r/2));
		int textPathY = (int) ((Math.sin(Math.toRadians(currentStartAngle + data.getAngle() / 2))) * (r/2));
		mPaint.setColor(Color.GREEN);
		mPaint.setTextSize(50);
		mPaint.getTextBounds(text, 0, text.length(), rect);
		float textWith = rect.width();
		float textHight = rect.height();
		canvas.drawText(text, textPathX - textWith / 2, textPathY + textHight / 2, mPaint);
	}


	//设置起始角度
	public void setStartAngle(int mStartAngle)
	{
		this.mStartAngle = mStartAngle;
		invalidate();//刷新
	}

	//设置数据
	public void setData(ArrayList<PieData> mData)
	{
		this.mData = mData;
		initData(mData);
		invalidate();//刷新
	}

	private void initData(ArrayList<PieData> mData)
	{
		if(mData == null || mData.size() == 0)
		{
			return;
		}
		float sumValue = 0.0f;
		for (int i = 0; i < mData.size(); i++)
		{
			PieData data = mData.get(i);
			sumValue += data.getValue();//计算数值和
			int j = i % mColors.length;//设置颜色
			data.setColor(mColors[j]);
		}

		float sumAngle = 0.0f;
		for (int i = 0; i < mData.size(); i++)
		{
			PieData data = mData.get(i);
			float pencent = data.getValue() / sumValue;//百分比
			float angle = pencent * 360;//对应的角度
			data.setPercent(pencent);//记录百分百
			data.setAngle(angle);//记录角度
			sumAngle += angle;
		}
	}


}














