package com.github.johnwl.bvh.math;

import java.io.Serializable;
import java.util.Objects;

public class Vector3Int implements Serializable {
    public final int x;
    public final int y;
    public final int z;

    public Vector3Int(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3Int that = (Vector3Int) o;
        return x == that.x &&
                y == that.y &&
                z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "[ x:" + this.x + ", y:" + this.y + ", z:" + this.z + " ]";
    }
}
