package com.jraska.dagger.codelab.app

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.jraska.dagger.codelab.app.di.AppComponent
import com.jraska.dagger.codelab.app.di.DaggerAppComponent
import com.jraska.dagger.codelab.config.di.ConfigComponent
import com.jraska.dagger.codelab.config.di.ConfigComponentProvider
import com.jraska.dagger.codelab.core.di.HasAppComponent

open class DaggerApp : Application(), HasAppComponent, ConfigComponentProvider {
  val appComponent: AppComponent by lazy {
    DaggerAppComponent.builder()
      .setContext(this)
      .build()
  }

  val configComponent: ConfigComponent by lazy {
    appComponent.configComponent().create()
  }

  override fun appComponent(): Any {
    return appComponent
  }

  override fun configComponent(): ConfigComponent {
    return configComponent
  }

  override fun onCreate() {
    super.onCreate()
  }

  companion object {
    fun of(activity: Activity): DaggerApp {
      return activity.application as DaggerApp
    }

    fun of(fragment: Fragment): DaggerApp {
      return of(fragment.activity!!)
    }
  }
}
