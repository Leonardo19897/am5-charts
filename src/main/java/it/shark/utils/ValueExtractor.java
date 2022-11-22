package it.shark.utils;

public interface ValueExtractor<T, S> {
    S getValue(T data);
}