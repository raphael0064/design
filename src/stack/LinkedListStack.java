package stack;

import java.util.LinkedList;

/**
 * 基于LinkedList实现的栈结构
 */
public class LinkedListStack<E> {
	private LinkedList<E> ll = new LinkedList<E>();

	//入栈
	public void push(E e){
		ll.addFirst(e);
	}

	//查看栈顶元素但不移除
	public E peek(){
		return ll.getFirst();
	}

	//出栈
	public E pop(){
		return ll.removeFirst();
	}

	//判空
	public boolean empty(){
		return ll.isEmpty();
	}

	//打印栈元素
	public String toString(){
		return ll.toString();
	}
}