package tech.takenoko.androidmvvm.page_sample3

import android.os.Bundle
import org.jetbrains.anko.setContentView
import tech.takenoko.androidmvvm.common.BaseActivity
import tech.takenoko.androidmvvm.page_sample2.Sample2_ViewModel
import javax.inject.Inject

/**
 * Created by takenaka on 2018/02/26.
 */
class Sample3_Activity : BaseActivity() {

    override val log: String = "Sample3_Activity"

    @Inject lateinit var viewModel: Sample3_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sample3_Ui().setContentView(this)
        bindViewModel(viewModel)
    }
}