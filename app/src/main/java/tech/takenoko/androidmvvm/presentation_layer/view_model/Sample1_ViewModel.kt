package tech.takenoko.androidmvvm.presentation_layer.view_model

import android.databinding.Bindable
import android.view.View
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.CommonNavigator
import tech.takenoko.androidmvvm.data_layer.database.Sample_Dao
import tech.takenoko.androidmvvm.data_layer.database.SharedPref
import tech.takenoko.androidmvvm.domain_layer.usecase.Sample1_Usecase
import tech.takenoko.androidmvvm.presentation_layer.view_controller.Sample1_Activity
import tech.takenoko.androidmvvm.presentation_layer.view_controller.Sample2_Activity
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/11.
 */
class Sample1_ViewModel @Inject constructor(): BaseViewModel("Sample1_ViewModel") {

    /**
     * binding data.
     */
    @Bindable var title: String = "Title"
}


