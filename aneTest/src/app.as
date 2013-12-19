package
{

	
	import com.qianchiAne.qcane.QCANE;
	import com.qianchiAne.qcane.QCEvent;
	
	import flash.display.Sprite;
	import flash.display.StageAlign;
	import flash.display.StageScaleMode;
	import flash.events.MouseEvent;
	import flash.events.StatusEvent;
	import flash.text.TextField;
	
	public class app extends Sprite
	{
		private static var TAG:String = "log";
		public function app()
		{
			super();
			
			// 支持 autoOrient
			stage.align = StageAlign.TOP_LEFT;
			stage.scaleMode = StageScaleMode.NO_SCALE;
			
			QCANE.getInstance().addEventListener(StatusEvent.STATUS,onStatusHandler);
			QCANE.getInstance().addEventListener(QCEvent.EVENT_PAY_SUCCEED,onPaySucceedHandler);
			QCANE.getInstance().addEventListener(QCEvent.EVENT_PAY_FAILED,onPayFailedHandler);
			var label:TextField = new TextField;
			label.text = "ffffffffffffffffffffffffffffffffffffffffffffffffffffffff\nfffffff";
			addChild(label);
			label.addEventListener(MouseEvent.CLICK,onTouchHandler);

			QCANE.getInstance().init();
		}
		
		protected function onPaySucceedHandler(event:QCEvent):void
		{
			trace('支付成功',event.data);
			
		}
		
		protected function onPayFailedHandler(event:QCEvent):void
		{
			trace('支付失败',event.data);
			
		}
		
		protected function onTouchHandler(event:MouseEvent):void
		{
			trace('click');
			QCANE.getInstance().pay("k3j1xiq4dtu8vy89g0nf7780",
				"12121000000000000000022",
			"2222222",
			"测试游戏",
			5,"34433","你好",1,'500,1000,2000,10000',"游戏描述","第一服","第一区 ");
		}
		
		protected function onStatusHandler(event:StatusEvent):void
		{
			if (event.code == TAG)
			{
				trace(event.level);
			}
		}
	}
}