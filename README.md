# AnimatorX

[![](https://jitpack.io/v/ssseasonnn/AnimatorX.svg)](https://jitpack.io/#ssseasonnn/AnimatorX)

动画 + 协程 = Power！

## 准备

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
	implementation 'com.github.ssseasonnn:AnimatorX:1.0.1'
}
```

## 使用方法

## 执行单个动画

- 位移动画

```kotlin
launch {
    //沿着X轴正向移动300像素
    button.translationX(0f, 300f)
}

launch {
    //沿着Y轴正向移动300像素
    button.translationY(0f, 300f)
}

launch {
    //沿着Z轴正向移动300像素
    button.translationZ(0f, 300f)
}
```

- 缩放动画

```kotlin
launch {
    //沿着X轴缩放，从原始大小放大到3倍大小
    button.scaleX(1f, 3f)
}

launch {
    //沿着Y轴缩放，从原始大小放大到3倍大小
    button.scaleY(1f, 3f)
}
```

- 旋转动画

```kotlin
launch {
    //沿着button中心旋转180度
    button.rotation(0f, 180f)
}

launch {
    //沿着X轴旋转
    button.rotationX(0f, 180f)
}

launch {
    //沿着Y轴旋转
    button.rotationY(0f, 180f)
}
```

- 渐变动画

```kotlin
launch {
    //渐变透明
    button.alpha(1f, 0f)
}
```

## 组合多个动画

- 顺序执行，只需要把需要执行的动画按照从上到下的顺序排列即可

```kotlin
launch {
    button1.translationX(0f, 300f)
    button2.alpha(1.0f, 0.1f)
    button3.rotation(0f, 180f)
    button4.scaleX(1.0f, 3f)

    //依次执行button1的位移动画，button2的渐变动画，button3的旋转动画，button4的缩放动画
}
```

- 并列执行，只需要把需要同时运行的动画放进async块中即可

```kotlin
launch {
    val anim1 = async { button1.translationX(0f, 300f)  }
    val anim2 = async { button2.alpha(1.0f, 0.1f) }
    val anim3 = async { button3.rotation(0f, 180f) }
    val anim4 = async { button4.scaleX(1.0f, 3f) }
    awaitAll(anim1, anim2, anim3, anim4)

    //这次四个动画将会一起执行
}
```

## 最后

除此之外，你还能享受到所有协程的其他特性，例如使用 **delay()** 对动画进行延时，
使用 **repeat()** 对动画进行重复播放等等，发挥你的想象力把！

## License

> ```
> Copyright 2019 Season.Zlc
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
