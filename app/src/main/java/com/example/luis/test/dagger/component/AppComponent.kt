package com.example.luis.test.dagger.component

import android.app.Application
import com.example.luis.test.App
import com.example.luis.test.dagger.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    AppModule::class,
    PresentationModule::class,
    DomainModule::class,
    CacheModule::class,
    RemoteModule::class
    ])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}