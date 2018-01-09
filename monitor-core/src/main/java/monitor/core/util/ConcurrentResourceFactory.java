package monitor.core.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by lizhitao on 2018/1/9.
 * ConcurrentResourceFactory
 */
public class ConcurrentResourceFactory<T, R> {
    private R[] resources;
    private AtomicReference<T>[] values;
    private LinkedBlockingQueue<Integer> linkedBlockingQueue;

    @SuppressWarnings("unchecked")
    public ConcurrentResourceFactory(int size) {
        if (size > 5000) {
            throw new RuntimeException("more than 5000:" + size);
        } else if (size <= 0) {
            throw new RuntimeException("less than 0:" + size);
        } else {
            this.values = new AtomicReference[size];

            int i;
            for (i = 0; i < size; ++i) {
                this.values[i] = new AtomicReference();
                this.values[i].set(null);
            }

            this.resources = (R[]) new Object[size];
            this.linkedBlockingQueue = new LinkedBlockingQueue<Integer>(size);

            for (i = 0; i < size; ++i) {
                this.linkedBlockingQueue.add(i);
            }

        }
    }

    @SuppressWarnings("unchecked")
    public T obtainValue(int resourceId, Class<? extends T> clazz) {
        AtomicReference atomicReference = this.values[resourceId];
        Object object = atomicReference.get();
        if (object != null) {
            return (T) object;
        } else {
            Object instance;
            try {
                instance = clazz.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("failed to instantiation!", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("IllegalAccessException!", e);
            }

            boolean flag = atomicReference.compareAndSet(null, instance);
            return (T) (flag ? instance : atomicReference.get());
        }
    }

    public AtomicReference<T>[] getAllValues() {
        return this.values;
    }

    public int size() {
        Integer size = this.linkedBlockingQueue.peek();
        return size == null ? this.resources.length : size;
    }

    public Integer registerResource(R resource) {
        Integer index = this.linkedBlockingQueue.poll();
        if (index == null) {
            return null;
        } else {
            this.resources[index] = resource;
            return index;
        }
    }

    public R getResource(int resourceId) {
        return this.resources[resourceId];
    }
}
