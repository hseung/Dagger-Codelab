 package com.jraska.dagger.codelab.config

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

 @Suppress("unused") // used in build.gradle
class ConfigTestRunner : AndroidJUnitRunner() {
  override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
    return super.newApplication(cl, HiltTestApplication::class.qualifiedName, context)
  }
}
