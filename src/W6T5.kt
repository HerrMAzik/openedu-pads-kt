import mooc.EdxIO

fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()
        if (n < 2) {
            io.println("YES")
            return
        }
        class Node(var key: Int? = null, var parent: Node? = null, var left: Node? = null, var right: Node? = null)

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

        fun min(node: Node): Node {
            var e = node
            while (e.left != null) e = e.left!!
            return e
        }

        fun next(node: Node): Node? {
            if (node.right != null) return min(node.right!!)
            var c = node
            var p = c.parent
            while (p != null && c == p.right) {
                c = p
                p = c.parent
            }
            return p
        }

        fun check(root: Node): Boolean {
            var node = min(root)
            var next: Node?
            while (true) {
                next = next(node)
                if (next == null) return true
                if (node.key!! >= next.key!!) return false
                node = next
            }
        }

        val res = check(a[0]!!)
        io.println(if (res) "YES" else "NO")
    }
}

