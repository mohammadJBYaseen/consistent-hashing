package org.yaseen.consistenthashing;

public interface KeyHashGenerator<T> {
    long hash( T key );
}
