import mooc.EdxIO

fun main() {
    EdxIO.create()?.use { io ->
        val n = io.nextInt()
        val a = Array<String>(n) {
            io.next()
        }
        val s = IntArray(n)
        var si = 0

        fun pop(): Int = s[--si]
        fun push(v: Int) {
            s[si++] = v
        }

        for (c in a) {
            when (c.first()) {
                '+' -> {
                    val b = pop()
                    val a = pop()
                    push(a + b)
                }
                '-' -> {
                    val b = pop()
                    val a = pop()
                    push(a - b)
                }
                '*' -> {
                    val b = pop()
                    val a = pop()
                    push(a * b)
                }
                else -> {
                    push(c.toInt())
                }
            }
        }
        io.println(pop())
    }
}
