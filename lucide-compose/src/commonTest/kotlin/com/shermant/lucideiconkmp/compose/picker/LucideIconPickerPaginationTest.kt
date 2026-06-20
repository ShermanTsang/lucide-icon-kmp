package com.shermant.lucideiconkmp.compose.picker

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LucideIconPickerPaginationTest {
    @Test
    fun returnsEmptyPageWhenItemsAreEmpty() {
        val page = paginateItems(
            items = emptyList<Int>(),
            pageSize = 12,
            requestedPage = 3,
        )

        assertEquals(emptyList(), page.items)
        assertEquals(0, page.currentPage)
        assertEquals(0, page.pageCount)
        assertEquals(0, page.totalItems)
    }

    @Test
    fun keepsSinglePageWhenResultCountIsLessThanPageSize() {
        val page = paginateItems(
            items = listOf(1, 2, 3),
            pageSize = 12,
            requestedPage = 0,
        )

        assertEquals(listOf(1, 2, 3), page.items)
        assertEquals(0, page.currentPage)
        assertEquals(1, page.pageCount)
        assertEquals(3, page.totalItems)
    }

    @Test
    fun slicesItemsByRequestedPage() {
        val page = paginateItems(
            items = (1..10).toList(),
            pageSize = 4,
            requestedPage = 1,
        )

        assertEquals(listOf(5, 6, 7, 8), page.items)
        assertEquals(1, page.currentPage)
        assertEquals(3, page.pageCount)
        assertEquals(10, page.totalItems)
    }

    @Test
    fun clampsPageToLastPageWhenRequestedPageIsTooLarge() {
        val page = paginateItems(
            items = (1..10).toList(),
            pageSize = 4,
            requestedPage = 9,
        )

        assertEquals(listOf(9, 10), page.items)
        assertEquals(2, page.currentPage)
        assertEquals(3, page.pageCount)
    }

    @Test
    fun clampsPageToFirstPageWhenRequestedPageIsNegative() {
        val page = paginateItems(
            items = (1..10).toList(),
            pageSize = 4,
            requestedPage = -2,
        )

        assertEquals(listOf(1, 2, 3, 4), page.items)
        assertEquals(0, page.currentPage)
        assertEquals(3, page.pageCount)
    }

    @Test
    fun rejectsNonPositivePageSize() {
        assertFailsWith<IllegalArgumentException> {
            paginateItems(
                items = listOf(1, 2, 3),
                pageSize = 0,
                requestedPage = 0,
            )
        }
    }
}
