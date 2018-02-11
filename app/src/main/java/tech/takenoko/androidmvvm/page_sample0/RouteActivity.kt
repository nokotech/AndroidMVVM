package tech.takenoko.androidmvvm.page_sample0

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.BaseActivityWithFragment
import tech.takenoko.androidmvvm.databinding.ActivityLayoutBinding
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/10.
 */
class RouteActivity : BaseActivityWithFragment() {

    @Inject lateinit var viewModel: RouteViewModel
    private lateinit var binding: ActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        binding = DataBindingUtil.setContentView<ActivityLayoutBinding>(this, R.layout.activity_layout)
        binding.viewModel = viewModel
    }

    override fun fragment(): Fragment = RouteFragment()
}
