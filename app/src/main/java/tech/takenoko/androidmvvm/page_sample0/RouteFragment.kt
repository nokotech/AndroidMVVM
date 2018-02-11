package tech.takenoko.androidmvvm.page_sample0

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.BaseFragment
import tech.takenoko.androidmvvm.databinding.FragmentRouteBinding

/**
 * Created by takenoko on 2018/02/11.
 */
class RouteFragment: BaseFragment() {

    override val log: String = "RouteFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRouteBinding.inflate(inflater, container,false)
        binding.viewModel = (activity as RouteActivity).viewModel
        return binding.root
    }

    override fun layoutId() = R.layout.fragment_route
}
