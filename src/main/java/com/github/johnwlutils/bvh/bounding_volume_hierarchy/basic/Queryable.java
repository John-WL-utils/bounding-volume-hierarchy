package com.github.johnwlutils.bvh.bounding_volume_hierarchy.basic;

public interface Queryable<T, U> {
    U query(T t);
}
