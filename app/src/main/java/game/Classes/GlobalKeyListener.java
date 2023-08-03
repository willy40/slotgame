package game.Classes;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {
	public interface SpinKeyEvent{
		void Process();
	}

	public interface EndKeyEvent{
		void Process();
	}

	public interface AddCreditsKeyEvent{
		void Process();
	}

	public SpinKeyEvent spinKeyEventListner;
	public EndKeyEvent endKeyEventListner;
	public AddCreditsKeyEvent addAddCreditsKeyEvent;

	public void nativeKeyPressed(NativeKeyEvent e) {
		switch (e.getKeyCode()){
			case NativeKeyEvent.VC_ESCAPE:
			{
				if(endKeyEventListner != null)
					endKeyEventListner.Process();
			}; break;

			case NativeKeyEvent.VC_SPACE:{
				if(spinKeyEventListner != null)
					spinKeyEventListner.Process();
			}; break;

			case NativeKeyEvent.VC_A: {
				if(addAddCreditsKeyEvent != null){
					addAddCreditsKeyEvent.Process();
				}
			}; break;
		}
	}
}
