package tech.takenoko.androidmvvm.page_sample1

import android.databinding.DataBindingUtil
import android.os.Bundle
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.BaseActivityWithFragment
import tech.takenoko.androidmvvm.common.BaseFragment
import tech.takenoko.androidmvvm.databinding.Sample1ActivityBinding
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/10.
 */
class Sample1_Activity(): BaseActivityWithFragment() {

    override val log: String = "Sample1_Activity"

    @Inject lateinit var viewModel: Sample1_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<Sample1ActivityBinding>(this, R.layout.sample1_activity)
        binding.viewModel = viewModel
        bindViewModel(viewModel)
    }

    override fun fragment(): BaseFragment = Sample1_Fragment1()
}
