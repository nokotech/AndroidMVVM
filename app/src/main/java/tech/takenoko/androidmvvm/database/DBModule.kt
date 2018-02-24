package tech.takenoko.androidmvvm.database

import com.github.gfx.android.orma.AccessThreadConstraint
import dagger.Module
import dagger.Provides
import tech.takenoko.androidmvvm.App
import javax.inject.Singleton

/**
 * Created by takenoko on 2018/02/24.
 */
@Module
class DBModule {

    @Singleton
    @Provides
    fun provideDatabase(application: App): OrmaDatabase {
        return OrmaDatabase.builder(application.applicationContext)
                .readOnMainThread(AccessThreadConstraint.NONE)
                .writeOnMainThread(AccessThreadConstraint.NONE)
                .build()
    }
}