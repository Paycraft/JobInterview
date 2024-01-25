package com.joedae.propertylist

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.joedae.propertylist.presentation.FavoriteTag
import com.joedae.propertylist.presentation.MainActivity
import com.joedae.propertylist.presentation.PropertyListTag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SetFavoriteTest {

    @get:Rule(order = 0)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltTestRule.inject()
    }

    @Test
    fun testIsFavorite() {
        composeTestRule.onNodeWithTag(PropertyListTag)
            .onChildren().filterToOne(hasTestTag(FavoriteTag)).assertExists()
    }
}
