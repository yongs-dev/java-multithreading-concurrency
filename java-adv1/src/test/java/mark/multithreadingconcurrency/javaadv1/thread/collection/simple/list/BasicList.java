package mark.multithreadingconcurrency.javaadv1.thread.collection.simple.list;

import java.util.Arrays;

import static mark.multithreadingconcurrency.javaadv1.util.ThreadUtils.sleep;

public class BasicList implements SimpleList {

    private static final int DEFAULT_CAPACITY = 5;

    private Object[] elementData;
    private int size = 0;

    public BasicList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Object o) {
        elementData[size] = o;
        sleep(100); // 멀티스레드 문제를 쉽게 확인하는 코드
        size++;
    }

    @Override
    public Object get(int index) {
        return elementData[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size)) + " size = " + size + ", capacity = " + elementData.length;
    }
}