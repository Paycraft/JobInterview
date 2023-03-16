package com.joedae.propertylist.di

import com.joedae.propertylist.domain.IGetPropertyUseCase
import com.joedae.propertylist.domain.implementation.GetPropertyUseCase
import com.joedae.propertylist.presentation.PropertyViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent.class)
        abstract class ViewModelModule {
            @Binds
            abstract fun viewModel(): PropertyViewModel
        }


//abstract class AppModule {
////    @Binds
//    abstract fun getPropertyUseCase(getPropertyUseCase: GetPropertyUseCase): IGetPropertyUseCase
//}