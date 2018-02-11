package tech.takenoko.androidmvvm.page_sample1

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.databinding.ActivitySampleBinding


class SampleActivity: AppCompatActivity() {

    var presenter: SamplePresenter? = null

    var viewModel = SampleViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySampleBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample)
        binding.activity = this
        //presenter = SamplePresenter(null, viewModel)
    }

    fun touch() {
        viewModel.setSampleText("aaaaaa")
    }
}
