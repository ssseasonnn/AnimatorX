package zlc.season.animatorx

import android.graphics.RectF
import android.os.Build
import android.util.Property
import android.view.View
import android.view.View.*
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.suspendCancellableCoroutine

const val DEFAULT_DURATION = 300L
val DEFAULT_INTERPOLATOR = LinearInterpolator()

/**
 * Margin start animation
 */
suspend fun View.animMarginStart(
    to: Float,
    from: Float = MARGIN_START.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(MARGIN_START, duration, interpolator, from, to)

/**
 * Margin end animation
 */
suspend fun View.animMarginEnd(
    to: Float,
    from: Float = MARGIN_END.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(MARGIN_END, duration, interpolator, from, to)

/**
 * Margin top animation
 */
suspend fun View.animMarginTop(
    to: Float,
    from: Float = MARGIN_TOP.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(MARGIN_TOP, duration, interpolator, from, to)

/**
 * Margin bottom animation
 */
suspend fun View.animMarginBottom(
    to: Float,
    from: Float = MARGIN_BOTTOM.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(MARGIN_BOTTOM, duration, interpolator, from, to)

/**
 * Left animation
 */
suspend fun View.animLeft(
    to: Float,
    from: Float = LEFT.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(LEFT, duration, interpolator, from, to)

/**
 * Top animation
 */
suspend fun View.animTop(
    to: Float,
    from: Float = TOP.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(TOP, duration, interpolator, from, to)

/**
 * Right animation
 */
suspend fun View.animRight(
    to: Float,
    from: Float = RIGHT.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(RIGHT, duration, interpolator, from, to)

/**
 * Bottom animation
 */
suspend fun View.animBottom(
    to: Float,
    from: Float = BOTTOM.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(BOTTOM, duration, interpolator, from, to)

/**
 * Rect animation
 */
suspend fun View.animRect(
    to: RectF,
    from: RectF = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat()),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = coroutineScope {
    val left = async { animLeft(to.left, from.left, duration, interpolator) }
    val top = async { animTop(to.top, from.top, duration, interpolator) }
    val right = async { animRight(to.right, from.right, duration, interpolator) }
    val bottom = async { animBottom(to.bottom, from.bottom, duration, interpolator) }
    left.await()
    top.await()
    right.await()
    bottom.await()
}

/**
 * TranslationX animation
 */
suspend fun View.animTranslationX(
    to: Float,
    from: Float = translationX,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(TRANSLATION_X, duration, interpolator, from, to)

/**
 * TranslationY animation
 */
suspend fun View.animTranslationY(
    to: Float,
    from: Float = translationY,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(TRANSLATION_Y, duration, interpolator, from, to)

/**
 * TranslationZ animation
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
suspend fun View.animTranslationZ(
    to: Float,
    from: Float = translationZ,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(TRANSLATION_Z, duration, interpolator, from, to)

/**
 * X animation
 */
suspend fun View.animX(
    to: Float,
    from: Float = x,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(X, duration, interpolator, from, to)

/**
 * Y animation
 */
suspend fun View.animY(
    to: Float,
    from: Float = y,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(Y, duration, interpolator, from, to)

/**
 * ScaleX animation
 */
suspend fun View.animScaleX(
    to: Float,
    from: Float = scaleX,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(SCALE_X, duration, interpolator, from, to)

/**
 * ScaleY animation
 */
suspend fun View.animScaleY(
    to: Float,
    from: Float = scaleY,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(SCALE_Y, duration, interpolator, from, to)

/**
 * Scale animation
 */
suspend fun View.animScale(
    to: Float,
    from: Float = scaleX,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = coroutineScope {
    val scaleX = async { animScaleX(to, from, duration, interpolator) }
    val scaleY = async { animScaleY(to, from, duration, interpolator) }
    scaleX.await()
    scaleY.await()
}

/**
 * Alpha animation
 */
suspend fun View.animAlpha(
    to: Float,
    from: Float = alpha,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(ALPHA, duration, interpolator, from, to)

/**
 * Rotation animation
 */
suspend fun View.animRotation(
    to: Float,
    from: Float = rotation,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(ROTATION, duration, interpolator, from, to)

/**
 * RotationX animation
 */
suspend fun View.animRotationX(
    to: Float,
    from: Float = rotationX,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(ROTATION_X, duration, interpolator, from, to)

/**
 * RotationY animation
 */
suspend fun View.animRotationY(
    to: Float,
    from: Float = rotationY,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(ROTATION_Y, duration, interpolator, from, to)


private suspend fun View.animator(
    property: Property<View, Float>,
    duration: Long,
    interpolator: Interpolator,
    from: Float,
    to: Float
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(property, duration, interpolator, con, from, to)
}