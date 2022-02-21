package com.github.johnwlutils.bvh.bounding_volume_hierarchy.query_radius;

import com.github.johnwlutils.bvh.binary.query_radius.LNQueryRadius;
import com.github.johnwlutils.bvh.bounding_volume_hierarchy.AxisAlignedBoundingBox;

import java.util.function.Function;

class AABBLNQueryRadius<T> extends LNQueryRadius<AxisAlignedBoundingBox, T> {
    public AABBLNQueryRadius(final T leafObject, final Function<T, AxisAlignedBoundingBox> aabbMapper) {
        super(aabbMapper.apply(leafObject), leafObject);
    }
}
