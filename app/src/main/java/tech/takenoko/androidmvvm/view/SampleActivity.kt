package tech.takenoko.androidmvvm.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.databinding.ActivitySampleBinding
import tech.takenoko.androidmvvm.viewmodel.SampleViewModel

class SampleActivity : AppCompatActivity() {

     val viewModel = SampleViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivitySampleBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample)
        binding.activity = this
    }

    fun touch() {
        viewModel.setSampleText("aaaaaa")
    }
}
