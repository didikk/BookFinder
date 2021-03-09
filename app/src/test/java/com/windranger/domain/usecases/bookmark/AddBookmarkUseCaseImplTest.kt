package com.windranger.domain.usecases.bookmark

import com.windranger.domain.repositories.BookRepo
import com.windranger.fake.fakeBookModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class AddBookmarkUseCaseImplTest {

    @Mock
    private lateinit var repo: BookRepo

    private lateinit var useCase: AddBookmarkUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        useCase = AddBookmarkUseCaseImpl(repo)
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
        Mockito.verify(repo).addBookmark(fakeBookModel)
    }
}