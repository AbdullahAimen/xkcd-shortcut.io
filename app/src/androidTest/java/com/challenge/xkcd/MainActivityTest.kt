package com.challenge.xkcd


import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    var mIdlingResource: IdlingResource? = null

    @Before
    fun setUp() {
        mIdlingResource = mActivityTestRule.activity.countingIdlingResource
        Espresso.registerIdlingResources(mIdlingResource)
    }

    @After
    fun tearDown() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource)
        }
    }

    @Test
    fun testNextBtnAppearance() {
        //Before press next there is an item pre-loaded
        Espresso.onView(ViewMatchers.withId(R.id.loadNextImb))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.progressLoading))
            .check(ViewAssertions.matches((not(ViewMatchers.isDisplayed()))))
    }

    @Test
    fun testNextBtnClick() {
        //Before press next there is an item pre-loaded
        Espresso.onView(ViewMatchers.withId(R.id.loadNextImb))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.progressLoading))
            .check(ViewAssertions.matches((not(ViewMatchers.isDisplayed()))))
        Espresso.onView(ViewMatchers.withId(R.id.loadPreviousImb))
            .check(ViewAssertions.matches((not(ViewMatchers.isEnabled()))))
        //load next one and be sure the previous btn is enabled
        Espresso.onView(ViewMatchers.withId(R.id.loadNextImb)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.loadPreviousImb))
            .check(ViewAssertions.matches((ViewMatchers.isEnabled())))
        Espresso.onView(ViewMatchers.withId(R.id.progressLoading))
            .check(ViewAssertions.matches((not(ViewMatchers.isDisplayed()))))
        Espresso.onView(ViewMatchers.withId(R.id.titleTv))
            .check(ViewAssertions.matches((not(ViewMatchers.withText("")))))

    }

    @Test
    fun testLastBtnClick() {
        //Before press next there is an item pre-loaded
        Espresso.onView(ViewMatchers.withId(R.id.loadLastImb))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.progressLoading))
            .check(ViewAssertions.matches((not(ViewMatchers.isDisplayed()))))
        Espresso.onView(ViewMatchers.withId(R.id.loadPreviousImb))
            .check(ViewAssertions.matches((not(ViewMatchers.isEnabled()))))
        //load last one and be sure the next btn is disabled
        Espresso.onView(ViewMatchers.withId(R.id.loadLastImb)).perform(ViewActions.click())
        //assert next btn is disabled
        Espresso.onView(ViewMatchers.withId(R.id.loadNextImb))
            .check(ViewAssertions.matches((not(ViewMatchers.isEnabled()))))
        Espresso.onView(ViewMatchers.withId(R.id.loadLastImb))
            .check(ViewAssertions.matches((not(ViewMatchers.isEnabled()))))

        //assert previous btn is enabled
        Espresso.onView(ViewMatchers.withId(R.id.loadPreviousImb))
            .check(ViewAssertions.matches((ViewMatchers.isEnabled())))
        Espresso.onView(ViewMatchers.withId(R.id.loadFirstImb))
            .check(ViewAssertions.matches((ViewMatchers.isEnabled())))

        //assert title is not empty
        Espresso.onView(ViewMatchers.withId(R.id.titleTv))
            .check(ViewAssertions.matches((not(ViewMatchers.withText("")))))
    }


    @Test
    fun testFirstBtnClick() {
        //Before press next there is an item pre-loaded
        Espresso.onView(ViewMatchers.withId(R.id.loadFirstImb))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.loadFirstImb))
            .check(ViewAssertions.matches((not(ViewMatchers.isEnabled()))))
        //load last one and be sure the next btn is disabled
        Espresso.onView(ViewMatchers.withId(R.id.loadLastImb))
            .check(ViewAssertions.matches((ViewMatchers.isEnabled())))
        Espresso.onView(ViewMatchers.withId(R.id.loadLastImb)).perform(ViewActions.click())
        //assert next btn is disabled
        Espresso.onView(ViewMatchers.withId(R.id.loadNextImb))
            .check(ViewAssertions.matches((not(ViewMatchers.isEnabled()))))
        Espresso.onView(ViewMatchers.withId(R.id.loadLastImb))
            .check(ViewAssertions.matches((not(ViewMatchers.isEnabled()))))

        //assert previous btn is enabled
        Espresso.onView(ViewMatchers.withId(R.id.loadPreviousImb))
            .check(ViewAssertions.matches((ViewMatchers.isEnabled())))
        Espresso.onView(ViewMatchers.withId(R.id.loadFirstImb))
            .check(ViewAssertions.matches((ViewMatchers.isEnabled())))
        Espresso.onView(ViewMatchers.withId(R.id.loadFirstImb)).perform(ViewActions.click())

        //assert previous btn is disabled
        Espresso.onView(ViewMatchers.withId(R.id.loadPreviousImb))
            .check(ViewAssertions.matches((not(ViewMatchers.isEnabled()))))
        Espresso.onView(ViewMatchers.withId(R.id.loadFirstImb))
            .check(ViewAssertions.matches((not(ViewMatchers.isEnabled()))))

        //assert title is not empty
        Espresso.onView(ViewMatchers.withId(R.id.titleTv))
            .check(ViewAssertions.matches((not(ViewMatchers.withText("")))))
    }
}