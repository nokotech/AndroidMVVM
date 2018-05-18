package tech.takenoko.androidmvvm.presentation_layer.presenter

import android.content.Context
import android.os.Looper
import tech.takenoko.androidmvvm.application.App
import java.util.logging.Handler

/**
 * Created by takenaka on 2018/05/18.
 */
abstract class BasePresenter(val app: App) {

    /** Sample DI. */
    // @inject
    // val xxxxUsecase: XxxxUsecase


    protected lateinit var context: Context
    private lateinit var uiThreadHandler: Handler

    init {
        this.context = app.applicationContext
//        uiThreadHandler = Handler(Looper.getMainLooper())
    }

    abstract fun loadView()
}