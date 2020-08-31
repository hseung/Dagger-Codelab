[![CircleCI](https://circleci.com/gh/jraska/Dagger-Codelab.svg?style=svg)](https://circleci.com/gh/jraska/Dagger-Codelab)  
# Dagger-Codelab - Section 3-1 - Multi-module with SubComponent  
 
앞서 진행한 `03-multiple-module-setup` 예제에서 안내하는 Dagger 사용 방법은 우리가 멀티모듈 프로젝트에서 주로 사용하고 있는 방법이 아닙니다.

따라서 `03-1-multi-module-subcomponent` 브랜치를 통해 멀티모듈에서 SubComponent를 사용하는 방법을 사용해보도록 하겠습니다.

구글 대거 코드랩을 완료했다면, 어렵지 않게 구현할 수 있을 것입니다.
 
## Task 1: Make ConfigComponent as SubComponent
`ConfigComponent`를 SubComponent로 바꾸고, 이 모듈에서 사용될 Scope인 `@ConfigScope`을 생성해 `ConfigComponent`에 달아줍니다.

```kotlin
@ConfigScope
@Subcomponent(modules = [ConfigModule::class])
interface ConfigComponent {
  fun inject(configActivity: ConfigActivity)

  @Subcomponent.Factory
  interface Factory {
    fun create(): ConfigComponent
  }
}
```

```kotlin
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ConfigScope
```

또한 더 이상 필요없는 상속관계인 `interface AppComponent : ConfigComponent`는 제거하고, ConfigComponent를 AppComponent의 Subcomponent로 등록합니다. 
```kotlin
@Component(modules = [
  SubComponentsModule::class, // Please create this class on your own to make ConfigComponent to be a subcomponent of AppComponent
  AnalyticsModule::class
])
interface AppComponent {
  ...
  fun configComponent(): ConfigComponent.Factory
  ...
}
```

이후에는 `ConfigActivity`에서 subcomponent구조에서 inject를 받을 수 있도록 인젝션 방식을 수정합니다. Application 클래스인 `DaggerApp`이 subcomponent를 생성해줄수 있는 새로운 인터페이스를 구현하면 됩니다.

이 상태에서는 ConfigModule에서 제공하는 `MutableConfig`가 @Singleton 스코프를 가질 수 없게됩니다. 그래서 일단 빌드를 성공시키기 위해 @Singleton 스코프를 제거합니다.


여기까지 완료한 이후 앱을 빌드해 테스트해보면 ConfigActivity에서 수정한 컨피그가 MainFragment에서는 반영이 안되는 것을 확인할 수 있습니다. 이는 @Singleton을 제거했기 때문입니다.
이 문제는 다음 스텝에서 해결하도록 합니다.
 
## Task 2: Make RemoteConfig as singleton again
이전 스텝에서 확인한 문제인 `MutableConfig`가 싱글턴으로 존재하지 않는 문제를 해결해봅니다.

`ConfigComponent`에서 @Singleton 객체를 제공하는 것은 빌드 에러가 발생하고 개념적으로도 말이 안되기 때문에, `ConfigComponent`에서가 아닌 `AppComponent` graph에서 Singleton `MutableConfig`, `RemoteConfig`를 제공하게 합니다.

일단 `AppModule`을 생성해 `ConfigModule`의 구현을 옮겨오고, `AppComponent`의 modules에 추가합니다.

```kotlin
@Module
class AppModule {
@Singleton
@Provides
fun remoteConfig(config: InMemoryConfig): MutableConfig = config

@Provides
fun inMemoryConfig(config: MutableConfig): RemoteConfig = config
}
```

반대로 `ConfigModule`의 구현부는 삭제합니다.
 
이제 앱을 빌드해보면, ConfigActivity에서 수정한 컨피그가 MainFragment에도 반영이 됨을 확인할 수 있습니다.

