import mooc.EdxIO

fun main() {
    fun swap(a: IntArray, i1: Int, i2: Int) {
        val t = a[i1]
        a[i1] = a[i2]
        a[i2] = t
    }

    EdxIO.create().use { io ->
        val n = io.nextInt()
        val a = IntArray(n)
        for (i in 0 until n) {
            a[i] = io.nextInt()
        }
        // selection sort
        for (i in 0 until n) {
            var idx = i
            for (j in i + 1 until n) {
                if (a[j] < a[idx]) {
                    idx = j
                }
            }
            if (idx != i) {
                swap(a, idx, i)
                io.println(String.format("Swap elements at indices %d and %d.", i + 1, idx + 1))
            }
        }
        io.println("No more swaps needed.")
        for (i in a) {
            io.print("$i ")
        }
    }
}
