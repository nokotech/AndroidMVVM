package tech.takenoko.androidmvvm.presentation_layer.view_controller

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.databinding.Sample1ActivityBinding
import tech.takenoko.androidmvvm.presentation_layer.presenter.Sample1_Presenter
import javax.inject.Inject


/**
 * Created by takenoko on 2018/02/10.
 */
class Sample1_Activity: BaseActivityWithFragment() {

    override val log: String = "Sample1_Activity"

    @Inject lateinit var presenter: Sample1_Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<Sample1ActivityBinding>(this, R.layout.sample1_activity)
        binding.viewModel = presenter.viewModel
        binding.onClick = this
        bindViewModel(presenter.viewModel)
    }

    override fun fragment(): BaseFragment = Sample1_Fragment1()

    override fun onClick(view: View?) {
        super.onClick(view)
        when(view?.id) {
            R.id.activity_button -> presenter.onClickToNavigator()
            R.id.fragment_button -> presenter.onClickToOK()
            R.id.activity_button_preference -> presenter.onClickToPreference()
        }
    }
}
