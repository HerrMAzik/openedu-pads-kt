import mooc.EdxIO

fun main() {
    fun <T> swap(a: Array<T>, i1: Int, i2: Int) {
        val t = a[i1]
        a[i1] = a[i2]
        a[i2] = t
    }

    EdxIO.create().use { io ->
        val n = io.nextInt()
        val a: Array<Pair<Double, Int>> = Array(n) { i -> Pair(io.nextDoubleFast(), i + 1) }
        for (i in 1 until n) {
            var j = i
            while (j > 0 && a[j - 1].first > a[j].first) {
                swap(a, j - 1, j)
                --j
            }
        }
        io.println(a[0].second.toString() + " " + a[a.size / 2].second + " " + a[a.size - 1].second)
    }
}
