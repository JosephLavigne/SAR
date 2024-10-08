package task3.abastract;

public abstract class NewTask {
		public abstract void post(Runnable r);
		private abstract static NewTask task();
		public abstract void kill();
		public abstract boolean killed();


}
