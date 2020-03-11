import mooc.EdxIO

fun main() {
    EdxIO.create()?.use { io ->
        val m = io.nextInt()
        val s = IntArray(m)
        var head = 0
        var tail = 0

        repeat(m) {
            val char = io.nextChar()
            when (char) {
                '+' -> {
                    io.skipWhiteSpace()
                    s[tail++] = io.nextInt()
                }
                '-' -> {
                    io.println(s[head++])
                }
            }
        }
    }
}
