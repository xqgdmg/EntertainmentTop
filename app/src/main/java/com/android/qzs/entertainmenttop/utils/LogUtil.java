package com.android.qzs.entertainmenttop.utils;

import android.util.Log;


/***
 * 日志工具类
 * @author ilishzero
 *
 */
public class LogUtil {
	
	/***
	 * 控制台日志
	 * @param msg	内容
	 */
	public static void logConsole(String msg){
		if(SystemAttributes.isDebug){
			System.out.println(msg);
		}
	}
	
	/***
	 *	带tag的日志
	 * @param tag
	 * @param msg
	 */
	public static void logByTag(String tag, String msg){
		if(SystemAttributes.isDebug){
			Log.i(tag, msg);
		}
	}
}
