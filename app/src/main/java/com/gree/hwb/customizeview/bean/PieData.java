package com.gree.hwb.customizeview.bean;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2016/10/17.
 */

public class PieData
{
	//绘制饼状图用户所关心的数据
	private String name;
	private float value;
	private float percent;

	//非用户关心的数据
	private int color = 0;//颜色
	private float angle = 0;//角度

	public PieData(@NonNull String name, @NonNull float value)
	{
		this.name = name;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public float getValue()
	{
		return value;
	}

	public void setValue(float value)
	{
		this.value = value;
	}

	public float getPercent()
	{
		return percent;
	}

	public void setPercent(float percent)
	{
		this.percent = percent;
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}

	public float getAngle()
	{
		return angle;
	}

	public void setAngle(float angle)
	{
		this.angle = angle;
	}

	@Override
	public String toString()
	{
		return "PieData{" +
					   "name='" + name + '\'' +
					   ", value=" + value +
					   ", percent=" + percent +
					   ", color=" + color +
					   ", angle=" + angle +
					   '}';
	}
}
