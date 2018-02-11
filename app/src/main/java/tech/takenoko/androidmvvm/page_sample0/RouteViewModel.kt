package tech.takenoko.androidmvvm.page_sample0

import android.databinding.Bindable
import android.view.View
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.BaseViewModel
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by takenoko on 2018/02/11.
 */
@Singleton
class RouteViewModel @Inject constructor() : BaseViewModel() {

    override val log = "RouteViewModel"

    @Bindable var title : String = "Title"

    fun onClickButton(view: View) {
        ULog.info(this::class.java.name, "onclick. id = " + view.id)
        when (view.id) {
            R.id.activity_button -> title = "aaaaa";
            R.id.fragment_button -> title = "bbbbb";
            else -> title = "ccccc";
        }
        notifyPropertyChanged(BR._all);
    }
}


