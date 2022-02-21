package com.github.johnwlutils.bvh.morton_code;

import com.github.johnwlutils.bvh.math.Vector3;
import com.github.johnwlutils.bvh.math.Vector3Int;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MortonMapper {
    public static <T> Map<Long, T> mapElements(
            final List<T> elements,
            final Function<T, Vector3> objectPositionMapper,
            final Function<Vector3, Vector3Int> linearTransform) {
        final Map<Long, T> mortonMap = new HashMap<>(elements.size());

        elements.forEach(t -> mortonMap.put(MortonEncoder3D.encode(
                linearTransform.apply(objectPositionMapper.apply(t))), t));

        return mortonMap;
    }

}
