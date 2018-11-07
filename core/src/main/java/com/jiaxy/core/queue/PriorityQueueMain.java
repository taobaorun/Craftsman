package com.jiaxy.core.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueMain {

    public static void main(String[] args) {
        PriorityQueue<Value> heap = new PriorityQueue<>(Value.COMPARATOR2);
       /* topk(heap,5,new Value(90));
        topk(heap,5,new Value(10));
        topk(heap,5,new Value(8));
        topk(heap,5,new Value(9));
        topk(heap,5,new Value(7));
        topk(heap,5,new Value(12));*/
        heap.offer(new Value(90));
        heap.offer(new Value(10));
        heap.offer(new Value(8));
        heap.offer(new Value(9));
        heap.offer(new Value(7));
        heap.offer(new Value(12));
        /*for (Value value : heap) {
            System.out.println(value.getV());
        }*/
        Value value = null;
        while ((value = heap.poll()) != null) {
            System.out.println(value.getV());
        }

    }

    public static void topk(PriorityQueue<Value> queue, int k, Value s) {
        if (queue.size() < k ||
                queue.peek().getV() < s.getV()) {
            if (queue.size() == k) {
                queue.poll();
            }
            queue.offer(s);
        }
    }

}

class Value {

    private Integer v;

    public Value(Integer v) {
        this.v = v;
    }

    public static Comparator<Value> COMPARATOR = new Comparator<Value>() {
        public int compare(Value o1, Value o2) {
            if (o1.getV() > o2.getV()) {
                return 1;
            } else if (o1.getV() < o2.getV()) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    public static final Comparator<Value> COMPARATOR2 = (o1, o2) -> o1.getV() > o2.getV() ? 1 : (o1.getV() < o2.getV() ? -1 : 0);

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}