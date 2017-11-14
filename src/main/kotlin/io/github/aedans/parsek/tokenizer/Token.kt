package io.github.aedans.parsek.tokenizer

/**
 * Class representing a single token.
 *
 * @param text The text of the token.
 * @param type The type of the token.
 */
data class Token<out T>(
        val text: String,
        val type: T
)