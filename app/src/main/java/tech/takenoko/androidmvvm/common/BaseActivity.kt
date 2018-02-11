package tech.takenoko.androidmvvm.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import tech.takenoko.androidmvvm.AndroidApplication
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.di.ApplicationComponent

/**
 * Created by takenoko on 2018/02/10.
 */
abstract class BaseActivity : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//         applicationComponent.inject(this)
//    }

    val applicationComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).applicationComponent
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}