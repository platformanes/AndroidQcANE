package com.qianchiAne.qcane
{
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	public class QCANE extends EventDispatcher
	{
		private static var _instance:QCANE;
		


		private var extContext:ExtensionContext;
		public function QCANE(singletonEnforcer:SingletonEnforcer)
		{
			super(null);
			
			extContext = ExtensionContext.createExtensionContext("com.qianchiAne.qcane", null);
			if (extContext == null) throw 'ANE 初始化出错';
			extContext.addEventListener(StatusEvent.STATUS,onStatusHandler);
		}
		
		/**
		 * 千尺支付 
		 * @param appKey 由千尺游戏平台分配的标识码
		 * @param exorderno 外部交易订单号，由游戏生成传递给SDK，必须保证唯一性，并且长度不超过25位
		 * @param guid 当前充值用户的唯一编号：游戏需保证用户编号的唯一性,而且长度不超过16位（若超过16位，则自动截取最后16位）
		 * @param gameName 游戏名称
		 * @param fixedValue 支付金额，有支付金额，优先使用支付金额，进行支付 "10,20,30"
		 * @param selectFaceValue 充值面额选择列表。有支付金额，优先使用传入的支付金额进行支付。没有支付金额传入，需填写支付面额数组。面额数组长度不大于8.-----暂时默认填0
		 * @param gameUid 游戏唯一的用户ID，不接sdk登录模块的使用该参数.不超过50位
		 * @param gameServer 游戏大服 非必须
		 * @param gameRegion 游戏大区 非必须
		 * @param gameRole 角色名称
		 * @param gameType 游戏类型 	1：网游 2：单机
		 * @param gameDesc 当前进入支付流程的游戏用户编号
		 * @param notifyUrl 游戏方用于接收交易结果通知的服务端地址，如该值为空，游戏平台会将交易结果通知到游戏提供的默认回调地址上	http开头(可传null)
		 * 
		 */		
		public function pay(appKey:String,exorderno:String,guid:String,gameName:String,fixedValue:int,
							gameUid:String,gameRole:String,gameType:int,selectFaceValue:String='',
							gameDesc:String='',gameServer:String='',gameRegion:String='',notifyUrl:String=''):void
		{
			extContext.call("pay",appKey,exorderno,guid,gameName,fixedValue,selectFaceValue,gameUid,gameServer,gameRegion,gameRole,gameType,gameDesc,notifyUrl);	
		}
		
		/**
		 * 游戏初始化完成后 调用
		 */
		public function init():void
		{
			extContext.call('init');
		}
		
		/**
		 * 暂时没有实现
		 */
		public function login():void
		{
			extContext.call('login');
		}
		
		public function test():void
		{
			extContext.call('test');
		}
		
		
		protected function onStatusHandler(event:StatusEvent):void
		{
			if(event.code == QCEvent.EVENT_PAY_SUCCEED || event.code == QCEvent.EVENT_PAY_FAILED)
			{
				dispatchEvent(new QCEvent(event.code,event.level));
			}
			else
			dispatchEvent(event);	
		}
		
		public static function getInstance():QCANE
		{
			if (_instance == null)
			{
				_instance = new QCANE(new SingletonEnforcer());
			}
			return _instance;
		}
	}
}

class SingletonEnforcer
{
}