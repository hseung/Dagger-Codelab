package com.jraska.dagger.codelab.app.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jraska.dagger.codelab.app.DaggerApp
import com.jraska.dagger.codelab.app.PackageName
import com.jraska.dagger.codelab.app.R
import com.jraska.dagger.codelab.config.ui.ConfigActivity
import com.jraska.dagger.codelab.core.analytics.AnalyticsEvent
import com.jraska.dagger.codelab.core.analytics.EventAnalytics

class MainActivity : AppCompatActivity() {

  lateinit var eventAnalytics: EventAnalytics
  lateinit var packageName: PackageName

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(findViewById(R.id.toolbar))

    title = packageName.thisAppPackage()
    findViewById<View>(R.id.fab).setOnClickListener {
      eventAnalytics.reportEvent(AnalyticsEvent.create("main_onFabClick"))
      ConfigActivity.start(this)
    }
  }
}
