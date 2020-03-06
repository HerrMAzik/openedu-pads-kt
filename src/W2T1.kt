import mooc.EdxIO

fun main() {
    fun merge(a: IntArray, l: Int, m: Int, r: Int, io: EdxIO) {
        val rl = r - l
        val b = IntArray(rl)
        var i = l
        var j = m
        var k = 0
        while (k < rl) {
            if (j == r || i < m && a[i] < a[j]) {
                b[k] = a[i]
                ++i
            } else {
                b[k] = a[j]
                ++j
            }
            ++k
        }
        System.arraycopy(b, 0, a, l, rl)
        io.println(String.format("%d %d %d %d", l + 1, r, a[l], a[r - 1]))
    }

    fun mergeSort(a: IntArray, l: Int, r: Int, io: EdxIO) {
        if (r - l < 2) {
            return
        }
        val m = (r + l) / 2
        mergeSort(a, l, m, io)
        mergeSort(a, m, r, io)
        merge(a, l, m, r, io)
    }

    EdxIO.create().use { io ->
        val n = io.nextInt()
        val a = IntArray(n)
        for (i in 0 until n) {
            a[i] = io.nextInt()
        }
        // merge sort
        mergeSort(a, 0, n, io)
        for (i in a) {
            io.print("$i ")
        }
    }
}
