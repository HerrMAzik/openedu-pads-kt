import mooc.EdxIO


fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()
        var x = io.nextLong()
        var a = io.nextInt()
        var b = io.nextLong()

        val ac = io.nextInt()
        val bc = io.nextLong()
        val ad = io.nextInt()
        val bd = io.nextLong()

        val size = 20_000_000
        val half = size.shr(1)
        val h = LongArray(size) { -1 }
        h.hashCode()
        fun hash(i: Long): Int = half + ((i * 2004017 % 200001953).toInt() % half)

        repeat(n) {
            var i = hash(x) - 1
            loop@ while (true) {
                i = (i + 1) % size
                when (h[i]) {
                    x -> {
                        a = (a + ac) % 1_000
                        b = (b + bc) % 1_000_000_000_000_000
                        break@loop
                    }
                    -1L -> {
                        h[i] = x
                        a = (a + ad) % 1_000
                        b = (b + bd) % 1_000_000_000_000_000
                        break@loop
                    }
                }
            }
            x = (x * a + b) % 1_000_000_000_000_000
        }
        io.println("$x $a $b")
    }
}

