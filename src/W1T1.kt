import mooc.EdxIO

fun main() {
    EdxIO.create().use { io -> io.println(io.nextInt() + io.nextInt()) }
}
