package tech.takenoko.androidmvvm.page_sample2

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.squareup.spoon.Spoon
import org.junit.Rule
import org.junit.Test
import tech.takenoko.androidmvvm.page_sample1.Sample1_Activity

/**
 * Created by takenoko on 2018/03/11.
 */
class Sample1_ActivityTest {
    private val context = InstrumentationRegistry.getTargetContext()

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(Sample1_Activity::class.java, false, true)

    @Test
    fun mainActivity() {
        Spoon.screenshot(activityRule.activity, "launch")
    }
}