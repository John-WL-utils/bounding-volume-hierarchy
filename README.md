<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

This is a bare bone bounding volume hierarchy implementation, in Java. It lets you construct BVH objects from .obj files.  
I couldn't find anything that resembled what I needed on the internet for Java specifically, so I just built my own representation.  
This implementation is based on the following blog posts:  
* <a href="https://developer.nvidia.com/blog/thinking-parallel-part-i-collision-detection-gpu/">Collision Detection on the GPU</a>  
* <a href="https://developer.nvidia.com/blog/thinking-parallel-part-ii-tree-traversal-gpu/">Tree Traversal on the GPU</a>  
* <a href="https://developer.nvidia.com/blog/thinking-parallel-part-iii-tree-construction-gpu/">Tree Construction on the GPU</a>  

Technicalities:
* Contrary to the cited sources, everything in this implementation runs on the cpu;  
* If you need to construct other types of BVH that don't only check for collisions between a mesh and a sphere, 
you can modify this implementation fairly easily for that matter;  
* This implementation only handles windows file system when reading .obj files.  

<!-- INSTALLATION -->
### Installation

To setup this project with maven localy on your machine, follow these steps:  
1. Clone the repository  
  ```sh
  git clone https://github.com/John-WL-utils/bounding-volume-hierarchy
  ```  
2. Install with maven on your local machine  
  ```sh
  mvn install
  ```
3. Add it as a dependency of another project  
  ```maven
  <!-- https://github.com/John-WL-utils/bounding-volume-hierarchy -->
  <dependency>
    <groupId>com.github.John-WL-utils</groupId>
    <artifactId>bvh</artifactId>
    <version>1.0.0</version>
  </dependency>
  ```

<!-- USAGE EXAMPLES -->
## Usage

To instantiate a Bvh object, you can simply do:  
```Java
// your .obj file
final File objFile = new File("objFile.obj");

// the bvh representation is created here
final Bvh bvh = new Bvh(objFile);
```  
Then, to get all the triangles that collide with a sphere, you can do:  
```Java
// create a sphere for the collision detection
final Vector3 position = new Vector3(1, 2, 3);
final double radii = 3;
final Sphere sphere = new Sphere(position, radii);

// retrieve every triangle that touches or that is contained inside the sphere
final List<Triangle3> collidingTriangles = bvh.query(sphere);
```

<!-- CONTRIBUTING -->
## Contributing

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star if you think it's worth it! Thanks!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


<!-- CONTACT -->
## Contact

Project Link: [https://github.com/John-WL-utils/bounding-volume-hierarchy](https://github.com/John-WL-utils/bounding-volume-hierarchy)
