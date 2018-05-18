package tech.takenoko.androidmvvm.data_layer.database

import java.io.Serializable

/**
 * Created by takenoko on 2018/02/26.
 */
class SharedPrefDataset {

    /** User's entity. */
    data class User (
            var userId: String?,
            var password: String?
    ): Serializable
}