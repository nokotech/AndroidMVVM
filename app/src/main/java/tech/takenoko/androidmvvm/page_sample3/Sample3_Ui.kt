package tech.takenoko.androidmvvm.page_sample3

import android.view.View
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by takenaka on 2018/02/26.
 */
class Sample3_Ui: AnkoComponent<Sample3_Activity> {

    override fun createView(ui: AnkoContext<Sample3_Activity>): View = with(ui){
        relativeLayout {
            val aButton: Button = button {
                text = "OK!"
                onClick { toast("click button.") }
            }

        }
    }

}