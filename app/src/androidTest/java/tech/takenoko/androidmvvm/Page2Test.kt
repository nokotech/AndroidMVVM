package tech.takenoko.androidmvvm

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tech.takenoko.androidmvvm.presentation_layer.view_controller.Sample2_Activity

/**
 * Created by takenaka on 2018/05/29.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class Page2Test {

    @Rule
    @JvmField
    var rule: ActivityTestRule<Sample2_Activity> = ActivityTestRule(Sample2_Activity::class.java)

    @Test
    fun ボタンタップ() {
        onView(withId(R.id.activity_button)).perform(click())
    }

    @Test
    fun ボタン以外タップ() {
        onView(withId(R.id.sample2_layout)).perform(click())
    }

    @Test
    fun バックボタン() {
        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mDevice.pressBack()
    }
}