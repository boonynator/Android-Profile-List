@file:JvmName("Log")

package android.util

/**
 * Override Log.e() only for testing purposes.
 */
fun e(tag: String, message: String): Int {
    println("ERROR: $tag: $message")
    return 0
}