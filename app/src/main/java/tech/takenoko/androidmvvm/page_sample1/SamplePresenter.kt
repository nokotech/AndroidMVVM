package tech.takenoko.androidmvvm.page_sample1

import tech.takenoko.androidmvvm.utility.UseCaseHandler


/**
 * Created by takenoko on 2018/02/08.
 */
class SamplePresenter(_useCaseHandler: UseCaseHandler, _view: SampleContract.View) : SampleContract.Presenter {

    private var view : SampleContract.View? = null
    private var useCaseHandler: UseCaseHandler? = null

    init {
        useCaseHandler = _useCaseHandler
        view = _view
        view?.setPresenter(this)
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

