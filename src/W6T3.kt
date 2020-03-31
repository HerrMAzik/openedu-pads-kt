import mooc.EdxIO

fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()
        val a = IntArray(n)
        var max = 0
        repeat(n) { i ->
            io.nextInt()
            val l = io.nextInt()
            val r = io.nextInt()
            val c = a[i] + 1
            if (l != 0) a[l - 1] = c
            if (r != 0) a[r - 1] = c
            max = maxOf(c, max)
        }

        io.println(max)
    }
}
