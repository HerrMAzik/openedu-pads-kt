import mooc.EdxIO

fun main() {
    EdxIO.create().use { io ->
        val n = io.nextInt()
        val ll = IntArray(n)
        val rr = IntArray(n)
        val kk = hashMapOf<Int, Int>()
        val ikk = hashMapOf<Int, Int>()
        repeat(n) { i ->
            val k = io.nextInt()
            val l = io.nextInt() - 1
            val r = io.nextInt() - 1
            kk[k] = i
            ikk[i] = k
            ll[i] = l
            rr[i] = r
        }

        fun remove(v: Int) {
            val i = kk.remove(v) ?: return
            val li = ll[i]
            if (li != -1) {
                val lv = ikk[li]
                remove(lv!!)
            }
            val ri = rr[i]
            if (ri != -1) {
                val lr = ikk[ri]!!
                remove(lr)
            }
        }

        val m = io.nextInt()
        repeat(m) {
            val v = io.nextInt()
            remove(v)
            io.println(kk.size)
        }
    }
}
