package com.gree.hwb.customizeview.util;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/09/22.
 */
public class Util
{
	private static boolean isShow = true;

	/**
	 * 打印Log日志方法
	 *
	 * @param tag
	 * @param msg
	 */
	public static void showLog(String tag, String msg)
	{
		if(isShow)
		{
			Log.i(tag, msg);
		}
	}

	/**
	 * 吐司显示
	 *
	 * @param info
	 * @param time
	 */
	public static void showToastByTime(String info, int time)
	{
		final Toast toast = Toast.makeText(MyApplication.getContext(), info, Toast.LENGTH_LONG);
		toast.show();
		Handler handler = new Handler();
		handler.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				toast.cancel();
			}
		}, time);
	}

	/**
	 * sp转换成px,为适应不同屏幕而设计
	 *
	 * @param context
	 * @param spValue
	 * @return
	 */
	public static int spToPx(Context context, float spValue)
	{
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue
	 * @return
	 */
	public static int pxTosp(Context context, float pxValue)
	{
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 *
	 * @param pxValue
	 * @return
	 */
	public static int pxTodp(Context context, float pxValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 *
	 * @param dipValue
	 * @return
	 */
	public static int dpTopx(Context context, float dipValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

}
