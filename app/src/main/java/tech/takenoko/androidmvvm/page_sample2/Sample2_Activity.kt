package tech.takenoko.androidmvvm.page_sample2

import android.os.Bundle
import tech.takenoko.androidmvvm.common.BaseActivity
import tech.takenoko.androidmvvm.page_sample1.Sample1_Model
import javax.inject.Inject


class Sample2_Activity() : BaseActivity() {

    override val log: String = "Sample2_Activity"

    @Inject lateinit var viewModel: Sample1_Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // val binding: ActivitySampleBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample)
        // binding.viewModel = viewModel
        // bindViewModel(viewModel)
    }

}
