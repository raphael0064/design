package question.doc;

/**
 * 用三个线程按顺序循环打印abc三个字母，比如abcabcabc
 */
public class LoopPrintThreadTest {
	private int count;

	public LoopPrintThreadTest(int count) {
		this.count = count;
	}

	public synchronized int getCount() {
		return count;
	}

	public synchronized int incrCount() {
		return ++count;
	}

	public static void main(String[] args) {
		LoopPrintThreadTest test = new LoopPrintThreadTest(1);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true && test.getCount() < 100) {
					if (test.getCount() % 3 == 1) {
						System.out.print("a");
						test.incrCount();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true && test.getCount() < 100) {
					if (test.getCount() % 3 == 2) {
						System.out.print("b");
						test.incrCount();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true && test.getCount() < 100) {
					if (test.getCount() % 3 == 0) {
						System.out.print("c");
						test.incrCount();
					}
				}
			}
		}).start();
	}
}
