package com.windranger.domain.usecases.bookmark

import com.windranger.domain.repositories.BookRepo
import com.windranger.fake.fakeBookModel
import com.windranger.helper.MockitoHelper.anyObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RemoveBookmarkUseCaseImplTest {
    @Mock
    private lateinit var repo: BookRepo

    private lateinit var useCase: RemoveBookmarkUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        useCase = RemoveBookmarkUseCaseImpl(repo)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowErrorWhenParamIsNull() = runBlockingTest {
        // Act
        useCase.execute(null)
    }

    @Test
    fun shouldCallAddBookmark() = runBlockingTest {
        // Act
        useCase.execute(fakeBookModel)

        // Assert
        Mockito.verify(repo).removeBookmark(anyObject(), anyObject())
    }
}