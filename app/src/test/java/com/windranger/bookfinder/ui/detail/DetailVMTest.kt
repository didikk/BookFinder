package com.windranger.bookfinder.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.windranger.domain.usecases.bookmark.AddBookmarkUseCase
import com.windranger.domain.usecases.bookmark.CheckDataInBookmarkUseCase
import com.windranger.domain.usecases.bookmark.RemoveBookmarkUseCase
import com.windranger.fake.fakeBookModel
import com.windranger.helper.observeOnce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DetailVMTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var addBookmarkUseCase: AddBookmarkUseCase

    @Mock
    private lateinit var removeBookmarkUseCase: RemoveBookmarkUseCase

    @Mock
    private lateinit var checkDataInBookmarkUseCase: CheckDataInBookmarkUseCase

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: DetailVM

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        viewModel = DetailVM(addBookmarkUseCase, removeBookmarkUseCase, checkDataInBookmarkUseCase)
        Dispatchers.setMain(testDispatcher)

        viewModel.setData(fakeBookModel)
    }

    @Test
    fun checkBookmarkReturnTrueWhenExist() = runBlockingTest {
        // Arrange
        Mockito.`when`(checkDataInBookmarkUseCase.execute(Mockito.any())).thenReturn(true)

        // Act
        viewModel.checkBookmark()

        // Assert
        viewModel.isBookmarked.observeOnce {
            assertTrue(it)
        }
    }

    @Test
    fun checkBookmarkReturnFalseWhenNotExist() = runBlockingTest {
        // Arrange
        Mockito.`when`(checkDataInBookmarkUseCase.execute(Mockito.any())).thenReturn(false)

        // Act
        viewModel.checkBookmark()

        // Assert
        viewModel.isBookmarked.observeOnce {
            assertFalse(it)
        }
    }

    @Test
    fun addBookmarkShouldModifyLivedata() = runBlockingTest {
        // Arrange
        viewModel.isBookmarked.value = false

        // Act
        viewModel.addOrRemoveBookmark()

        // Assert
        Mockito.verify(addBookmarkUseCase).execute(Mockito.any())
        viewModel.isBookmarked.observeOnce {
            assertTrue(it)
        }
    }

    @Test
    fun removeBookmarkShouldModifyLivedata() = runBlockingTest {
        // Arrange
        viewModel.isBookmarked.value = true

        // Act
        viewModel.addOrRemoveBookmark()

        // Assert
        Mockito.verify(removeBookmarkUseCase).execute(Mockito.any())
        viewModel.isBookmarked.observeOnce {
            assertFalse(it)
        }
    }
}