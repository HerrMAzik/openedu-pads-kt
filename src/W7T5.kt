import mooc.EdxIO
import java.lang.module.FindException


fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()

        class Node(
                var key: Int,
                var height: Int = 0,
                var left: Node? = null,
                var right: Node? = null
        )

        fun height(n: Node?): Int {
            return n?.height ?: -1
        }

        fun balance(node: Node?): Int {
            return if (node == null) 0 else height(node.right) - height(node.left)
        }

        fun updateHeight(n: Node) {
            n.height = 1 + maxOf(height(n.left), height(n.right))
        }

        fun rotateLeft(node: Node): Node {
            val root = node.right
            node.right = root!!.left
            root.left = node
            updateHeight(node)
            updateHeight(root)
            return root
        }

        fun rotateRight(node: Node): Node {
            val root = node.left
            node.left = root!!.right
            root.right = node
            updateHeight(node)
            updateHeight(root)
            return root
        }

        fun fixHeight(node: Node): Node {
            updateHeight(node)
            return when {
                balance(node) < -1 -> {
                    if (balance(node.left) > 0) {
                        node.left = rotateLeft(node.left!!)
                    }
                    rotateRight(node)
                }
                balance(node) > 1 -> {
                    if (balance(node.right) < 0) {
                        node.right = rotateRight(node.right!!)
                    }
                    rotateLeft(node)
                }
                else -> node
            }
        }

        fun insert(node: Node?, key: Int): Node {
            if (node == null) return Node(key)
            if (node.key > key) {
                node.left = insert(node.left, key)
            } else if (node.key < key) {
                node.right = insert(node.right, key)
            } else {
                return node;
            }
            return fixHeight(node)
        }

        fun mostRight(node: Node): Node {
            if (node.right == null) return node
            return mostRight(node.right!!)
        }

        fun delete(node: Node?, key: Int): Node? {
            if (node == null) return null
            var root = node
            when {
                root.key > key -> {
                    root.left = delete(root.left, key)
                }
                root.key < key -> {
                    root.right = delete(root.right, key)
                }
                else -> {
                    when {
                        root.left == null -> root = root.right
                        root.right == null -> root = root.left
                        else -> {
                            val r = mostRight(root.left!!)
                            root.key = r.key
                            root.left = delete(root.left, r.key)
                        }
                    }
                }
            }
            if (root != null) root = fixHeight(root)
            return root
        }

        fun find(node: Node?, key: Int): Node? {
            if (node == null) return null
            if (node.key > key) return find(node.left, key)
            if (node.key < key) return find(node.right, key)
            return node
        }

        var root: Node? = null
        repeat(n) {
            val op = io.nextChar()
            val k = io.nextInt()
            when (op) {
                'A' -> {
                    root = insert(root, k)
                    io.println(balance(root))
                }
                'D' -> {
                    root = delete(root, k)
                    io.println(balance(root))
                }
                'C' -> {
                    val node = find(root, k)
                    if (node == null) io.println("N") else io.println("Y")
                }
            }
        }
    }
}

