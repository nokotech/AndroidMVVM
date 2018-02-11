package tech.takenoko.androidmvvm.common

import dagger.android.DaggerActivity

/**
 * Created by takenoko on 2018/02/10.
 */
abstract class BaseActivity : DaggerActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//         applicationComponent.inject(this)
//    }

//    val applicationComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
//        (application as AndroidApplication).applicationComponent
//    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}