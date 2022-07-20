package by.romanovich.mydictinary

import by.romanovich.mydictinary.domain.utils.isCorrectSearchingWord
import by.romanovich.mydictinary.domain.utils.isNotEmptySearchingWord
import by.romanovich.mydictinary.domain.utils.nullSearchingWordForTest
import org.junit.Assert.*
import org.junit.Test


class SearchingWordValidatorTest {
    @Test
    fun searchingWordValidator_InvalidSearchingWord_ReturnsFalse() {
        assertFalse(isCorrectSearchingWord("2"))
    }

    @Test
    fun searchingWordValidator_InvalidSearchingWordSpecialSymbol_ReturnsFalse() {
        assertFalse(isCorrectSearchingWord("pic4"))
    }

    @Test
    fun searchingWordValidator_CorrectSearchingWord_ReturnsTrue() {
        assertTrue(isCorrectSearchingWord("pice"))
    }

    @Test
    fun searchingWordValidator_NotEmptySearchingWord_ReturnsTrue() {
        assertTrue(isNotEmptySearchingWord("pice"))
    }

    @Test
    fun searchingWordValidator_EmptySearchingWord_ReturnsFalse() {
        assertFalse(isNotEmptySearchingWord(""))
    }

    @Test
    fun searchingWordValidator_NotNullSearchingWord_ReturnsNotNull() {
        assertNotNull(nullSearchingWordForTest("pice"))
    }

    @Test
    fun searchingWordValidator_NullSearchingWord_ReturnsNull() {
        assertNull(nullSearchingWordForTest(""))
    }

}