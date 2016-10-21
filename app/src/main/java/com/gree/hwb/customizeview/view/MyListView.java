package com.gree.hwb.customizeview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.gree.hwb.customizeview.R;

/**
 * Created by Administrator on 2016/10/12.
 */

public class MyListView extends ListView implements View.OnTouchListener, GestureDetector.OnGestureListener
{
	private GestureDetector gestureDetector;//手势监听类
	private OnDeleteListener listener;
	private View deleteButton;
	private ViewGroup itemLayout;
	private int selectItem;
	private boolean isDeleteShown;

	public MyListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		gestureDetector = new GestureDetector(getContext(), this);
		setOnTouchListener(this);
	}

	public void setOnDeleteListener(OnDeleteListener listener)
	{
		this.listener = listener;
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent)
	{
		if(isDeleteShown)
		{
			itemLayout.removeView(deleteButton);
			deleteButton = null;
			isDeleteShown = false;
			return false;
		}
		else
		{
			return gestureDetector.onTouchEvent(motionEvent);
		}
	}

	@Override
	public boolean onDown(MotionEvent motionEvent)
	{
		if(!isDeleteShown)
		{
			selectItem = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
		}
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		if(!isDeleteShown && Math.abs(velocityX) > Math.abs(velocityY))
		{
			deleteButton = LayoutInflater.from(getContext()).inflate(R.layout.delete_button, null);
			deleteButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					itemLayout.removeView(deleteButton);
					deleteButton = null;
					isDeleteShown = false;
					listener.onDelete(selectItem);
				}
			});
			itemLayout = (ViewGroup) getChildAt(selectItem - getFirstVisiblePosition());
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			params.addRule(RelativeLayout.CENTER_VERTICAL);
			itemLayout.addView(deleteButton, params);
			isDeleteShown = true;
		}
		return false;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent motionEvent)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent motionEvent)
	{

	}

	@Override
	public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1)
	{
		return false;
	}

	@Override
	public void onLongPress(MotionEvent motionEvent)
	{

	}

	public interface OnDeleteListener
	{
		void onDelete(int index);
	}
}



























