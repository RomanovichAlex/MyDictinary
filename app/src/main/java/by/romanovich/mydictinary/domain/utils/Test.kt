package by.romanovich.mydictinary.domain.utils

import java.util.regex.Pattern

/**
 * Паттерн для сравнения.
 */
private val SEARCHING_WORD_PATTERN = Pattern.compile(
    "[a-zA-Zа-яА-Я]{1,256}"
)

fun isValidSearchingWord(searchingWord: CharSequence?): Boolean {
    return isCorrectSearchingWord(searchingWord)
            && isNotNullSearchingWord(searchingWord)
            && isNotEmptySearchingWord(searchingWord)
}

fun isCorrectSearchingWord(searchingWord: CharSequence?): Boolean {
    return SEARCHING_WORD_PATTERN.matcher(searchingWord.toString()).matches()
}

fun isNotNullSearchingWord(searchingWord: CharSequence?): Boolean {
    return searchingWord != null
}

fun isNotEmptySearchingWord(searchingWord: CharSequence?): Boolean {
    return searchingWord != ""
}

fun nullSearchingWordForTest(searchingWord: CharSequence?): CharSequence? {
    return if (searchingWord == "") {
        null
    } else {
        searchingWord
    }
}