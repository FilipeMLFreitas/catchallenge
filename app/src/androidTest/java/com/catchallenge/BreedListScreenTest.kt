package com.catchallenge

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.catchallenge.ui.screens.breedlist.BreedListScreen
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BreedListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    //TODO: instrumented tests not running on the device. they're simply stuck in a "loading" state

    @Test
    fun testLoading() {
        composeTestRule.setContent {
            BreedListScreen(onBreedClick = mockk(relaxed = true))
        }

    }
}