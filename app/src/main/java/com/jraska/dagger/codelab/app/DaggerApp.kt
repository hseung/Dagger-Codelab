package com.jraska.dagger.codelab.app

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.jraska.dagger.codelab.app.di.AppComponent
import com.jraska.dagger.codelab.core.analytics.di.AnalyticsComponent
import com.jraska.dagger.codelab.core.di.HasAppComponent

open class DaggerApp : Application(), HasAppComponent {
  val appComponent: AppComponent by lazy {
    throw NotImplementedError("Tun the codelab to implement this")
  }

  val analyticsComponent: AnalyticsComponent by lazy { createAnalyticsComponent() }

  override fun appComponent(): Any {
    return appComponent
  }

  override fun onCreate() {
    super.onCreate()
  }

  private fun createAnalyticsComponent(): AnalyticsComponent = throw NotImplementedError("Run the codelab section")

  companion object {
    fun of(activity: Activity): DaggerApp {
      return activity.application as DaggerApp
    }

    fun of(fragment: Fragment): DaggerApp {
      return of(fragment.activity!!)
    }
  }
}
