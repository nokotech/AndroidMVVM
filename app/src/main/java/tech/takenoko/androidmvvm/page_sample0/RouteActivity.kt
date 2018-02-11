package tech.takenoko.androidmvvm.page_sample0

import android.databinding.DataBindingUtil
import android.os.Bundle
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.BaseActivityWithFragment
import tech.takenoko.androidmvvm.common.BaseFragment
import tech.takenoko.androidmvvm.databinding.ActivityLayoutBinding
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/10.
 */
class RouteActivity(): BaseActivityWithFragment() {

    override val log: String = "RouteActivity"

    @Inject lateinit var viewModel: RouteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLayoutBinding>(this, R.layout.activity_layout)
        binding.viewModel = viewModel
        bindViewModel(viewModel)
    }

    override fun fragment(): BaseFragment = RouteFragment()
}
