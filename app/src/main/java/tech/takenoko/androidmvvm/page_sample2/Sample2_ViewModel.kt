package tech.takenoko.androidmvvm.page_sample2

import android.databinding.Bindable
import android.view.View
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.common.BaseViewModel
import tech.takenoko.androidmvvm.utility.Navigator
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/11.
 */
class Sample2_ViewModel @Inject constructor(): BaseViewModel("Sample2_ViewModel") {

    /**
     * DI Usecase.
     */
//    @Inject lateinit var navigator: Navigator
    @Inject lateinit var sample2Usecase: Sample2_Usecase
    @Inject lateinit var navigator: Navigator<Sample2_Activity>

    /**
     * binding data.
     */
    @Bindable
    var text: String = "Text"

    fun onClickButton(view: View) {
        ULog.info(log, "called onClickButton. id = " + view.id)
        text = sample2Usecase.getSampleText()
        // navigator.next<Sample1_Activity>()
        notifyPropertyChanged(BR._all);
    }
}