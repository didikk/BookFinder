package com.windranger.bookfinder.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.windranger.domain.usecases.bookmark.GetAllBookmarkUseCase
import com.windranger.fake.fakeBookList
import com.windranger.helper.observeOnce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class BookmarkVMTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: GetAllBookmarkUseCase

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: BookmarkVM

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        viewModel = BookmarkVM(useCase)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun shouldReturnListWhenBookmarkExist() = runBlockingTest {
        // Arrange
        Mockito.`when`(useCase.execute(Mockito.any())).thenReturn(fakeBookList)

        // Act
        viewModel.getBookmarks()
        viewModel.books.observeOnce {
            assertEquals(fakeBookList, it)
        }
    }
}