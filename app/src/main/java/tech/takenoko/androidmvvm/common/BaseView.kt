package tech.takenoko.androidmvvm.common

/**
 * Created by takenoko on 2018/02/09.
 */


interface BaseView<T : BasePresenter> {

    fun setPresenter(presenter: T)

}