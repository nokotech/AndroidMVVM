package tech.takenoko.androidmvvm.page_sample0

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.BaseFragment
import tech.takenoko.androidmvvm.databinding.FragmentRouteBinding
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/11.
 */
class RouteFragment : BaseFragment() {

    @Inject lateinit var viewModel: RouteViewModel
    private lateinit var binding: FragmentRouteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRouteBinding.inflate(inflater, container,false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun layoutId() = R.layout.fragment_route
}
