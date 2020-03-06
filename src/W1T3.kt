import mooc.EdxIO

fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()
        val a = IntArray(n)
        for (i in 0 until n) {
            a[i] = io.nextInt()
        }
        io.print("1 ")

        fun swap(a: IntArray, i1: Int, i2: Int) {
            val t = a[i1]
            a[i1] = a[i2]
            a[i2] = t
        }

        // insertion sort
        for (i in 1 until n) {
            var j = i
            while (j > 0 && a[j - 1] > a[j]) {
                swap(a, j - 1, j)
                --j
            }
            io.print("${j + 1} ")
        }
        io.println()
        for (i in a) {
            io.print("$i ")
        }
        io.println()
    }
}
