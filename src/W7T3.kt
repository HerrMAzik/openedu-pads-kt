import mooc.EdxIO


fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()
        if (n == 0) {
            io.println("1")
            io.println("${io.nextInt()} 0 0")
            return
        }

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
                    if (balance(node.left) > -1) {
                        node.left = rotateLeft(node.left!!)
                    }
                    rotateRight(node)
                }
                balance(node) > 1 -> {
                    if (balance(node.right) < 1) {
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
            } else {
                node.right = insert(node.right, key)
            }
            return fixHeight(node)
        }

        fun calcHeight(node: Node) {
            if (node.left != null) calcHeight(node.left!!)
            if (node.right != null) calcHeight(node.right!!)
            updateHeight(node)
        }

        val a = Array<Node?>(n + 1) { null }
//        var root = insert(null, io.nextInt())
//        io.nextInt()
//        io.nextInt()
//        repeat(n - 1) {
//            val k = io.nextInt()
//            val l = io.nextInt()
//            val r = io.nextInt()
//            root = insert(root, k)
//        }
        repeat(n) { i ->
            val k = io.nextInt()
            val l = io.nextInt()
            val r = io.nextInt()

            val node = a[i]?.also { it.key = k } ?: Node(k).also { a[i] = it }
            if (l != 0) {
                node.left = Node(-1)
                a[l - 1] = node.left
            }
            if (r != 0) {
                node.right = Node(-1)
                a[r - 1] = node.right
            }
        }

        calcHeight(a[0]!!)

        var root = insert(a[0], io.nextInt())

        io.println(n + 1)
        var ai = 0;
        var bi = 0;
        a[ai++] = root
        while (bi < n + 1) {
            val c = a[bi++]!!
            val li = if (c.left == null) 0 else {
                a[ai] = c.left
                ++ai
            }
            val ri = if (c.right == null) 0 else {
                a[ai] = c.right
                ++ai
            }
            io.println("${c.key} $li $ri")
        }
    }
}

