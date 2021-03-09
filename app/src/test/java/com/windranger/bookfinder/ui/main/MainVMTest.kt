package com.windranger.bookfinder.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.windranger.bookfinder.base.DataState
import com.windranger.domain.usecases.GetBooksUseCase
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
import org.mockito.exceptions.base.MockitoException
import java.io.IOException

@ExperimentalCoroutinesApi
class MainVMTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: GetBooksUseCase

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: MainVM

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        viewModel = MainVM(useCase)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun shouldReturnListWhenNetworkSuccess() = runBlockingTest {
        // Arrange
        Mockito.`when`(useCase.execute(Mockito.anyString())).thenReturn(fakeBookList)

        // Act
        viewModel.getBooks("")
        viewModel.books.observeOnce {
            assertEquals(DataState.Success(fakeBookList), it)
        }
    }

    @Test(expected = MockitoException::class)
    fun shouldReturnErrorWhenNetworkFailed() = runBlockingTest {
        // Arrange
        val errorMsg = "Connection lost, please check your connection."
        Mockito.`when`(useCase.execute(Mockito.anyString()))
            .thenThrow(IOException())

        // Act
        viewModel.getBooks("")
    }
}