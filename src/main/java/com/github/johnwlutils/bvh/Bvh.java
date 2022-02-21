package com.github.johnwlutils.bvh;

import com.github.johnwlutils.bvh.bounding_volume_hierarchy.query_radius.BVHQueryRadius;
import com.github.johnwlutils.bvh.file.ObjFileReader;
import com.github.johnwlutils.bvh.shape.Triangle3;

import java.io.File;

public class Bvh extends BVHQueryRadius<Triangle3> {
    /**
     * Constructs a bounding volume hierarchy object from an .obj file.
     * @param objFile The .obj file used to construct the bvh.
     */
    public Bvh(final File objFile) {
        super(ObjFileReader.loadMeshFromFile(objFile),
                t -> t.centerPoint,
                Triangle3::toAabb,
                (t, s) -> s.contains(s.center.projectOnto(t)));
    }
}
