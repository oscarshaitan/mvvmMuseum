package com.allegorit.mvvmpoc.di

import com.allegorit.mvvmpoc.data.api.ApiService
import com.allegorit.mvvmpoc.data.api.RetrofitService
import com.allegorit.mvvmpoc.data.db.DbDataSource
import com.allegorit.mvvmpoc.data.db.MuseumDataBase
import com.allegorit.mvvmpoc.data.db.MuseumDbDataSource
import com.allegorit.mvvmpoc.data.repository.DbRepository
import com.allegorit.mvvmpoc.data.repository.MuseumDbRepository
import com.allegorit.mvvmpoc.data.repository.MuseumRemoteDataSource
import com.allegorit.mvvmpoc.data.repository.MuseumRemoteRepository
import com.allegorit.mvvmpoc.viewmodel.MuseumViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val museumModel: Module = module {
    single { RetrofitService.createRetrofitService(ApiService::class.java) }
    single<MuseumRemoteDataSource> { MuseumRemoteRepository(get()) }
    single { MuseumDataBase.getInstance(get()) }
    single<DbDataSource> { MuseumDbDataSource(get()) }
    single<DbRepository> { MuseumDbRepository(get()) }
    viewModel { MuseumViewModel(get(), get()) }
}
