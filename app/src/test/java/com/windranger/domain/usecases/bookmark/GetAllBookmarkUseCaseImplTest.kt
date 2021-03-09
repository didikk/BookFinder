package com.windranger.domain.usecases.bookmark

import com.windranger.domain.repositories.BookRepo
import com.windranger.fake.fakeBookList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetAllBookmarkUseCaseImplTest {
    @Mock
    private lateinit var repo: BookRepo

    private lateinit var useCase: GetAllBookmarkUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        useCase = GetAllBookmarkUseCaseImpl(repo)
    }

    @Test
    fun shouldReturnBookmarks() = runBlockingTest {
        // Arrange
        Mockito.`when`(repo.getBookmarks()).thenReturn(fakeBookList)

        // Act
        val result = useCase.execute("")

        // Assert
        assertNotNull(result)
        assertEquals(fakeBookList, result)
    }
}