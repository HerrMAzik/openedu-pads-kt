import mooc.EdxIO

fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()
        if (n < 2) {
            io.println("YES")
            return
        }
        data class Node(val key: Int, var parent: Node? = null, var left: Node? = null, var right: Node? = null)

        val ll = Array<Node?>(n) { null }
        val rr = Array<Node?>(n) { null }
        repeat(n) { i ->
            val k = io.nextInt()
            val l = io.nextInt()
            val r = io.nextInt()

            val node = Node(k)
            if (ll[i] != null) {
                node.parent = ll[i]
                ll[i]!!.left = node
            } else if (rr[i] != null) {
                node.parent = rr[i]
                rr[i]!!.right = node
            }

            if (l != 0) ll[l - 1] = node
            if (r != 0) rr[r - 1] = node
        }

        val root = ll.find { i -> i != null } ?: rr.find { i -> i != null } ?: error("null")

        fun min(node: Node): Node {
            var e = node
            while (e.left != null) e = e.left!!
            return e
        }

        fun next(node: Node): Node? {
            if (node.right != null) return min(node.right!!)
            var c= node
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
                if (node.key >= next.key) return false
                node = next
            }
        }

        val res = check(root)
        io.println(if (res) "YES" else "NO")
    }
}

