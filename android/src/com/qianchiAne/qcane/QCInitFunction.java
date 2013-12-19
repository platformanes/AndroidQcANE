package com.qianchiAne.qcane;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.qianchi.sdk.common.util.CheckGameData;


public class QCInitFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		CheckGameData.initPayData(context.getActivity(), null);
		Log.d("init","completed");
		return null;
	}

}
