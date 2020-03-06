import mooc.EdxIO

fun main() {
    EdxIO.create()?.use {
        val n = it.nextInt()
        val k = it.nextInt()

        val l = List(k) { i ->
            IntArray((n + k - 1 - i) / k)
        }

        repeat(n) { i ->
            val b = i % k
            val o = i / k
            l[b][o] = it.nextInt()
        }

        fun partition(a: IntArray, l: Int, r: Int, m: Int): Pair<Int, Int> {
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

        fun sort(a: IntArray, l: Int, r: Int) {
            if (r - l < 2) return
            val m = l + (r - l) / 2
            val e = partition(a, l, r, m)

            sort(a, l, e.first)
            sort(a, e.second, r)
        }

        l.forEach { a ->
            sort(a, 0, a.size)
        }

        var last = l[0][0]
        for (i in 1 until n) {
            val b = i % k
            val o = i / k
            if (last > l[b][o]) {
                it.println("NO")
                return
            }
            last = l[b][o]
        }
        it.println("YES")
    }
}
