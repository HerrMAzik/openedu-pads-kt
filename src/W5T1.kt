import mooc.EdxIO

fun main() {
    EdxIO.create()?.use { io ->
        val n = io.nextInt()
        val a = IntArray(n) {
            io.nextInt()
        }

        for (i in 0 until n.shr(1) + 1) {
            val i1 = i.shl(1) + 1
            if (i1 < n && a[i1] < a[i]) {
                io.println("NO")
                return
            }
            val i2 = i.shl(1) + 2
            if (i2 < n && a[i2] < a[i]) {
                io.println("NO")
                return
            }
        }
        io.println("YES")
    }
}
