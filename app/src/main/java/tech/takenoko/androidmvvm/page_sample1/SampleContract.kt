package tech.takenoko.androidmvvm.page_sample1

import tech.takenoko.androidmvvm.common.BasePresenter
import tech.takenoko.androidmvvm.common.BaseView

/**
 * Created by takenoko on 2018/02/09.
 */
interface SampleContract {

    interface View : BaseView<Presenter> {
        fun setText(text: String)
    }

    interface Presenter : BasePresenter {
    }
}