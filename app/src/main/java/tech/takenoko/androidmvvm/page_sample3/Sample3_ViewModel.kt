package tech.takenoko.androidmvvm.page_sample3

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.view.View
import tech.takenoko.androidmvvm.common.BaseViewModel
import tech.takenoko.androidmvvm.common.CommonNavigator
import tech.takenoko.androidmvvm.page_sample2.Sample2_Activity
import tech.takenoko.androidmvvm.page_sample2.Sample2_CustomAdapter
import tech.takenoko.androidmvvm.page_sample2.Sample2_Usecase
import tech.takenoko.androidmvvm.utility.ULog
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/11.
 */
class Sample3_ViewModel @Inject constructor(): BaseViewModel("Sample3_ViewModel") {

    /** DI Usecase. */
    @Inject lateinit var navigator: CommonNavigator<Sample3_Activity>
}