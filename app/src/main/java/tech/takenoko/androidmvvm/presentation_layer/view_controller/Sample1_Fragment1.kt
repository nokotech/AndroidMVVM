package tech.takenoko.androidmvvm.presentation_layer.view_controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.databinding.Sample1Fragment1Binding

/**
 * Created by takenoko on 2018/02/11.
 */
class Sample1_Fragment1 : BaseFragment() {

    override val log: String = "Sample1_Fragment1"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = Sample1Fragment1Binding.inflate(inflater, container,false)
        binding.viewModel = (activity as Sample1_Activity).presenter.viewModel
        binding.onClick = (activity as Sample1_Activity)
        return binding.root
    }

    override fun layoutId() = R.layout.sample1_fragment1
}
