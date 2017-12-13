## java多线程编程

**一、多线程的优缺点**

多线程的优点：

1）资源利用率更好
2）程序设计在某些情况下更简单
3）程序响应更快

多线程的代价：

1）设计更复杂
虽然有一些多线程应用程序比单线程的应用程序要简单，但其他的一般都更复杂。在多线程访问共享数据的时候，这部分代码需要特别的注意。线程之间的交互往往非常复杂。不正确的线程同步产生的错误非常难以被发现，并且重现以修复。

2）上下文切换的开销
当CPU从执行一个线程切换到执行另外一个线程的时候，它需要先存储当前线程的本地的数据，程序指针等，然后载入另一个线程的本地数据，程序指针等，最后才开始执行。这种切换称为”(“context switch”)。CPU会在一个上下文中执行一个线程，然后切换到另外一个上下文中执行另外一个线程。上下文切换并不廉价。如果没有必要，应该减少上下文切换的发生。

**二、创建java多线程**

1、创建Thread的子类

创建Thread子类的一个实例并重写run方法，run方法会在调用start()方法之后被执行。例子如下：

```

public class MyThread extends Thread {

   public void run(){

     System.out.println("MyThread running");

   }

}

`MyThread myThread = new MyThread();`

`myTread.start();`

```
也可以如下创建一个Thread的匿名子类：

```

`Thread thread = new Thread(){`

`   public void run(){`

`     System.out.println("Thread Running");`

`   }`

`};`

`thread.start();`

```

2、实现Runnable接口

第二种编写线程执行代码的方式是新建一个实现了java.lang.Runnable接口的类的实例，实例中的方法可以被线程调用。下面给出例子：

```

`public class MyRunnable implements Runnable {`

`   public void run(){`

`    System.out.println("MyRunnable running");`

`   }`

`}`

`Thread thread = new Thread(new MyRunnable());`

`thread.start();`

```

同样，也可以创建一个实现了Runnable接口的匿名类，如下所示：

```

`Runnable myRunnable = new Runnable(){`

`   public void run(){`

`     System.out.println("Runnable running");`

`   }`

`}`

`Thread thread = new Thread(myRunnable);`

`thread.start();`

```

**三、线程安全**

在同一程序中运行多个线程本身不会导致问题，问题在于多个线程访问了相同的资源。如同一内存区（变量，数组，或对象）、系统（数据库，web services等）或文件。实际上，这些问题只有在一或多个线程向这些资源做了写操作时才有可能发生，只要资源没有发生变化，多个线程读取相同的资源就是安全的。

当两个线程竞争同一资源时，如果对资源的访问顺序敏感，就称存在竞态条件。导致竞态条件发生的代码区称作临界区。

如果一个资源的创建，使用，销毁都在同一个线程内完成，且永远不会脱离该线程的控制，则该资源的使用就是线程安全的。

**四、java同步块**

Java中的同步块用synchronized标记。同步块在Java中是同步在某个对象上。所有同步在一个对象上的同步块在同时只能被一个线程进入并执行操作。所有其他等待进入该同步块的线程将被阻塞，直到执行该同步块中的线程退出。

有四种不同的同步块：

实例方法同步：

```

` public synchronized void add(int value){`

`this.count += value;`

` }`

```

Java实例方法同步是同步在拥有该方法的对象上。这样，每个实例其方法同步都同步在不同的对象上，即该方法所属的实例。只有一个线程能够在实例方法同步块中运行。如果有多个实例存在，那么一个线程一次可以在一个实例同步块中执行操作。一个实例一个线程。

静态方法同步：

```

`public static synchronized void add(int value){`

` count += value;`

` }`

```

静态方法的同步是指同步在该方法所在的类对象上。因为在Java虚拟机中一个类只能对应一个类对象，所以同时只允许一个线程执行同一个类中的静态同步方法。

实例方法中的同步块：

```

`public void add(int value){`

`    synchronized(this){`

`       this.count += value;`

`    }`

`  }`

```

注意Java同步块构造器用括号将对象括起来。在上例中，使用了&ldquo;this&rdquo;，即为调用add方法的实例本身。在同步构造器中用括号括起来的对象叫做监视器对象。上述代码使用监视器对象同步，同步实例方法使用调用方法本身的实例作为监视器对象。一次只有一个线程能够在同步于同一个监视器对象的Java方法内执行。

下面两个例子都同步他们所调用的实例对象上，因此他们在同步的执行效果上是等效的。

```

`public class MyClass {`

`   public synchronized void log1(String msg1, String msg2){`

`      log.writeln(msg1);`

`      log.writeln(msg2);`

`   }`

`   public void log2(String msg1, String msg2){`

`      synchronized(this){`

`         log.writeln(msg1);`

`         log.writeln(msg2);`

`      }`

`   }`

` }`

```

静态方法中的同步块：

```

`public class MyClass {`

`    public static synchronized void log1(String msg1, String msg2){`

`       log.writeln(msg1);`

`       log.writeln(msg2);`

`    }`

`    public static void log2(String msg1, String msg2){`

`       synchronized(MyClass.class){`

`          log.writeln(msg1);`

`          log.writeln(msg2);`

`       }`

`    }`

`  }`

```

这两个方法不允许同时被线程访问。如果第二个同步块不是同步在MyClass.class这个对象上。那么这两个方法可以同时被线程访问。

**五、java线程通信**

线程通信的目标是使线程间能够互相发送信号。另一方面，线程通信使线程能够等待其他线程的信号。

Java有一个内建的等待机制来允许线程在等待信号的时候变为非运行状态。java.lang.Object 类定义了三个方法，wait()、notify()和notifyAll()来实现这个等待机制。

一个线程一旦调用了任意对象的wait()方法，就会变为非运行状态，直到另一个线程调用了同一个对象的notify()方法。为了调用wait()或者notify()，线程必须先获得那个对象的锁。也就是说，线程必须在同步块里调用wait()或者notify()。

以下为一个使用了wait()和notify()实现的线程间通信的共享对象：

```

`public class MyWaitNotify{`

`  MonitorObject myMonitorObject = new MonitorObject();`

`  boolean wasSignalled = false;`

`  public void doWait(){`

`    synchronized(myMonitorObject){`

`      while(!wasSignalled){`

`        try{`

`          myMonitorObject.wait();`

`         } catch(InterruptedException e){...}`

`      }`

`      //clear signal and continue running.`

`      wasSignalled = false;`

`    }`

`  }`

`  public void doNotify(){`

`    synchronized(myMonitorObject){`

`      wasSignalled = true;`

`      myMonitorObject.notify();`

`    }`

`  }`

`}`

```

注意以下几点：

1、不管是等待线程还是唤醒线程都在同步块里调用wait()和notify()。这是强制性的！一个线程如果没有持有对象锁，将不能调用wait()，notify()或者notifyAll()。否则，会抛出IllegalMonitorStateException异常。

2、一旦线程调用了wait()方法，它就释放了所持有的监视器对象上的锁。这将允许其他线程也可以调用wait()或者notify()。

3、为了避免丢失信号，必须把它们保存在信号类里。如上面的wasSignalled变量。

4、假唤醒：由于莫名其妙的原因，线程有可能在没有调用过notify()和notifyAll()的情况下醒来。这就是所谓的假唤醒（spurious wakeups）。为了防止假唤醒，保存信号的成员变量将在一个while循环里接受检查，而不是在if表达式里。这样的一个while循环叫做自旋锁。

5、不要在字符串常量或全局对象中调用wait()。即上面MonitorObject不能是字符串常量或是全局对象。每一个MyWaitNotify的实例都拥有一个属于自己的监视器对象，而不是在空字符串上调用wait()/notify()。

**六、java中的锁**

自Java 5开始，java.util.concurrent.locks包中包含了一些锁的实现，因此你不用去实现自己的锁了。

常用的一些锁：

java.util.concurrent.locks.Lock;
java.util.concurrent.locks.ReentrantLock;
java.util.concurrent.locks.ReadWriteLock;
java.util.concurrent.locks.ReentrantReadWriteLock;

一个可重入锁（reentrant lock）的简单实现：

```

`public class Lock {`

`    boolean isLocked = false;`

`    Thread  lockedBy = null;`

`    int lockedCount = 0;`

`    public synchronized void lock() throws InterruptedException{`

`        Thread callingThread = Thread.currentThread();`

`        while(isLocked && lockedBy != callingThread){`

`            wait();`

`        }`

`        isLocked = true;`

`        lockedCount++;`

`        lockedBy = callingThread;`

`    }`

`    public synchronized void unlock(){`

`        if(Thread.currentThread() == this.lockedBy){`

`            lockedCount--;`

`            if(lockedCount == 0){`

`                isLocked = false;`

`                notify();`

`            }`

`        }`

`    }`

`}`

```

注意的一点：在finally语句中调用unlock()

```

`lock.lock();`

`try{`

`    //do critical section code, which may throw exception`

`} finally {`

`    lock.unlock();`

`}`

```

**七、java中其他同步方法**

信号量（Semaphore）：java.util.concurrent.Semaphore

阻塞队列（Blocking Queue）：java.util.concurrent.BlockingQueue

```

`public class BlockingQueue {`

`    private List queue = new LinkedList();`

`    private int limit = 10;`

`    public BlockingQueue(int limit) {`

`        this.limit = limit;`

`    }`

`    public synchronized void enqueue(Object item) throws InterruptedException {`

`        while (this.queue.size() == this.limit) {`

`            wait();`

`        }`

`        if (this.queue.size() == 0) {`

`            notifyAll();`

`        }`

`        this.queue.add(item);`

`    }`

`    public synchronized Object dequeue() throws InterruptedException {`

`        while (this.queue.size() == 0) {`

`            wait();`

`        }`

`        if (this.queue.size() == this.limit) {`

`            notifyAll();`

`        }`

`        return this.queue.remove(0);`

`    }`

`}`

```

**八、java中的线程池**

Java通过Executors提供四种线程池，分别为：

newCachedThreadPool

创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。

newFixedThreadPool 

创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。

newScheduledThreadPool 

创建一个大小无限制的线程池。此线程池支持定时以及周期性执行任务。

newSingleThreadExecutor

创建一个单线程的线程池。此线程池支持定时以及周期性执行任务。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。

线程池简单用法：

```

`import java.util.concurrent.ExecutorService;`

`import java.util.concurrent.Executors;`

`public class Main {`

`    public static void main(String[] args) {`

`        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();`

`        for (int i = 0; i < 10; i++) {`

`            final int index = i;`

`            cachedThreadPool.execute(new Runnable() {`

`                public void run() {`

`                    System.out.println(index);`

`                }`

`            });`

`        }`

`    }`

`}`

```

参考：

http://tutorials.jenkov.com/java-concurrency/index.html

http://ifeve.com/java-concurrency-thread-directory/

Java线程池的分析和使用：http://ifeve.com/java-threadpool/



