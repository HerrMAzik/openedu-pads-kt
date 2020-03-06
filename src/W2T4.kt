import mooc.EdxIO

fun main() {
    with(W2T4) {
        main()
    }
}

object W2T4 {

    lateinit var a: IntArray

    fun main() {
        EdxIO.create()?.use {
            val n = it.nextInt()
            val k1 = it.nextInt() - 1
            val k2 = it.nextInt() - 1
            val k = Pair(k1, k2)
            val A = it.nextInt()
            val B = it.nextInt()
            val C = it.nextInt()
            a = IntArray(n)
            a[0] = it.nextInt()
            a[1] = it.nextInt()
            for (i in 2 until n) a[i] = A * a[i - 2] + B * a[i - 1] + C

            sort(0, n, k)

            val list = a.asList().subList(k.first, k.second + 1)
            it.println(list.joinToString(" "))
        }
    }

    fun sort(l: Int, r: Int, k: Pair<Int, Int>) {
        if (r - l < 2) return
        val m = l + (r - l) / 2
        val e = partition(l, r, m)

        if (k.first <= e.first) sort(l, e.first, k)
        if (k.second >= e.second) sort(e.second, r, k)
    }

    fun partition(l: Int, r: Int, m: Int): Pair<Int, Int> {
        a[l] = a[m].also { a[m] = a[l] }
        var j = l + 1
        var k = l + 1
        val f = a[l]
        for (i in l + 1 until r) {
            when {
                a[i] < f -> {
                    if (i != k) a[i] = a[k].also { a[k] = a[j] }.also { a[j] = a[i] }
                    else a[i] = a[j].also { a[j] = a[i] }
                    ++k
                    ++j
                }
                a[i] == f -> {
                    a[i] = a[k].also { a[k] = a[i] }
                    ++k
                }
            }
        }
        a[l] = a[j - 1].also { a[j - 1] = a[l] }
        return Pair(j - 1, k)
    }

}
