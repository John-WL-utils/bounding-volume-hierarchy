package com.github.johnwl.bvh;

import com.github.johnwl.bvh.bounding_volume_hierarchy.query_radius.BVHQueryRadius;
import com.github.johnwl.bvh.file.ObjFileReader;
import com.github.johnwl.bvh.shape.Triangle3D;

import java.io.File;

public class Bvh extends BVHQueryRadius<Triangle3D> {
    /**
     * Constructs a bounding volume hierarchy object from an .obj file.
     * @param objFile The .obj file used to construct the bvh.
     */
    public Bvh(final File objFile) {
        super(ObjFileReader.loadMeshFromFile(objFile),
                t -> t.centerPoint,
                Triangle3D::toAabb,
                (t, s) -> s.contains(s.center.projectOnto(t)));
    }
}
