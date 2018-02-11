package tech.takenoko.androidmvvm.page_sample2

import android.databinding.DataBindingUtil
import android.os.Bundle
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.BaseActivity
import tech.takenoko.androidmvvm.databinding.Sample2ActivityBinding
import javax.inject.Inject


class Sample2_Activity() : BaseActivity() {

    override val log: String = "Sample2_Activity"

    @Inject lateinit var viewModel: Sample2_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<Sample2ActivityBinding>(this, R.layout.sample2_activity)
        binding.viewModel = viewModel
        bindViewModel(viewModel)
    }

}
