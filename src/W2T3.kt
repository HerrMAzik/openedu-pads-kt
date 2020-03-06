import mooc.EdxIO

fun main() {
    EdxIO.create()?.use {
        with(it) {
            val n = nextInt()
            val a = IntArray(n) { i -> i + 1 }

            if (n > 2) {
                for (i in 2 until n) {
                    val m = i shr 1
                    val t = a[m]
                    a[m] = a[i]
                    a[i] = t
                }
            }

            print(a.joinToString(" "))
        }
    }
}
