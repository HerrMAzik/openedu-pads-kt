import mooc.EdxIO

fun main() {
    EdxIO.create()?.use { io ->
        val n = io.nextInt()
        val a = IntArray(n)
        val b = IntArray(n + 1)
        val c = IntArray(n)
        var ai = 0

        fun swap(i: Int, j: Int) {
            a[i] = a[j].also { a[j] = a[i] }
            b[c[i]] = b[c[j]].also { b[c[j]] = b[c[i]] }
            c[i] = c[j].also { c[j] = c[i] }
        }

        fun siftUp(i: Int) {
            if (i == 0) return

            var k = i
            var p = (k - 1) / 2

            while (k > 0 && a[p] > a[k]) {
                swap(k, p)
                k = p
                p = (k - 1) / 2
            }
        }

        fun replace(i: Int, v: Int) {
            a[b[i]] = v
            siftUp(b[i])
        }

        fun insert(i: Int, v: Int) {
            a[ai] = v
            b[i] = ai
            c[ai] = i
            ai++
            siftUp(ai - 1)
        }

        fun siftDown(i: Int) {
            val k = i
            var m = k
            val l = k * 2 + 1
            val r = k * 2 + 2
            if (l < ai && a[l] < a[m]) m = l
            if (r < ai && a[r] < a[m]) m = r
            if (k != m) {
                swap(k, m)
                siftDown(m)
            }
        }

        fun remove(): String? {
            if (ai == 0) return null
            ai--
            swap(0, ai)
            siftDown(0)
            return a[ai].toString()
        }

        repeat(n) { i ->
            val s = io.next()!!
            when (s.first()) {
                'A' -> {
                    insert(i + 1, io.nextInt())
                }
                'D' -> {
                    replace(io.nextInt(), io.nextInt())
                }
                'X' -> {
                    io.println(remove() ?: "*")
                }
            }
        }
    }
}
