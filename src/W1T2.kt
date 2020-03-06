import mooc.EdxIO

fun main() {
    EdxIO.create().use { io ->
        val a = io.nextInt()
        val b = io.nextLong()
        io.println(a + b * b)
    }
}
