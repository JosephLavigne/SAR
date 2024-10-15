package task3.implement;

import task3.abastract.EventPump;
import task3.abastract.NewTask;

public class ImNewTask extends NewTask {
	
	public EventPump pump;
	public boolean killed; // True = task is killed
	private Runnable runnable;
	
	public ImNewTask() {
		this.pump = ImEventPump.getinstance();
		this.killed = false;
		this.runnable = null;
		
	}

	@Override
	public void post(Runnable r) {
		if(this.killed) {
			return;
		}
		this.runnable = r;
		this.pump.post(r);
		
	}

	@Override
	public void kill() {
		this.killed = true;
		
	}

	@Override
	public boolean killed() {
		return this.killed;
	}

}
