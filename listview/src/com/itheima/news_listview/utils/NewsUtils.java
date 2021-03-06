package com.itheima.news_listview.utils;

import java.util.ArrayList;

import android.content.Context;

import com.itheima.news_listview.R;
import com.itheima.news_listview.bean.NewsBean;

public class NewsUtils {

	//封装新闻的假数据到list中返回
	public static ArrayList<NewsBean> getAllNews(Context context) {

		ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();

		for(int i = 0 ;i <100;i++)
		{
			NewsBean newsBean = new NewsBean();
			
			newsBean.des= "称谢霆锋隐私权收到侵犯，将保留追究法律责任";
		
			newsBean.icon = context.getResources().getDrawable(R.drawable.ic_launcher);//通过context对象将一个资源id转换成一个Drawable对象。
			arrayList.add(newsBean);


			NewsBean newsBean1 = new NewsBean();
			newsBean1.des= "身边的人都知道谢霆锋最爱王菲，二人早有复合迹象";
			newsBean1.icon = context.getResources().getDrawable(R.drawable.icon);//通过context对象将一个资源id转换成一个Drawable对象。
			arrayList.add(newsBean1);



			NewsBean newsBean2 = new NewsBean();
			newsBean2.des= "74期平均薪资20000，其中有一个哥们超过10万，这些It精英都迎娶了白富美.";
			newsBean2.icon = context.getResources().getDrawable(R.drawable.icon2);//通过context对象将一个资源id转换成一个Drawable对象。
			arrayList.add(newsBean2);
		}
		return arrayList;
	}

}
