# Realize MVVM on Android

[![Build Status](https://travis-ci.org/nokotech/AndroidMVVM.svg?branch=master)](https://travis-ci.org/nokotech/AndroidMVVM)

## Libraries Used
* Android Databinding
* RxJava
* Dagger
* Retrofit
* OkHttp
* Gson

## Source

### View

* Base class

```
abstract class BaseActivity: DaggerActivity() {

    /** ViewModel */
    private lateinit var viewModel: BaseViewModel

    /** bind VM. */
    protected fun bindViewModel(viewModel: BaseViewModel) {
        this.viewModel = viewModel
        this.viewModel.onCreate()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    /** Back Button Action. */
    override fun onBackPressed() {
        super.onBackPressed()
        this.viewModel.onBackPressed()
    }
}
```

* Implementation class

```
class XxxxxActivity() : BaseActivity() {

    @Inject lateinit var viewModel: XxxxxViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<XxxxxActivityBinding>(this, R.layout.xxxxx_activity)
        binding.viewModel = this.viewModel
        bindViewModel(this.viewModel)
    }

}
```


### ViewModel

* Base class

```
abstract class BaseViewModel(): BaseObservable() {

    open fun onCreate() {}
    open fun onStart() {}
    open fun onResume() {}
    open fun onPause() {}
    open fun onStop() {}
    open fun onBackPressed() {}
}
```

* Implementation class

```
class XxxxxViewModel @Inject constructor(): BaseViewModel() {

    /** DI Usecase. */
    @Inject lateinit var usecase: XxxxxUsecase
    @Inject lateinit var navigator: CommonNavigator<XxxxxActivity> // UtilUtility for transitionity

    /** binding data. */
    @Bindable var sampleText: ObservableField<String> = ObservableField("")

    /** button action. */
    fun onClickButton(view: View) {
        usecase.getXxxxx(this)
        // usecase.isYyyyy(this) ? navigator.next<YyyyyActivity>() : null
    }
}
```


### Usecase

android.databinding.ObservableField<T> --> rx.Observable  
In the Usecase, switch to the main thread.

* Base class

```
abstract class BaseUsecase() {

    /* MainThreadHandler */
    private val mHandler = Handler(Looper.getMainLooper())

    /**
     * execute Main Thread.
     * @param callback
     */
    fun onMainThread(callback: DefaultBlock) {
        mHandler.post { callback() }
    }

    /** (Extensions) change value and notify. */
    fun <T> ObservableField<T>.update(value: T, notify: Int) {
        this.set(value)
        notifyPropertyChanged(notify);
    }
}
```

* Implementation class

```
class XxxxxUsecase @Inject constructor(): BaseUsecase() {

    /** DI Repository. */
    @Inject lateinit var repository: XxxxxRepository

    /** get repository data. */
    fun getXxxxx(viewModel: XxxxxViewModel) {

        // define subscriber.
        val subscriber = object: SingleSubscriber<GetXxxxxEntity>() {
            override fun onSuccess(t: GetXxxxxEntity?) { onMainThread {
                viewModel.sampleText.update(t, BR._all)
            }}
            override fun onError(error: Throwable?) { onMainThread {
                viewModel.sampleText.update("Error", BR._all)
            }}
        }

        // get repository.
        repository.getXxxxx().subscribe(subscriber)
    }
}
```


### Repository

* Base class

```
@Singleton
abstract class BaseRepository<K, V> {

    /** repository propaty cache. */
    private var cache: MutableMap<K, V> = HashMap()

    /** get propaty cache. */
    protected fun getCache(): MutableMap<K, V> {
        ULog.debug(log, "getCache called. cache = " + cache.toString())
        return this.cache
    }

    /** clear propaty cache. */
    protected fun clearCache() {
        ULog.debug(log, "clearCache called.")
        cache.clear()
    }

    /** Single creater. */
    protected fun <T> rxSingle(f: SingleSubscriberBlock<T>): Single<T> {
        return Single.create { sub -> f(sub) }
    }
}
```

* Implementation class

```
@Singleton
class XxxxxRepository @Inject constructor() : BaseRepository<String, String>() {

    /** DI Api. */
    @Inject lateinit var xxxxxApi: XxxxxApi

    /** cache key */
    val GET_LATEST__DATE = "GET_XXXXX__DATE"

    /** cache property */
    var cacheGetXxxxx: GetXxxxxEntity? = null

    /**
     * get repository data return Single.
     * @return GetXxxxxEntity of Single emitter.
     */
    fun getXxxxx(): Single<GetXxxxxEntity> {
        return rxSingle { subscriber -> run {

            // get property cache.
            if (getCache()[GET_XXXXX__DATE] != null) {
                subscriber.onSuccess(cacheGetXxxxx)
            }

            // define subscriber.
            val apiSubscriber = RxSingleSubscriber<GetXxxxxEntity>(
            ).setSuccessBlock{ t ->
                // caching
                cacheGetXxxxx = t
                getCache().put(GET_XXXXX__DATE, Util.dateToString(Date()))
                subscriber.onSuccess(t)
            }.setErrorBlock { e ->
                subscriber.onError(e)
            }

            // get an entity from api.
            xxxxxApi.getXxxxx().subscribe(apiSubscriber)
        }}
    }
}
```

### Repository

* entity
```
data class GetXxxxxEntity (
        var a: String?,
        var b: String?,
        var c: String?,
): Serializable
```

* interface class

```
interface XxxxxProtocol {

    @GET("")
    fun getXxxxxProtocol(@Query("q") q: String): Single<GetXxxxxEntity>
}
```

* Implementation class

```
class XxxxxApi @Inject constructor() : Xxxxx_Protocol {

    /**
     * called API
     * @param query
     * @return RxSingle
     */
    @Singleton
    fun getLatest(query: String): Single<GetXxxxxEntity> {
        return ApiBuilder
                .build(@"http://localhost:8080")
                .create(XxxxxProtocol::class.java)
                .getXxxxxProtocol(query)
                .subscribeOn(Schedulers.newThread())
    }
}
```

### Application

```
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
```

### AppComponent

```
@Singleton
@Component(modules = arrayOf(
        XxxxxActivityModule::class,
        AndroidInjectionModule::class
))
interface AppComponent : AndroidInjector<App> {

    @Component.Builder interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }
}

```
