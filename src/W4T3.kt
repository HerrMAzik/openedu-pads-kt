import mooc.EdxIO

fun main() {
    EdxIO.create()?.use { io ->
        val n = io.nextInt()

        val s = CharArray(10001)
        var si = 0
        repeat(n) {
            si = 0
            var g = true
            for (c in io.next()) {
                if (c == '(' || c == '[') {
                    s[si++] = c
                } else if (c == ')') {
                    if (si == 0 || s[si - 1] != '(') {
                        g = false
                        break
                    }
                    si--
                } else if (c == ']') {
                    if (si == 0 || s[si - 1] != '[') {
                        g = false
                        break
                    }
                    si--
                }
            }
            io.println(if (g && si == 0) "YES" else "NO")
        }
    }
}
