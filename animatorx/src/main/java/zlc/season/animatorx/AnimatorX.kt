package zlc.season.animatorx

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ObjectAnimator.ofFloat
import android.os.Build
import android.util.Property
import android.view.View
import android.view.View.*
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import kotlinx.coroutines.*

const val DEFAULT_DURATION = 300L
val DEFAULT_INTERPOLATOR = LinearInterpolator()

suspend fun View.translationX(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(TRANSLATION_X, duration, interpolator, con, from, to)
}

suspend fun View.translationY(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(TRANSLATION_Y, duration, interpolator, con, from, to)
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
suspend fun View.translationZ(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(TRANSLATION_Z, duration, interpolator, con, from, to)
}

suspend fun View.scale(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = coroutineScope {
    val scaleX = async { scaleX(from, to, duration, interpolator) }
    val scaleY = async { scaleY(from, to, duration, interpolator) }
    awaitAll(scaleX, scaleY)
}

suspend fun View.scaleX(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(SCALE_X, duration, interpolator, con, from, to)
}

suspend fun View.scaleY(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(SCALE_Y, duration, interpolator, con, from, to)
}

suspend fun View.alpha(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(ALPHA, duration, interpolator, con, from, to)
}

suspend fun View.rotation(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(ROTATION, duration, interpolator, con, from, to)
}

suspend fun View.rotationX(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(ROTATION_X, duration, interpolator, con, from, to)
}

suspend fun View.rotationY(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(ROTATION_Y, duration, interpolator, con, from, to)
}

suspend fun View.x(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(X, duration, interpolator, con, from, to)
}

suspend fun View.y(
    from: Float,
    to: Float,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(Y, duration, interpolator, con, from, to)
}

private fun View.animatorOf(
    property: Property<View, Float>,
    duration: Long,
    interpolator: Interpolator,
    con: CancellableContinuation<Unit>,
    vararg float: Float
): ObjectAnimator {
    return ofFloat(this, property, *float).apply {
        this.duration = duration
        this.interpolator = interpolator

        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                con.resumeWith(Result.success(Unit))
            }
        })
        con.invokeOnCancellation {
            if (isRunning) {
                cancel()
            }
        }
        if (con.isActive) {
            start()
        }
    }
}
