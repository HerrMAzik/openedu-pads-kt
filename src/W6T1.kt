import mooc.EdxIO

fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()
        val a = IntArray(n) { io.nextInt() }
        val m = io.nextInt()

        fun search1(v: Int): Int {
            var l = -1
            var r = n
            while (r - l > 1) {
                val mid = (l + r) / 2
                if (a[mid] < v) l = mid else r = mid
            }
            return r
        }

        fun search2(v: Int): Int {
            var l = -1
            var r = n
            while (r - l > 1) {
                val mid = (l + r) / 2
                if (a[mid] <= v) l = mid else r = mid
            }
            return l
        }

        repeat(m) {
            val b = io.nextInt()
            val first = search1(b)
            val last = search2(b)
            io.println(if (first > last) "-1 -1" else "${first + 1} ${last + 1}")
        }
    }
}
