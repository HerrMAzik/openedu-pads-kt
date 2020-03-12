import mooc.EdxIO

fun main() {
    EdxIO.create()?.use { io ->
        val cmd = mutableListOf<String>()
        while (true) {
            val s = io.next() ?: break
            cmd.add(s)
        }
        var head = 0
        var tail = 0
        val q = IntArray(25_000_000)
        val l = mutableMapOf<String, Int>()
        val regs = IntArray(26)
        val mod = 65536
        val aa = 'a'.toInt()
        var maxLab = 0

        fun get() = q[head.also { if (it + 1 == q.size) head = 0 else head++ }]
        fun put(v: Int) {
            q[tail.also { tail = if (it == q.size - 1) 0 else it + 1 }] = v
        }

        fun lab(v: String, i: Int): Int {
            val a = l[v]
            if (a != null) return a
            var j = maxOf(i, maxLab)
            while (j < cmd.size) {
                if (cmd[j][0] == ':') {
                    val vv = cmd[j].substring(1)
                    l[vv] = j
                    if (vv == v) {
                        maxLab = j
                        return j
                    }
                }
                j++
            }
            error("no label $v")
        }

        var i = 0
        while (i < cmd.size) {
            val c = cmd[i++]
            when (c.first()) {
                '+' -> {
                    val a = get()
                    val b = get()
                    put((a + b) % mod)
                }
                '-' -> {
                    val a = get()
                    val b = get()
                    put((mod + a - b) % mod)
                }
                '*' -> {
                    val a = get().toLong()
                    val b = get()
                    put(((a * b) % mod).toInt())
                }
                '/' -> {
                    val a = get()
                    val b = get()
                    if (b == 0) put(0)
                    else put((a / b) % mod)
                }
                '%' -> {
                    val a = get()
                    val b = get()
                    if (b == 0) put(0)
                    else put(a % b)
                }
                '>' -> {
                    val r = c[1].toInt() - aa
                    regs[r] = get()
                }
                '<' -> {
                    val r = c[1].toInt() - aa
                    put(regs[r])
                }
                'P' -> {
                    val v = if (c.length == 1) get() else regs[c[1].toInt() - aa]
                    io.println(v)
                }
                'C' -> {
                    val v = if (c.length == 1) get() else regs[c[1].toInt() - aa]
                    io.print((v % 256).toChar())
                }
                ':' -> {
                    val v = c.substring(1)
                    l[v] = i
                }
                'J' -> {
                    val v = c.substring(1)
                    i = lab(v, i)
                }
                'Z' -> {
                    val r = regs[c[1].toInt() - aa]
                    if (r == 0) i = lab(c.substring(2), i)
                }
                'E' -> {
                    val r1 = regs[c[1].toInt() - aa]
                    val r2 = regs[c[2].toInt() - aa]
                    if (r1 == r2) i = lab(c.substring(3), i)
                }
                'G' -> {
                    val r1 = regs[c[1].toInt() - aa]
                    val r2 = regs[c[2].toInt() - aa]
                    if (r1 > r2) i = lab(c.substring(3), i)
                }
                'Q' -> {
                    i = cmd.size
                }
                else -> {
                    put(c.toInt() % mod)
                }
            }
        }
    }
}
