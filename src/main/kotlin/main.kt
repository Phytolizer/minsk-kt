fun main() {
    while (true) {
        print("minsk>> ")
        val line = readLine()
        if (line.isNullOrEmpty()) {
            break
        }

        if (line == "1 + 2 * 3") {
            println("7")
        } else {
            println("ERROR: Invalid expression.")
        }
    }
}