import mooc.EdxIO

fun main() {
    EdxIO.create()?.use { io ->
        val n = io.nextInt()
        val m = io.nextInt()
        val z = n * m
        val a = IntArray(n) { io.nextInt() }
        val b = IntArray(m) { io.nextInt() }

        val cnt = Array(4) {
            IntArray(256)
        }

        var d = IntArray(z)
        var c = IntArray(z) { i ->
            val ai = i / m
            val bi = i % m
            val c = a[ai] * b[bi]
            repeat(4) { k ->
                cnt[k][(c shr (k shl 3)) and 0xFF]++
            }
            c
        }

        repeat(4) { j ->
            repeat(255) { i ->
                cnt[j][i + 1] += cnt[j][i]
            }
        }

        repeat(4) { i ->
            for (idx in c.indices.reversed()) {
                val off = (c[idx] shr (i shl 3)) and 0xFF
                d[--cnt[i][off]] = c[idx]
            }
            c = d.also { d = c }
        }

        var i = 0
        var sum = 0L
        while (i < z) {
            sum += c[i]
            i += 10
        }
        io.println(sum)
    }
}
