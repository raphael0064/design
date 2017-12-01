package question.doc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用三个线程按顺序循环打印abc三个字母，比如abcabcabc
 */
public class LoopPrintThreadTest2 {


	public static void main(String[] args) {

		AtomicInteger count = new AtomicInteger(1);
		new Thread(() -> {
			while (true && count.get() < 100) {
				if (count.get() % 3 == 1) {
					System.out.print("a");
					count.incrementAndGet();
				}
			}
		}).start();

		new Thread(() -> {
			while (true && count.get() < 100) {
				if (count.get() % 3 == 2) {
					System.out.print("b");
					count.incrementAndGet();
				}
			}
		}).start();

		new Thread(() -> {
			while (true && count.get() < 100) {
				if (count.get() % 3 == 0) {
					System.out.print("c");
					count.incrementAndGet();
				}
			}
		}).start();
	}
}
