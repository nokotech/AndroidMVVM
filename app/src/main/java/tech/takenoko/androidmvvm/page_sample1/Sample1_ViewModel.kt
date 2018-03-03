package tech.takenoko.androidmvvm.page_sample1

import android.databinding.Bindable
import android.view.View
import tech.takenoko.androidmvvm.BR
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.BaseViewModel
import tech.takenoko.androidmvvm.common.CommonNavigator
import tech.takenoko.androidmvvm.database.Sample_Dao
import tech.takenoko.androidmvvm.database.SharedPref
import tech.takenoko.androidmvvm.page_sample2.Sample2_Activity
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/11.
 */
class Sample1_ViewModel @Inject constructor(): BaseViewModel("Sample1_ViewModel") {

    /**
     * DI Usecase.
     */
    @Inject lateinit var sample1Usecase: Sample1_Usecase
    @Inject lateinit var navigator: CommonNavigator<Sample1_Activity>
    @Inject lateinit var preference: SharedPref
    @Inject lateinit var sampleDao: Sample_Dao

    /**
     * binding data.
     */
    @Bindable var title: String = "Title"

    override fun onCreate() {
        title = sample1Usecase.changeTitle2()
    }

    override fun onClickButton(view: View) {
        super.onClickButton(view)
        when (view.id) {
            R.id.activity_button -> navigator.next<Sample2_Activity>()// title = sample1Usecase.cangeTitle1()
            R.id.fragment_button -> title = sample1Usecase.changeTitle2()
            R.id.activity_button_preference -> { preference.allClear(); sampleDao.deleteAll() }
        }
        notifyPropertyChanged(BR._all)
    }
}


