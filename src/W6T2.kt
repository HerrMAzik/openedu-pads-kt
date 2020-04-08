import mooc.EdxIO

fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()
        val a = io.nextDoubleFast()
        val eps = 1e-7 / n
        var b = a
        var l = 0.0
        var r = a
        while (r - l > eps) {
            val m = (l + r) / 2
            var prev = a
            var cur = m
            var pos = cur >= 0
            for (i in 2 until n) {
                val next = 2 * cur - prev + 2
                if (next < 0) {
                    pos = false
                    break
                }
                prev = cur
                cur = next
            }
            if (pos) {
                r = m
                b = cur
            } else l = m
        }
        io.println(b)
    }
}
