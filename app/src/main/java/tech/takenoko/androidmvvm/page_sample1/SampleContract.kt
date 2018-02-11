package tech.takenoko.androidmvvm.page_sample1

/**
 * Created by takenoko on 2018/02/09.
 */
interface SampleContract {

    interface View {
        fun setPresenter(presenter: Presenter)
        fun setText(text: String)
    }

    interface Presenter {
        fun start()
    }
}