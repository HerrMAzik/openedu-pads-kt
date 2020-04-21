import mooc.EdxIO
import kotlin.math.abs


fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()

        class Node(
                val key: String,
                var value: String,
                var prev: Node? = null,
                var next: Node? = null
        )

        val deleted = Node("", "")

        val size = 1_000_000
        val a = Array<Node?>(size) { null }
        var tail: Node? = null

        fun hash(key: String): Int {
            return abs(key.hashCode()) % size
        }

        fun put(key: String, value: String) {
            val hash = hash(key)
            var i = hash
            var j = -1
            while (true) {
                if (a[i] !== null) {
                    val ex = a[i]!!
                    if (ex.key == key) {
                        ex.value = value
                        return
                    }
                    if (j == -1 && ex.key === deleted.key) j = i
                } else {
                    if (j == -1) j = i
                    val node = Node(key, value)
                    if (tail === null) tail = node

                    if (tail !== node) {
                        tail!!.next = node
                        node.prev = tail
                        tail = node
                    }
                    a[j] = node
                    return
                }
                i = (i + 1) % size
            }
        }

        fun get(key: String): Node? {
            val hash = hash(key)
            var i = hash
            while (true) {
                if (a[i] !== null) {
                    val ex = a[i]!!
                    if (ex.key == key) return ex
                } else {
                    return null
                }
                i = (i + 1) % size
            }
        }

        fun prev(key: String): Node? {
            return get(key)?.prev
        }

        fun next(key: String): Node? {
            return get(key)?.next
        }

        fun delete(key: String) {
            var i = hash(key) - 1
            while (true) {
                i = (i + 1) % size
                if (a[i] === null) return
                val ex = a[i]!!
                if (ex.key === deleted.key) continue
                if (ex.key == key) {
                    a[i] = deleted
                    ex.prev?.next = ex.next
                    ex.next?.prev = ex.prev
                    if (ex === tail) tail = ex.prev
                    return
                }
            }
        }

        repeat(n) {
            val op = io.next()
            val key = io.next()
            when (op) {
                "put" -> {
                    put(key, io.next())
                }
                "get" -> {
                    io.println(get(key)?.value ?: "<none>")
                }
                "prev" -> {
                    io.println(prev(key)?.value ?: "<none>")
                }
                "next" -> {
                    io.println(next(key)?.value ?: "<none>")
                }
                "delete" -> {
                    delete(key)
                }
            }
        }
    }
}

