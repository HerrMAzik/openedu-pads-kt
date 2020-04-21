import mooc.EdxIO
import java.lang.module.FindException
import java.rmi.Remote
import kotlin.math.abs


fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()

        val size = 1_000_000
        val a = LongArray(size) { Long.MIN_VALUE }

        fun hash(i: Long): Int {
            return abs((i * 12_345_681_767 % 12_345_681_919).toInt()) % size
        }

        fun add(v: Long) {
            val hash = hash(v)
            var i = hash
            var j = -1
            while (true) {
                if (a[i] == v) return
                if (a[i] == Long.MAX_VALUE && j == -1) {
                    j = i
                    continue
                }
                if (a[i] == Long.MIN_VALUE) {
                    if (j == -1) j = i
                    a[j] = v
                    return
                }
                i = (i + 1) % size
            }
        }

        fun remove(v: Long) {
            val hash = hash(v)
            var i = hash
            while (true) {
                if (a[i] == v) {
                    a[i] = Long.MAX_VALUE
                    return
                }
                if (a[i] == Long.MIN_VALUE) return
                i = (i + 1) % size
            }
        }

        fun find(v: Long): Boolean {
            val hash = hash(v)
            var i = hash
            while (true) {
                if (a[i] == v) return true
                if (a[i] == Long.MIN_VALUE) return false
                i = (i + 1) % size
            }
        }

        repeat(n) {
            val op = io.nextChar()
            val i = io.nextLong()
            when (op) {
                'A' -> {
                    add(i)
                }
                'D' -> {
                    remove(i)
                }
                '?' -> {
                    io.println(if (find(i)) "Y" else "N")
                }
            }
        }
    }
}

