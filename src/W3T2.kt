import mooc.EdxIO

fun main() {
    EdxIO.create()?.use { io ->
        val n = io.nextInt()
        val m = io.nextInt()
        val k = io.nextInt()

        val aa = 'a'.toInt()
        var pos = IntArray(n) { it }
        var aux = IntArray(n)
        repeat(m - k) {
            io.nextBytes()
        }
        val a = Array(k) {
            io.nextBytes()!!
        }
        val c = IntArray(26)

        for (i in a.size - 1 downTo 0) {
            c.fill(0)
            for (b in a[i]) {
                c[b - aa]++
            }
            repeat(25) { j ->
                c[j + 1] += c[j]
            }

            for (pi in pos.size - 1 downTo 0) {
                val j = a[i][pos[pi]] - aa
                --c[j]
                aux[c[j]] = pos[pi]
            }

            pos = aux.also { aux = pos }
        }

        io.println(pos.map { it + 1 }.joinToString(" "))
    }
}
