import mooc.EdxIO

fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()
        val m = hashMapOf<Int, Int>()

        class Node(
                var key: Int? = null,
                var h: Int = 0,
                var parent: Node? = null,
                var left: Node? = null,
                var right: Node? = null
        )

        val a = Array<Node?>(n) { null }
        repeat(n) { i ->
            val k = io.nextInt()
            val l = io.nextInt()
            val r = io.nextInt()

            val node = a[i]?.also { it.key = k } ?: Node(k).also { a[i] = it }
            if (l != 0) {
                node.left = Node(parent = node)
                a[l - 1] = node.left
            }
            if (r != 0) {
                node.right = Node(parent = node)
                a[r - 1] = node.right
            }
        }

        fun h(node: Node?): Int {
            if (node == null) return 0
            if (node.h != 0) return node.h
            val lh = h(node.left)
            val rh = h(node.right)
            val h = maxOf(lh, rh)
            node.h = h + 1
            return node.h
        }
        h(a[0])

        for (node in a) {
            io.println(h(node?.right) - h(node?.left))
        }
    }
}

