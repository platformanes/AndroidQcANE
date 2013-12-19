package com.qianchiAne.qcane
{
	import flash.events.Event;
	
	public class QCEvent extends Event
	{
		public static const EVENT_PAY_SUCCEED:String = 'paySucceed';
		public static const EVENT_PAY_FAILED:String = 'payFailed';
		
		private var _data:Object;
		public function QCEvent(type:String,data:Object, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			_data = data;
		}
		
		public function get data():Object
		{
			return _data;
		}
		
		override public function clone():Event
		{
			return new QCEvent(type,_data, bubbles,cancelable);
		}
	}
}