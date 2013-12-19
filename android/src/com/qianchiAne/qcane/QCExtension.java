package com.qianchiAne.qcane;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;


public class QCExtension implements FREExtension {

	private static FREContext context;
	@Override
	public FREContext createContext(String arg0) {

		context = new QCContext();
		return context;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}
	
	private static String TAG = "log";
	public static void log(String content){
		Log.d(TAG, content);
		context.dispatchStatusEventAsync(TAG, content);
	}

}
