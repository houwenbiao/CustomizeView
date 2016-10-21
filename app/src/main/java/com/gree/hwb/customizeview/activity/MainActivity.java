package com.gree.hwb.customizeview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;


import com.gree.hwb.customizeview.view.MyAdapter;
import com.gree.hwb.customizeview.view.MyListView;
import com.gree.hwb.customizeview.R;
import com.sloop.view.loading.LeafLoading;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义View
 */

public class MainActivity extends AppCompatActivity
{
	private LinearLayout mainLayout;
	private MyListView myListView;
	private MyAdapter adapter;
	private List<String> contentList = new ArrayList<String>();
	private int progress;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("zx","不适用数据线调试");
		/*//找到组件
		LeafLoading loading = (LeafLoading) findViewById(R.id.loading);
		//设置进度
		progress = 0;
		for (int progress = 0; progress < 101; progress++)
		{
			loading.setProgress(50);
		}*/

		/*mainLayout = (LinearLayout) findViewById(R.id.main_layout);
		LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		View buttonLayout = inflater.inflate(R.layout.button_layout,null);
		mainLayout.addView(buttonLayout);

		initList();
		myListView = (MyListView) findViewById(R.id.my_list_view);
		myListView.setOnDeleteListener(new MyListView.OnDeleteListener() {
			@Override
			public void onDelete(int index) {
				contentList.remove(index);
				adapter.notifyDataSetChanged();
			}
		});
		adapter = new MyAdapter(this, 0, contentList);
		myListView.setAdapter(adapter);*/
		/*PieView pieView = new PieView(this);
		setContentView(pieView);
		ArrayList<PieData> datas = new ArrayList<>();
		PieData pieData = new PieData("sloop", 30);
		PieData pieData2 = new PieData("sloop", 30);
		PieData pieData3 = new PieData("sloop", 30);
		PieData pieData4 = new PieData("sloop", 30);
		datas.add(pieData);
		datas.add(pieData2);
		datas.add(pieData3);
		datas.add(pieData4);
		pieView.setStartAngle(90);
		pieView.setData(datas);
		for(PieData data: datas)
		{
			Log.i("zx", data.toString());
		}*/


	}


	/*private void initList() {
		contentList.add("Content Item 1");
		contentList.add("Content Item 2");
		contentList.add("Content Item 3");
		contentList.add("Content Item 4");
		contentList.add("Content Item 5");
		contentList.add("Content Item 6");
		contentList.add("Content Item 7");
		contentList.add("Content Item 8");
		contentList.add("Content Item 9");
		contentList.add("Content Item 10");
		contentList.add("Content Item 11");
		contentList.add("Content Item 12");
		contentList.add("Content Item 13");
		contentList.add("Content Item 14");
		contentList.add("Content Item 15");
		contentList.add("Content Item 16");
		contentList.add("Content Item 17");
		contentList.add("Content Item 18");
		contentList.add("Content Item 19");
		contentList.add("Content Item 20");
	}*/
}
