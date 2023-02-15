package com.me.myquran.data.module

import android.app.Application
import androidx.room.Room
import com.me.myquran.data.local.MyQuranDatabase
import com.me.myquran.data.local.migration.migration_1_2
import com.me.myquran.data.remote.api.EQuranApi
import com.me.myquran.data.repository.MyQuranRepositoryImpl
import com.me.myquran.domain.repository.MyQuranRepository
import com.me.myquran.utils.DefaultDispatcherProvider
import com.me.myquran.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun bindDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        api: EQuranApi,
        db: MyQuranDatabase,
        dispatcherProvider: DispatcherProvider,
    ): MyQuranRepository {
        return MyQuranRepositoryImpl(api, db, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): MyQuranDatabase {
        return Room.databaseBuilder(
            app,
            MyQuranDatabase::class.java,
            MyQuranDatabase.DATABASE_NAME
        ).build()
    }

}