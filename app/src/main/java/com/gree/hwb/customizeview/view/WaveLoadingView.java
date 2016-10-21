package com.gree.hwb.customizeview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.gree.hwb.customizeview.util.Util;

/**
 * Created by Administrator on 2016/10/21.
 */

public class WaveLoadingView extends View
{
	private Context context;
	private Paint mWavePaint2;//绘制波纹2
	private Paint mWavePaint;//绘制波纹
	private Paint mTextPaint;//绘制文字的画笔
	private PorterDuffXfermode mMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
	private Paint mCirclePaint;//绘制圆
	private Canvas mCanvas;//画布
	private int mWidth;//屏幕宽度
	private int mHeight;//屏幕高度
	private Bitmap mBitmap;
	private boolean isLeft;//是否在右侧标识
	private int x;
	private int y;
	private Path mPath, mPath2;//绘制两条波纹的路径
	private int mPercent = 60;


	public WaveLoadingView(Context context)
	{
		this(context, null);
	}

	public WaveLoadingView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public WaveLoadingView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		this.context = context;
		mWavePaint2 = new Paint();
		mWavePaint2.setStrokeWidth(10);
		mWavePaint2.setAntiAlias(true);//设置画笔抗锯齿
		mWavePaint2.setAntiAlias(true);
		mWavePaint2.setColor(Color.parseColor("#8800ff66"));

		mPath = new Path();
		mPath2 = new Path();
		mWavePaint = new Paint();
		mWavePaint.setAntiAlias(true);//设置画笔抗锯齿
		mWavePaint.setColor(Color.parseColor("#8800aa11"));
		mCirclePaint = new Paint();
		mCirclePaint.setAntiAlias(true);//设置画笔抗锯齿
		mCirclePaint.setColor(Color.parseColor("#88dddddd"));
		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);//取出宽度的确切数值
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);//取出宽度的测量模式
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);//取出高度的确切数值
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);//取出高度的测量模式
		if(widthMode == MeasureSpec.EXACTLY)
		{
			mWidth = widthSize;
		}
		if(heightMode == MeasureSpec.EXACTLY)
		{
			mHeight = heightSize;
		}
		//如果对View的宽高进行修改了，不要调用 super.onMeasure( widthMeasureSpec, heightMeasureSpec);
		// 要调用 setMeasuredDimension( widthsize, heightsize); 这个函数
		setMeasuredDimension(mWidth, mHeight);
		mBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);//生成一个bitmap
		mCanvas = new Canvas(mBitmap);//将bitmap放在我们自己的画布上，实际上canvas.draw时候改变的是biemap的对象
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		if(x > 50)
		{
			isLeft = true;
		}
		else if(x < 0)
		{
			isLeft = false;
		}
		if(isLeft)
		{
			x -= 1;
		}
		else
		{
			x += 1;
		}

		mPath.reset();//路径清空
		mPath2.reset();
		y = (int) ((1 - mPercent / 100f) * mHeight);
		mPath.moveTo(0, y);
		mPath2.moveTo(mWidth, y);
		mPath.cubicTo(100 + x * 2, 30 + y, 100 + x * 2, y - 30, mWidth, y);//前两个参数是控制点，后面一个是结束点
		mPath2.cubicTo(200 - x * 2, 30 + y, 200 - x * 2, y - 30, 0, y);
		mPath.lineTo(mWidth, mHeight);
		mPath2.lineTo(0, mHeight);
		mPath.lineTo(0, mHeight);//充满整屏幕
		mPath2.lineTo(mWidth, mHeight);//充满整屏幕
		mPath.close();//闭合回路
		mPath2.close();//闭合回路
		mBitmap.eraseColor(Color.parseColor("#00000000"));
		mCanvas.drawCircle(mWidth / 2, mHeight / 2, Util.dpTopx(context, 100), mCirclePaint);
		mWavePaint.setXfermode(mMode);//设置模式
		mWavePaint2.setXfermode(mMode);
		mCanvas.drawPath(mPath, mWavePaint);
		mCanvas.drawPath(mPath2, mWavePaint2);
		mWavePaint.setXfermode(null);//清空格式
		mWavePaint2.setXfermode(null);
		/*mCanvas.drawRect(100, 100, 200, 200, mWavePaint);*/
		canvas.drawBitmap(mBitmap, 0, 0, null);

		String str = mPercent + "";

		mTextPaint.setTextSize(Util.spToPx(context, 30));
		float txtLength = mTextPaint.measureText(str);

		canvas.drawText(str, mWidth / 2 - txtLength / 2, mHeight / 2 + Util.dpTopx(context, 10), mTextPaint);

		mTextPaint.setTextSize(Util.spToPx(context, 20));
		canvas.drawText("%", mWidth / 2 + Util.dpTopx(context, 15), mHeight / 2 - Util.dpTopx(context, 15), mTextPaint);
		postInvalidateDelayed(10);
	}
}


























