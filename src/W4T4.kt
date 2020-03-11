import mooc.EdxIO

fun main() {
    EdxIO.create()?.use { io ->
        val m = io.nextInt()
        val s1 = IntArray(m)
        val ms1 = IntArray(m)
        val s2 = IntArray(m)
        val ms2 = IntArray(m)
        var si1 = 0
        var si2 = 0

        repeat(m) {
            when (io.nextChar()) {
                '+' -> {
                    io.skipWhiteSpace()
                    val v = io.nextInt()
                    val min = if (si1 != 0) minOf(v, ms1[si1 - 1]) else v
                    si1++.also { s1[it] = v }.also { ms1[it] = min }
                }
                '-' -> {
                    if (si2 == 0) {
                        while (si1 != 0) {
                            val v = s1[--si1]
                            val min = if (si2 != 0) minOf(v, ms2[si2 - 1]) else v
                            si2++.also { s2[it] = v }.also { ms2[it] = min }
                        }
                    }
                    si2--
                }
                '?' -> {
                    val min = when {
                        si1 == 0 -> {
                            ms2[si2 - 1]
                        }
                        si2 == 0 -> {
                            ms1[si1 - 1]
                        }
                        else -> {
                            minOf(ms2[si2 - 1], ms1[si1 - 1])
                        }
                    }
                    io.println(min)
                }
            }
        }
    }
}
