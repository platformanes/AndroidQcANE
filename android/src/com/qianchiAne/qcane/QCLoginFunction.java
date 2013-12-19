package com.qianchiAne.qcane;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.qianchi.sdk.login.base.QCListener;
import com.qianchi.sdk.login.constant.Passport;

public class QCLoginFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {

		login(context, args);
		return null;
	}

	private void login(FREContext context, FREObject[] args) {

		Passport p = new Passport();
		p.startPoster(context.getActivity(), new QCListener() {
			@Override
			public void onComplete(String response) {
				// ��¼�ɹ�ֱ�ӿɴ� response ��ȡ�� guid �� tokenId
				try {
					String guid = new JSONObject(response).getString("guid");
					String tokenId = new JSONObject(response)
							.getString("tokenId");
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		});
	}

}
