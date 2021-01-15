package com.example.youviewexercise

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.youviewexercise.api.RandomUserRepository
import com.example.youviewexercise.models.Location
import com.example.youviewexercise.models.Person
import com.example.youviewexercise.testutils.MainCoroutineRule
import com.example.youviewexercise.testutils.observeOnce
import com.example.youviewexercise.testutils.runBlockingTest
import com.example.youviewexercise.viewmodels.MasterViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MasterViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing, i.e. uses the TestCoroutineDispatcher
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var mockRepo: RandomUserRepository
    private lateinit var viewModel: MasterViewModel
    private val nina = getNina()
    private val james = getJames()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        coEvery { mockRepo.getRandomUsers() } returns listOf(nina, james)
        viewModel = MasterViewModel(mockRepo, mainCoroutineRule.testDispatcher, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun shouldInitiallyShowLoading() = mainCoroutineRule.runBlockingTest {
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        val viewModel =
            MasterViewModel(
                repository = mockRepo,
                mainDispatcher = Dispatchers.Unconfined,
                ioDispatcher = mainCoroutineRule.testDispatcher
            )

        viewModel.getListOfPeople()

        viewModel.viewState.value?.let {
            assertThat(it.loading).isTrue
        }
    }

    @Test
    fun shouldGetRandomUsers() {
        viewModel.getListOfPeople()

        coVerify { mockRepo.getRandomUsers() }

        viewModel.viewState.observeOnce { state ->
            assertThat(state.persons.first()).isEqualTo(nina)
            assertThat(state.persons.findLast { it == james })
        }
    }

    @Test
    fun shouldHideProgressBarAfterReceivingUsers() {
        viewModel.getListOfPeople()

        coVerify { mockRepo.getRandomUsers() }

        viewModel.viewState.observeOnce { state ->
            assertThat(state.loading).isFalse()
        }
    }

    @Test
    fun shouldNotShowErrorAfterReceivingUsers() {
        viewModel.getListOfPeople()

        coVerify { mockRepo.getRandomUsers() }

        viewModel.viewState.observeOnce { state ->
            assertThat(state.loadingError).isFalse()
        }
    }

    @Test
    fun shouldShowErrorWhenCannotGetUsers() {
        coEvery { mockRepo.getRandomUsers() } throws Exception("error")
        viewModel = MasterViewModel(mockRepo, mainCoroutineRule.testDispatcher, mainCoroutineRule.testDispatcher)

        viewModel.getListOfPeople()

        viewModel.viewState.observeOnce {
            assertThat(it.loadingError).isEqualTo(true)
            assertThat(it.loading).isEqualTo(false)
            assertThat(it.persons).isEqualTo(emptyList<Person>())
        }
    }

    private fun getLocation(): Location {
        return Location(
            streetNumber = "26",
            streetName = "25th Avenue",
            city = "New York",
            state = "New York",
            country = "USA",
            postcode = "51654"
        )
    }

    private fun getNina() = Person(
        title = "Ms",
        firstName = "Nina",
        lastName = "Simone",
        email = "n.simone@music.com",
        thumbnailUrl = "ns url",
        largeUrl = "ns large url",
        location = getLocation()
    )

    private fun getJames() = Person(
        title = "Mr",
        firstName = "James",
        lastName = "Brown",
        email = "j.brown@aol.com",
        thumbnailUrl = "jb url",
        largeUrl = "jb url 2",
        location = getLocation().copy(streetNumber = "53")
    )

}