import mooc.EdxIO
import java.lang.module.FindException


fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()

        val a = mutableSetOf<Long>()

        repeat(n) {
            val op = io.nextChar()
            val i = io.nextLong()
            when (op) {
                'A' -> {
                    a.add(i)
                }
                'D' -> {
                    a.remove(i)
                }
                '?' -> {
                    io.println(if (a.contains(i)) "Y" else "N")
                }
            }
        }
    }
}

