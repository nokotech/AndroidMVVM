package tech.takenoko.androidmvvm.page_sample2

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.view.View
import tech.takenoko.androidmvvm.common.BaseViewModel
import tech.takenoko.androidmvvm.common.CommonNavigator
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/11.
 */
class Sample2_ViewModel @Inject constructor(): BaseViewModel("Sample2_ViewModel") {

    /** DI Usecase. */
    @Inject lateinit var usecase: Sample2_Usecase
    @Inject lateinit var navigator: CommonNavigator<Sample2_Activity>

    /** binding data. */
    @Bindable var sampleText: ObservableField<String> = ObservableField("")
    @Bindable var latestButtonList: ObservableArrayList<Sample2_CustomAdapter.SampleList> = ObservableArrayList()

    /** button action. */
    fun onClickButton(view: View) {
        ULog.info(log, "called onClickButton. id = " + view.id)
        usecase.getSampleText(this)
        // navigator.next<Sample1_Activity>()
    }
}