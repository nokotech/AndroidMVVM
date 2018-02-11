package tech.takenoko.androidmvvm.page_sample0

import android.os.Bundle
import tech.takenoko.androidmvvm.R
import tech.takenoko.androidmvvm.common.BaseActivityWithFragment
import tech.takenoko.androidmvvm.common.BaseFragment
import tech.takenoko.androidmvvm.utility.CLog
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/10.
 */
class RouteActivity : BaseActivityWithFragment() {

    @Inject
    lateinit var viewModel: RouteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
//        CLog.info("RouteActivity", viewModel?.log)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
    }

    override fun fragment(): BaseFragment = RouteFragment()
}
