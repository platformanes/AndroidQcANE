package com.qianchiAne.qcane;

import android.content.Intent;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class QCtest implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] arg1) {
		Intent intent = new Intent(context.getActivity(),test.class);
      context.getActivity().startActivity(intent);
		return null;
	}

}
