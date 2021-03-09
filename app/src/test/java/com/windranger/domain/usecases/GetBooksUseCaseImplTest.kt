package com.windranger.domain.usecases

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
class GetBooksUseCaseImplTest {

    @Mock
    private lateinit var repo: BookRepo

    private lateinit var useCase: GetBooksUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        useCase = GetBooksUseCaseImpl(repo)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowErrorWhenParamIsNull() = runBlockingTest {
        // Arrange
        Mockito.`when`(repo.getBooks(Mockito.anyString())).thenReturn(fakeBookList)

        // Act
        useCase.execute(null)
    }

    @Test
    fun shouldReturnBook() = runBlockingTest {
        // Arrange
        Mockito.`when`(repo.getBooks(Mockito.anyString())).thenReturn(fakeBookList)

        // Act
        val result = useCase.execute("")

        // Assert
        assertNotNull(result)
        assertEquals(fakeBookList, result)
    }
}