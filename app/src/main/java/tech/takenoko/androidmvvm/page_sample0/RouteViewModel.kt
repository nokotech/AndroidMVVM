package tech.takenoko.androidmvvm.page_sample0

import android.util.Log
import android.view.View
import tech.takenoko.androidmvvm.common.BaseViewModel
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/11.
 */
@RouteModules.AnnotSample0Scope
class RouteViewModel @Inject constructor() : BaseViewModel() {

    override val log = "RouteViewModel"

    fun onClickButton(view: View) {
        Log.d(this::class.java.name, "onclick")
    }
}


