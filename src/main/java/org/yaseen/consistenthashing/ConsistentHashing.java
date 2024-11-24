package org.yaseen.consistenthashing;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.*;
import java.util.stream.IntStream;

public class ConsistentHashing<T extends Node> {
    private final NavigableMap<Long, T> ring;
    private final int numberOfReplicas;
    private final KeyHashGenerator<String> keyHashGenerator;

    public ConsistentHashing(@Min(1) int numberOfReplicas, @NotNull KeyHashGenerator<String> keyHashGenerator) {
        this.ring = new TreeMap<>();
        this.numberOfReplicas = numberOfReplicas;
        this.keyHashGenerator = keyHashGenerator;
    }

    public void addNode(T node) {
        IntStream.range(0, numberOfReplicas)
                 .forEach(i -> ring.put(keyHashGenerator.hash(node.getName() + ":" + i), node));
    }

    public void removeNode(T node) {
        IntStream.range(0, numberOfReplicas)
                 .forEach(i -> ring.remove(keyHashGenerator.hash(node.getName() + ":" + i)));
    }

    public Optional<T> getNode(String key) {
        if (ring.isEmpty()) {
            return Optional.empty();
        }
        long hashValue = keyHashGenerator.hash(key);
        if(!ring.containsKey(hashValue)) {
            hashValue = Optional.ofNullable(ring.ceilingKey(hashValue)).orElse(ring.firstKey());
        }
        return Optional.of(ring.get(hashValue));
    }
}
