package com.github.johnwl.bvh.bounding_volume_hierarchy.query_radius;

import com.github.johnwl.bvh.binary.basic.Node;
import com.github.johnwl.bvh.bounding_volume_hierarchy.AxisAlignedBoundingBox;
import com.github.johnwl.bvh.bounding_volume_hierarchy.basic.Queryable;
import com.github.johnwl.bvh.math.Vector3;
import com.github.johnwl.bvh.math.Vector3Int;
import com.github.johnwl.bvh.morton_code.MortonMapper;
import com.github.johnwl.bvh.shape.Sphere;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BVHQueryRadius<T> implements Queryable<Sphere, List<T>> {
    private final Optional<Node<AxisAlignedBoundingBox, T>> root;
    private final BiFunction<T, Sphere, Boolean> leafObjectValidator;

    public BVHQueryRadius(
            final List<T> elements,
            final Function<T, Vector3> leafObjectPositionMapper,
            final Function<T, AxisAlignedBoundingBox> leafObjectAabbMapper,
            final BiFunction<T, Sphere, Boolean> leafObjectValidator) {
        this.leafObjectValidator = leafObjectValidator;
        final Map<Long, T> mortonEncodedElements = MortonMapper.mapElements(
                elements,
                leafObjectPositionMapper,
                v -> new Vector3Int(
                        (int)((v.x + 10000) * 100),
                        (int)((v.y + 10000) * 100),
                        (int)((v.z + 10000) * 100)));
        final List<Long> sortedMortonCodes = new ArrayList<>(mortonEncodedElements.keySet())
                .stream()
                .sorted(Comparator.comparingLong((key -> key)))
                .collect(Collectors.toList());

        if(sortedMortonCodes.size() > 1) {
            this.root = Optional.of(new AABBBNQueryRadius<>(sortedMortonCodes, mortonEncodedElements, leafObjectPositionMapper, leafObjectAabbMapper));
        }
        else if(sortedMortonCodes.size() == 1) {
            this.root = Optional.of(new AABBLNQueryRadius<>(elements.get(0), leafObjectAabbMapper));
        }
        else {
            this.root = Optional.empty();
        }
    }

    /**
     * Queries a spherical region in the bvh.
     * @param collidingRegion The spherical region to query.
     * @return The list of elements that collide with the region.
     */
    @Override
    public List<T> query(final Sphere collidingRegion) {
        final List<T> collidingElements = new ArrayList<>();
        final AxisAlignedBoundingBox regionAabb = collidingRegion.toAabb();

        root.ifPresent(node -> node.accept(
                regionAabb::collidesWith,
                element -> leafObjectValidator.apply(element, collidingRegion),
                collidingElements::add));

        return collidingElements;
    }
}
