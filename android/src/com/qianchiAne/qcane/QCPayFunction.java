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

		
		PayInfo payInfo = new PayInfo();			    // payInfo ֧��������Ϣ��װ����
		try
		{
			// ��ǧ����Ϸƽ̨����ı�ʶ��
			String appKey = args[0].getAsString();
			// �ⲿ���׶����ţ�����Ϸ���ɴ��ݸ�SDK�����뱣֤Ψһ�ԣ����ҳ��Ȳ�����25λ
			String exorderno = args[1].getAsString();
			// ��ǰ��ֵ�û���Ψһ��ţ���Ϸ�豣֤�û���ŵ�Ψһ��,���ҳ��Ȳ�����16λ��������16λ�����Զ���ȡ���16λ��
			String Guid = args[2].getAsString();
			// ��Ϸ����
			String gameName = args[3].getAsString();
			// ֧������֧��������ʹ��֧��������֧��
			int fixedValue = args[4].getAsInt();
			// ��ֵ���ѡ���б���֧��������ʹ�ô����֧��������֧����û��֧�����룬����д֧��������顣������鳤�Ȳ�����8.  
			String vs = args[5].getAsString();
			String v[] = vs.split(",");
			int[] selectFaceValues = new int[v.length];
			
			for (int i = 0; i < v.length; i++) {
				selectFaceValues[i] = Integer.parseInt(v[i]);
			}
			
			//��ϷΨһ���û�ID������sdk��¼ģ���ʹ�øò���.������50λ
			String exGameUid = args[6].getAsString();
			// ��Ϸ��� �Ǳ���
			String gameServer = args[7].getAsString();
			// ��Ϸ���� �Ǳ���  
			String gameRegion = args[8].getAsString();
			// ��ɫ����
			String gameRole = args[9].getAsString();
			// ��Ϸ����    1������ 2������
			int gameType = args[10].getAsInt();
			// ��ǰ����֧�����̵���Ϸ�û����
			String gameDesc = args[11].getAsString();
			// ��Ϸ�����ڽ��ս��׽��֪ͨ�ķ���˵�ַ�����ֵΪ�գ���Ϸƽ̨�Ὣ���׽��֪ͨ����Ϸ�ṩ��Ĭ�ϻص���ַ��  http ��ͷ(�ɴ�null)  
			String notifyUrl = args[12].getAsString();
			
	
			
			payInfo.setAppKey(appKey);
			payInfo.setExorderno(exorderno);
			payInfo.setGuid(Guid);
			payInfo.setGameName(gameName);
			payInfo.setFixedValue(fixedValue);
			payInfo.setSuid(0);						// Sdk���û�id������ǧ��ͨ��֤����Ϸ����¼�ɹ���ɻ�ȡ����û�н�ǧ��ͨ��֤�ģ�����Ĭ��ֵ 0��
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
					//֧���ɹ�����
					QCExtension.log("֧���ɹ�");
					context.dispatchStatusEventAsync("paySucceed","");
				}
				else
				{
					//֧��ʧ�ܲ���
					QCExtension.log("֧��ʧ�� ����: " + result);
					context.dispatchStatusEventAsync("payFailed",String.valueOf(result));
				}
			}
		});
		return null;
	}

}
