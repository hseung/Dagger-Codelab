package com.jraska.dagger.codelab.config.di

import com.jraska.dagger.codelab.config.ui.ConfigActivity
import dagger.Subcomponent

const val CONFIG_BYE_BUTTON = "bye_button"

@ConfigScope
@Subcomponent(modules = [ConfigModule::class])
interface ConfigComponent {
  fun inject(configActivity: ConfigActivity)

  @Subcomponent.Factory
  interface Factory {
    fun create(): ConfigComponent
  }
}
