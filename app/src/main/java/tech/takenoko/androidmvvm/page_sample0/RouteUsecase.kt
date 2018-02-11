package tech.takenoko.androidmvvm.page_sample0

import tech.takenoko.androidmvvm.common.BaseUsecase
import javax.inject.Inject

/**
 * Created by takenoko on 2018/02/11.
 */
class RouteUsecase @Inject constructor(): BaseUsecase () {

    fun cangeTitle1(): String {
        return RouteRepository.getSanpleText1();
    }

    fun cangeTitle2(): String {
        return RouteRepository.getSanpleText2();
    }
}