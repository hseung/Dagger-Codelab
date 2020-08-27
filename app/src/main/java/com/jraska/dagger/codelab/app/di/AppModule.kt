package com.jraska.dagger.codelab.app.di

import com.jraska.dagger.codelab.config.InMemoryConfig
import com.jraska.dagger.codelab.config.MutableConfig
import com.jraska.dagger.codelab.core.config.RemoteConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
  @Provides
  @Singleton
  fun remoteConfig(config: InMemoryConfig): MutableConfig = config

  @Provides
  fun inMemoryConfig(config: MutableConfig): RemoteConfig = config
}
