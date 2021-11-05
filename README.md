# AnimatorX

[![](https://jitpack.io/v/ssseasonnn/AnimatorX.svg)](https://jitpack.io/#ssseasonnn/AnimatorX)

Let Android animation can also use Kotlin Coroutine!

<img src="animator.gif" width="50%" height="50%"/>

## prepare

1. Add jitpack to build.gradle
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2.  Add dependency

```gradle
dependencies {
	implementation 'com.github.ssseasonnn:AnimatorX:1.0.2'
}
```

## Usage

- Basic Usage

```kotlin
launch {
    button.animLeft(to = 0f)
    button.animRight(to = 0f)
    button.animTop(to = 0f)
    button.animBottom(to = 0f)

    button.animScaleX(to = 3f)
    button.animScaleY(to = 3f)
    button.animScale(to = 3f)

    button.animMarginStart(to = 100f)
    button.animMarginEnd(to = 100f)
    button.animMarginTop(to = 100f)
    button.animMarginBottom(to = 100f)
}
```

- Compose multi anim

```kotlin
launch {
    val anim1 = async { button.animLeft(to = 0f)  }
    val anim2 = async { button.animRight(to = 0f) }
    val anim3 = async { button.animTop(to = 0f) }
    val anim4 = async { button.animBottom(to = 0f) }
    awaitAll(anim1, anim2, anim3, anim4)
}
```

## License

> ```
> Copyright 2021 Season.Zlc
>
> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at
>
>    http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
> ```
