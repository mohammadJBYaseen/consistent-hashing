# consistent-hashing

Consistent hashing is a technique used in distributed systems to uniformly distribute data across nodes while minimizing data movement when nodes are added or removed. It is particularly useful in scenarios like load balancing, distributed caching, and sharding.

## Features

- **Uniform Distribution**: Ensures data is evenly distributed across available nodes.
- **Scalability**: Minimizes data redistribution when adding or removing nodes.
- **Fault Tolerance**: Easily handles node failures by redistributing only the affected keys.
- **Flexibility**: Suitable for various applications like caching, load balancing, and distributed systems.

## How It Works

1. **Hashing Keys**: Each data item (key) is hashed to a position on a virtual ring (usually represented as a circular space).
2. **Hashing Nodes**: Each node is also hashed to a position on the same virtual ring.
3. **Data Assignment**: A key is assigned to the first node that appears in a clockwise direction on the ring.
4. **Adding/Removing Nodes**: When nodes are added or removed, only the keys on the affected segments of the ring are redistributed, minimizing disruption.

## Benefits

- **Reduced Data Movement**: Only a fraction of keys are moved when nodes change.
- **Efficient Scaling**: Adding more nodes does not require rehashing all data.
- **Improved Performance**: Distributes load effectively across nodes.

## Installation

To use this implementation, clone the repository:

```bash
# Clone the repository
git clone https://github.com/mohammadJBYaseen/consistent-hashing.git

# Navigate to the project directory
cd consistent-hashing
```

## Usage

### Example (Java)

```java
import consistenthashing.ConsistentHashing;

public class Main {
    public static void main(String[] args) {
        // Create a consistent hashing instance
        ConsistentHashing ch = new ConsistentHashing();

        // Add nodes
        ch.addNode("Node1");
        ch.addNode("Node2");
        ch.addNode("Node3");

        // Assign keys to nodes
        String node = ch.getNodeForKey("my-data-key");
        System.out.println("Key is assigned to " + node);

        // Remove a node
        ch.removeNode("Node2");
    }
}
```

## Testing

Run the tests to ensure everything works as expected:

```bash
# Navigate to the test directory
cd src/test

# Compile and run the tests
javac -d ../bin *.java
java -cp ../bin org.junit.runner.JUnitCore TestConsistentHashing
```

## Applications

- **Distributed Caching**: Systems like Memcached and Redis clusters.
- **Load Balancing**: Distributing traffic across web servers.
- **Sharding**: Partitioning databases or file systems.

## Contributing

We welcome contributions! If you'd like to contribute, please:

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Submit a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## References

- [Consistent Hashing: Wikipedia](https://en.wikipedia.org/wiki/Consistent_hashing)
- [Scalable Distributed Data Structures: Dynamo](https://www.allthingsdistributed.com/files/amazon-dynamo-sosp2007.pdf)

---

Feel free to reach out with any questions or feedback!
