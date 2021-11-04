package zlc.season.animatorx

import android.graphics.Rect
import android.os.Build
import android.util.Property
import android.view.View
import android.view.View.*
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.suspendCancellableCoroutine

const val DEFAULT_DURATION = 300L
val DEFAULT_INTERPOLATOR = LinearInterpolator()

/**
 * Margin start animation
 */
suspend fun View.marginStart(
    to: Float,
    from: Float = MARGIN_START.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(MARGIN_START, duration, interpolator, from, to)

/**
 * Margin end animation
 */
suspend fun View.marginEnd(
    to: Float,
    from: Float = MARGIN_END.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(MARGIN_END, duration, interpolator, from, to)

/**
 * Margin top animation
 */
suspend fun View.marginTop(
    to: Float,
    from: Float = MARGIN_TOP.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(MARGIN_TOP, duration, interpolator, from, to)

/**
 * Margin bottom animation
 */
suspend fun View.marginBottom(
    to: Float,
    from: Float = MARGIN_BOTTOM.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(MARGIN_BOTTOM, duration, interpolator, from, to)

/**
 * Left animation
 */
suspend fun View.left(
    to: Float,
    from: Float = LEFT.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(LEFT, duration, interpolator, from, to)

/**
 * Top animation
 */
suspend fun View.top(
    to: Float,
    from: Float = TOP.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(TOP, duration, interpolator, from, to)

/**
 * Right animation
 */
suspend fun View.right(
    to: Float,
    from: Float = RIGHT.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(RIGHT, duration, interpolator, from, to)

/**
 * Bottom animation
 */
suspend fun View.bottom(
    to: Float,
    from: Float = BOTTOM.get(this),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(BOTTOM, duration, interpolator, from, to)

/**
 * Rect animation
 */
suspend fun View.rect(
    to: Rect,
    from: Rect = Rect(left, top, right, bottom),
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = coroutineScope {
    val left = async { left(to.left.toFloat(), from.left.toFloat(), duration, interpolator) }
    val top = async { top(to.top.toFloat(), from.top.toFloat(), duration, interpolator) }
    val right = async { right(to.right.toFloat(), from.right.toFloat(), duration, interpolator) }
    val bottom = async { bottom(to.bottom.toFloat(), from.bottom.toFloat(), duration, interpolator) }
    awaitAll(left, top, right, bottom)
}

/**
 * TranslationX animation
 */
suspend fun View.translationX(
    to: Float,
    from: Float = translationX,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(TRANSLATION_X, duration, interpolator, from, to)

/**
 * TranslationY animation
 */
suspend fun View.translationY(
    to: Float,
    from: Float = translationY,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(TRANSLATION_Y, duration, interpolator, from, to)

/**
 * TranslationZ animation
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
suspend fun View.translationZ(
    to: Float,
    from: Float = translationZ,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(TRANSLATION_Z, duration, interpolator, from, to)

/**
 * ScaleX animation
 */
suspend fun View.scaleX(
    to: Float,
    from: Float = scaleX,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(SCALE_X, duration, interpolator, from, to)

/**
 * ScaleY animation
 */
suspend fun View.scaleY(
    to: Float,
    from: Float = scaleY,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(SCALE_Y, duration, interpolator, from, to)

/**
 * Scale animation
 */
suspend fun View.scale(
    to: Float,
    from: Float = scaleX,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = coroutineScope {
    val scaleX = async { scaleX(to, from, duration, interpolator) }
    val scaleY = async { scaleY(to, from, duration, interpolator) }
    awaitAll(scaleX, scaleY)
}

/**
 * Alpha animation
 */
suspend fun View.alpha(
    to: Float,
    from: Float = alpha,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(ALPHA, duration, interpolator, from, to)

/**
 * Rotation animation
 */
suspend fun View.rotation(
    to: Float,
    from: Float = rotation,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(ROTATION, duration, interpolator, from, to)

/**
 * RotationX animation
 */
suspend fun View.rotationX(
    to: Float,
    from: Float = rotationX,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(ROTATION_X, duration, interpolator, from, to)

/**
 * RotationY animation
 */
suspend fun View.rotationY(
    to: Float,
    from: Float = rotationY,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(ROTATION_Y, duration, interpolator, from, to)

/**
 * X animation
 */
suspend fun View.x(
    to: Float,
    from: Float = x,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(X, duration, interpolator, from, to)

/**
 * Y animation
 */
suspend fun View.y(
    to: Float,
    from: Float = y,
    duration: Long = DEFAULT_DURATION,
    interpolator: Interpolator = DEFAULT_INTERPOLATOR
) = animator(Y, duration, interpolator, from, to)

private suspend fun View.animator(
    property: Property<View, Float>,
    duration: Long,
    interpolator: Interpolator,
    from: Float,
    to: Float
) = suspendCancellableCoroutine<Unit> { con ->
    animatorOf(property, duration, interpolator, con, from, to)
}