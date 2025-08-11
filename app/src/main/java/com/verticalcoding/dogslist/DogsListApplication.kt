package com.verticalcoding.dogslist

import android.app.Application
import com.verticalcoding.dogslist.data.AppContainer
import com.verticalcoding.dogslist.data.DefaultAppContainer

class DogsListApplication: Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}