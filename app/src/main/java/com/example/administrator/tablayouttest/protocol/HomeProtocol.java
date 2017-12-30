package com.example.administrator.tablayouttest.protocol;


import com.example.administrator.tablayouttest.bean.AppInfo;
import com.example.administrator.tablayouttest.http.okHttp;
import com.example.administrator.tablayouttest.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class HomeProtocol extends BaseProtocol<List<AppInfo>> {
	private List<String> mPictureUrl;

	@Override
	protected String getKey() {
		return "home";
	}

	public List<String> getPictureUrl() {
		return mPictureUrl;
	}


	@Override
	protected List<AppInfo> parseFromJson(String json) {
		okHttp ok = new okHttp();
		String json01=ok.okHttp01();
		try {
			JSONObject jsonObject = new JSONObject(json01);
			mPictureUrl = new ArrayList<String>();
			JSONArray array = jsonObject.optJSONArray("picture");
			if(array != null){
				for (int i = 0; i < array.length(); i++) {
					mPictureUrl.add(array.getString(i));
				}
			}
			List<AppInfo> list = new ArrayList<AppInfo>();
			array = jsonObject.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				AppInfo info = new AppInfo();
				info.setId(obj.getLong("id"));
				info.setName(obj.getString("name"));
				info.setPackageName(obj.getString("packageName"));
				info.setIconUrl(obj.getString("iconUrl"));
				info.setStars(Float.valueOf(obj.getString("stars")));
				info.setSize(obj.getLong("size"));
				info.setDownloadUrl(obj.getString("downloadUrl"));
				info.setDes(obj.getString("des"));
				list.add(info);
			}
			return list;
		} catch (Exception e) {
			LogUtils.e(e);
			return null;
		}
	}

}
