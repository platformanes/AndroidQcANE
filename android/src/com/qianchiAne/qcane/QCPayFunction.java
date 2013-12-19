package com.qianchiAne.qcane;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.qianchi.sdk.pay.bean.PayInfo;
import com.qianchi.sdk.pay.common.FinishPayData;
import com.qianchi.sdk.pay.logic.PayEntry;
import com.qianchi.sdk.pay.logic.PayEntry.OnPayProcessListener;

public class QCPayFunction implements FREFunction {

	@Override
	public FREObject call(final FREContext context, FREObject[] args) {

		
		PayInfo payInfo = new PayInfo();			    // payInfo 支付所需信息封装对象
		try
		{
			// 由千尺游戏平台分配的标识码
			String appKey = args[0].getAsString();
			// 外部交易订单号，由游戏生成传递给SDK，必须保证唯一性，并且长度不超过25位
			String exorderno = args[1].getAsString();
			// 当前充值用户的唯一编号：游戏需保证用户编号的唯一性,而且长度不超过16位（若超过16位，则自动截取最后16位）
			String Guid = args[2].getAsString();
			// 游戏名称
			String gameName = args[3].getAsString();
			// 支付金额，有支付金额，优先使用支付金额，进行支付
			int fixedValue = args[4].getAsInt();
			// 充值面额选择列表。有支付金额，优先使用传入的支付金额进行支付。没有支付金额传入，需填写支付面额数组。面额数组长度不大于8.  
			String vs = args[5].getAsString();
			String v[] = vs.split(",");
			int[] selectFaceValues = new int[v.length];
			
			for (int i = 0; i < v.length; i++) {
				selectFaceValues[i] = Integer.parseInt(v[i]);
			}
			
			//游戏唯一的用户ID，不接sdk登录模块的使用该参数.不超过50位
			String exGameUid = args[6].getAsString();
			// 游戏大服 非必须
			String gameServer = args[7].getAsString();
			// 游戏大区 非必须  
			String gameRegion = args[8].getAsString();
			// 角色名称
			String gameRole = args[9].getAsString();
			// 游戏类型    1：网游 2：单机
			int gameType = args[10].getAsInt();
			// 当前进入支付流程的游戏用户编号
			String gameDesc = args[11].getAsString();
			// 游戏方用于接收交易结果通知的服务端地址，如该值为空，游戏平台会将交易结果通知到游戏提供的默认回调地址上  http 开头(可传null)  
			String notifyUrl = args[12].getAsString();
			
	
			
			payInfo.setAppKey(appKey);
			payInfo.setExorderno(exorderno);
			payInfo.setGuid(Guid);
			payInfo.setGameName(gameName);
			payInfo.setFixedValue(fixedValue);
			payInfo.setSuid(0);						// Sdk的用户id。接入千尺通行证的游戏，登录成功后可获取到。没有接千尺通行证的，传入默认值 0；
			if (vs != "")
			{
				payInfo.setSelectFaceValues(selectFaceValues);
			}
			else
			{
				payInfo.setSelectFaceValues(new int[0]);
			}
			payInfo.setGameType(gameType);
			payInfo.setExGameUid(exGameUid);
			if (gameServer != "")
			payInfo.setGameServer(gameServer);
			if (gameRegion != "")
			payInfo.setGameRegion(gameRegion);
			payInfo.setGameRole(gameRole);
			if (gameDesc != "")
			payInfo.setGameDesc(gameDesc);
			if (notifyUrl != "")
				payInfo.setNotifyUrl(notifyUrl);
			else
				payInfo.setNotifyUrl(null);
			
			Log.d("payinfo appKey",appKey);
			Log.d("payinfo exorderno",exorderno);
			Log.d("payinfo Guid",Guid);
			Log.d("payinfo gameName",gameName);
			Log.d("payinfo fixedValue",String.valueOf(fixedValue));
//			Log.d("payinfo selectFaceValues",String.valueOf(selectFaceValues));
			Log.d("payinfo gameType",String.valueOf(gameType));
			Log.d("payinfo exGameUid",exGameUid);
			Log.d("payinfo gameServer",gameServer);
			Log.d("payinfo gameRegion",gameRegion);
			Log.d("payinfo gameRole",gameRole);
			Log.d("payinfo gameDesc",gameDesc);
			Log.d("payinfo notifyUrl",notifyUrl);
			
		}
		catch(Exception e)
		{
			
		}
		Log.d("payinfo", payInfo.toString());
		Log.d("start","pay");
		PayEntry.startPayProcess(context.getActivity(), payInfo, new OnPayProcessListener()
		{
			@Override
			public void finishPayProcess(int result, FinishPayData info) 
			{
				if(result==10000)
				{
					//支付成功操作
					QCExtension.log("支付成功");
					context.dispatchStatusEventAsync("paySucceed","");
				}
				else
				{
					//支付失败操作
					QCExtension.log("支付失败 返回: " + result);
					context.dispatchStatusEventAsync("payFailed",String.valueOf(result));
				}
			}
		});
		return null;
	}

}
