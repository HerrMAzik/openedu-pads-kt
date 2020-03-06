import mooc.EdxIO

object W2T3 {
    @JvmStatic
    fun main(args: Array<String>) {
        EdxIO.create().use { io -> val n = io.nextInt() }
    }
}
