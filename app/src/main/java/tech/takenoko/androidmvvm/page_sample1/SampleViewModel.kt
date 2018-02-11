package tech.takenoko.androidmvvm.page_sample1

import android.databinding.ObservableField

class SampleViewModel {

    var sampleText : ObservableField<String> = ObservableField()

    init {
        sampleText.set("Hello World !!!")
    }

    fun setSampleText(value: String) {
        sampleText.set(value)
    }
}