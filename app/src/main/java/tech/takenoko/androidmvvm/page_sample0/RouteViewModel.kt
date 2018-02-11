package tech.takenoko.androidmvvm.page_sample0

import android.databinding.Bindable
import android.view.View
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.BaseViewModel
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/11.
 */
class RouteViewModel @Inject constructor(): BaseViewModel("RouteViewModel") {

    /**
     * DI Usecase.
     */
    @Inject lateinit var routeUsecase: RouteUsecase

    /**
     * binding data.
     */
    @Bindable var title: String = "Title"

    fun onClickButton(view: View) {
        ULog.info(log, "called onClickButton. id = " + view.id)
        when (view.id) {
            R.id.activity_button -> title = routeUsecase.cangeTitle1();
            R.id.fragment_button -> title = routeUsecase.cangeTitle2();
        }
        notifyPropertyChanged(BR._all);
    }
}


