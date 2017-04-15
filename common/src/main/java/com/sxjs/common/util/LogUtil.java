package com.sxjs.common.util;

import android.util.Log;

import com.sxjs.common.CommonConfig;


public class LogUtil {

	public static void i(String tag, String message) {
		if (CommonConfig.DEBUG)
			Log.i(tag, message);
	}

	public static void e(String tag, String message) {
		if (CommonConfig.DEBUG)
			Log.e(tag, message);
	}

	public static void e(String tag, String message, Throwable throwble) {
		if (CommonConfig.DEBUG)
			Log.e(tag, message, throwble);
	}

	public static void w(String tag, String message) {
		if (CommonConfig.DEBUG)
			Log.w(tag, message);
	}

	public static void w(String tag, String message, Throwable throwble) {
		if (CommonConfig.DEBUG)
			Log.w(tag, message, throwble);
	}

	public static void d(String tag, String message) {
		if (CommonConfig.DEBUG)
			Log.d(tag, message);
	}

	public static void v(String tag, String message) {
		if (CommonConfig.DEBUG)
			Log.v(tag, message);
	}

	public static void e(Throwable e) {
		if (CommonConfig.DEBUG)
			e.printStackTrace();
	}

	public static void p(Object e) {
		if (CommonConfig.DEBUG)
			System.out.println(e.toString());
	}
}
