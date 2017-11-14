package io.github.aedans.parsek

/**
 * Creates a parser that fails if eiter parser fails.
 *
 * @param parser1 The first parser to test.
 * @param parser2 The second parser to test.
 */
fun <A, B1, B2> andParser(
        parser1: Parser<A, B1>,
        parser2: Parser<A, B2>
): Parser<A, Pair<B1, B2>> = { input ->
    val result1 = parser1(input)
    when (result1) {
        is ParseResult.Failure -> ParseResult.Failure(result1.err)
        is ParseResult.Success -> {
            val result2 = parser2(result1.rest)
            when (result2) {
                is ParseResult.Failure -> ParseResult.Failure(result2.err)
                is ParseResult.Success -> ParseResult.Success(result2.rest, result1.result to result2.result)
            }
        }
    }
}
