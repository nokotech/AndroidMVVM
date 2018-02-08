package tech.takenoko.androidmvvm.viewmodel

import android.databinding.ObservableField
import android.view.View

class SampleViewModel {

    var sampleText : ObservableField<String> = ObservableField()

    init {
        sampleText.set("Hello World !!!")
    }

    fun setSampleText(value: String) {
        sampleText.set(value)
    }
}