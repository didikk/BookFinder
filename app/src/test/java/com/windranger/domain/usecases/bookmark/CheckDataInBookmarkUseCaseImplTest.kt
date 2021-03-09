package com.windranger.domain.usecases.bookmark

import com.windranger.domain.repositories.BookRepo
import com.windranger.fake.fakeBookModel
import com.windranger.helper.MockitoHelper.anyObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CheckDataInBookmarkUseCaseImplTest {

    @Mock
    private lateinit var repo: BookRepo

    private lateinit var useCase: CheckDataInBookmarkUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        useCase = CheckDataInBookmarkUseCaseImpl(repo)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowErrorWhenParamIsNull() = runBlockingTest {
        // Act
        useCase.execute(null)
    }

    @Test
    fun shouldReturnTrueWhenBookmarkExist() = runBlockingTest {
        // Arrange
        Mockito.`when`(repo.isInBookmark(anyObject(), anyObject())).thenReturn(true)

        // Act
        val result = useCase.execute(fakeBookModel)

        // Assert
        assertTrue(result)
    }

    @Test
    fun shouldReturnFalseWhenBookmarkNotExist() = runBlockingTest {
        // Arrange
        Mockito.`when`(repo.isInBookmark(anyObject(), anyObject())).thenReturn(false)

        // Act
        val result = useCase.execute(fakeBookModel)

        // Assert
        assertFalse(result)
    }
}