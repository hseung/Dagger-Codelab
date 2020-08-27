package com.jraska.dagger.codelab.app.di

import com.jraska.dagger.codelab.config.di.ConfigComponent
import dagger.Module

@Module(subcomponents = [ConfigComponent::class])
class SubComponentsModule {
}
