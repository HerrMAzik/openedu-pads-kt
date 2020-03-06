import mooc.EdxIO

fun main() {
    fun merge(a: IntArray, l: Int, m: Int, r: Int): Long {
        val rl = r - l
        val b = IntArray(rl)
        var i = l
        var j = m
        var k = 0
        var c: Long = 0
        while (k < rl) {
            if (j == r || i < m && a[i] <= a[j]) {
                if (l + k > i) c += l + k - i.toLong()
                b[k] = a[i]
                ++i
            } else {
                b[k] = a[j]
                ++j
            }
            ++k
        }
        System.arraycopy(b, 0, a, l, rl)
        return c
    }

    fun mergeSort(a: IntArray, l: Int, r: Int): Long {
        if (r - l < 2) {
            return 0
        }
        val m = (r + l) / 2
        var c = mergeSort(a, l, m)
        c += mergeSort(a, m, r)
        return c + merge(a, l, m, r)
    }

    EdxIO.create().use { io ->
        val n = io.nextInt()
        val a = IntArray(n)
        for (i in 0 until n) {
            a[i] = io.nextInt()
        }
        // merge sort
        val c = mergeSort(a, 0, n)
        io.println(c)
    }
}
