package org.yaseen;

import org.yaseen.consistenthashing.ConsistentHashing;
import org.yaseen.consistenthashing.KeyHashGenerator;
import org.yaseen.consistenthashing.Node;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("Hello world!");
        KeyHashGenerator<String> hashGenerator = new KeyHashGenerator<String>() {
            private final MessageDigest md= MessageDigest.getInstance("MD5");
            @Override
            public long hash(String key) {
                md.reset();
                md.update(key.getBytes());
                byte[] digest = md.digest();
                long hash = ((long) (digest[3] & 0xFF) << 24) |
                            ((long) (digest[2] & 0xFF) << 16) |
                            ((long) (digest[1] & 0xFF) << 8) |
                            ((long) (digest[0] & 0xFF));
                return hash;
            }
        };
        ConsistentHashing<ServerNode> ch = new ConsistentHashing<>(3,hashGenerator);
        ch.addNode(new ServerNode("server1"));
        ch.addNode(new ServerNode("server2"));
        ch.addNode(new ServerNode("server3"));


        System.out.println("key1: is present on server: " + ch.getNode("key1").get().getName());
        System.out.println("key67890: is present on server: " + ch.getNode("key67890").get().getName());

        System.out.println("key2: is present on server: " + ch.getNode("key2").get().getName());
        System.out.println("key67892: is present on server: " + ch.getNode("key67892").get().getName());

        ch.removeNode(new ServerNode("server1"));
        System.out.println("After removing server1");

        System.out.println("key1: is present on server: " + ch.getNode("key1").get().getName());
        System.out.println("key67890: is present on server: " + ch.getNode("key67890").get().getName());
        System.out.println("key2: is present on server: " + ch.getNode("key2").get().getName());
        System.out.println("key67892: is present on server: " + ch.getNode("key67892").get().getName());
    }

    public record ServerNode(String name) implements Node {

        @Override
        public String getName() {
            return this.name;
        }
    }
}
