package com.example.administrator.tablayouttest.protocol;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import android.os.SystemClock;

import com.example.administrator.tablayouttest.utils.FileUtils;
import com.example.administrator.tablayouttest.utils.IOUtils;
import com.example.administrator.tablayouttest.utils.LogUtils;
import com.example.administrator.tablayouttest.utils.StringUtils;


/**
 * Created by mwqi on 2014/6/7.
 */
public abstract class BaseProtocol<Data> {

	/** 需要增加的额外参数 */
	protected String getParames() {
		return "";
	}

	/** 该协议的访问地址 */
	protected abstract String getKey();

	/** 从json中解析 */
	protected abstract Data parseFromJson(String json);
}
