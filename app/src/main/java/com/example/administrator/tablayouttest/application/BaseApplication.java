package com.example.administrator.tablayouttest.application;

import android.app.Application;
import android.os.Handler;
/**
 * ============================================================
 * 
 * 版 权 ： 黑马程序员教育集团 版权所有 (c) 2015
 * 
 * 作 者 : 马伟奇
 * 
 * 版 本 ： 1.0
 * 
 * 创建日期 ： 2015-8-19 上午9:28:21
 * 
 * 描 述 ：
 * 
 *      获取到常用的工具类
 * 修订历史 ：
 * 
 * ============================================================
 **/
public class BaseApplication extends Application {
	//获取到主线程的上下文
	private static BaseApplication mContext;
	//获取到主线程的handler
	private static Handler mMainThreadHanler;
	//获取到主线程
	private static Thread mMainThread;
	//获取到主线程的id
	private static int mMainThreadId;
	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		this.mContext = this;
		this.mMainThreadHanler = new Handler();
		this.mMainThread = Thread.currentThread();
		//获取到调用线程的id
		this.mMainThreadId = android.os.Process.myTid();
	}
	
	public static BaseApplication getApplication(){
		return mContext;
	}
	
	public static Handler getMainThreadHandler(){
		return mMainThreadHanler;
	}
	
	public static Thread getMainThread(){
		return mMainThread;
	}
	
	public static int getMainThreadId(){
		return mMainThreadId;
	}
	
	
	
	
	
	
}
