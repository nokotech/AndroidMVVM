package tech.takenoko.androidmvvm.presentation_layer.view_model

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/11.
 */
class Sample2_ViewModel @Inject constructor(): BaseViewModel("Sample2_ViewModel") {

    /** binding data. */
    @Bindable var sampleText: ObservableField<String> = ObservableField("")
    @Bindable var latestButtonList: ObservableArrayList<Sample2_CustomAdapter.SampleList> = ObservableArrayList()
}