import mooc.EdxIO

fun main() {
    EdxIO.create()?.use { io ->
        val m = io.nextInt()
        val s = IntArray(m)
        var si = 0

        repeat(m) {
            val char = io.nextChar()
            when (char) {
                '+' -> {
                    io.skipWhiteSpace()
                    s[si++] = io.nextInt()
                }
                '-' -> {
                    io.println(s[--si])
                }
            }
        }
    }
}
