package tech.takenoko.androidmvvm.presentation_layer.view_controller

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.view.View
import android.widget.ListView
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.databinding.Sample2ActivityBinding
import tech.takenoko.androidmvvm.databinding.Sample2ComponentViewRowBinding
import tech.takenoko.androidmvvm.presentation_layer.presenter.Sample1_Presenter
import tech.takenoko.androidmvvm.presentation_layer.presenter.Sample2_Presenter
import tech.takenoko.androidmvvm.presentation_layer.view_model.Sample2_CustomAdapter
import tech.takenoko.androidmvvm.presentation_layer.view_model.Sample2_ViewModel
import javax.inject.Inject


class Sample2_Activity() : BaseActivity() {

    override val log: String = "Sample2_Activity"

    @Inject lateinit var presenter: Sample2_Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<Sample2ActivityBinding>(this, R.layout.sample2_activity)
        binding.viewModel = presenter.viewModel
        binding.onClick = this
        bindViewModel(presenter.viewModel)
    }

    /**
     * ListView と ViewModel のカスタムバインディング
     */
    object Binding {
        @BindingAdapter("list")
        @JvmStatic
        fun setList(listView: ListView, viewModel: ObservableArrayList<Sample2_CustomAdapter.SampleList>) {
            val adapter = Sample2_CustomAdapter<Sample2_CustomAdapter.SampleList, Sample2ComponentViewRowBinding>(
                    listView.context,
                    viewModel,
                    R.layout.sample2_component_view_row,
                    { item, binding -> binding.sampleList = item })
            listView.adapter = adapter
        }
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when(view?.id) {
            R.id.activity_button -> presenter.onClickToCallApi()
        }
    }
}
