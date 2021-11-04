package zlc.season.animatorx

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.util.Property
import android.view.View
import android.view.animation.Interpolator
import kotlinx.coroutines.CancellableContinuation
import android.view.ViewGroup.MarginLayoutParams as MLP

internal val LEFT = PropertyImpl("left", View::setLeft, View::getLeft)
internal val RIGHT = PropertyImpl("right", View::setLeft, View::getLeft)
internal val TOP = PropertyImpl("top", View::setLeft, View::getLeft)
internal val BOTTOM = PropertyImpl("bottom", View::setLeft, View::getLeft)

internal val MARGIN_START = MarginPropertyImpl("margin_start", MLP::setMarginStart, MLP::getMarginStart)
internal val MARGIN_END = MarginPropertyImpl("margin_end", MLP::setMarginEnd, MLP::getMarginEnd)
internal val MARGIN_TOP = MarginPropertyImpl("margin_top", MLP::topMargin.setter, MLP::topMargin.getter)
internal val MARGIN_BOTTOM = MarginPropertyImpl("margin_bottom", MLP::bottomMargin.setter, MLP::bottomMargin.getter)


internal class PropertyImpl(
    name: String,
    val setFunc: View.(Int) -> Unit,
    val getFunc: View.() -> Int
) : Property<View, Float>(Float::class.java, name) {

    override fun get(view: View): Float {
        return view.getFunc().toFloat()
    }

    override fun set(view: View, value: Float) {
        view.setFunc(value.toInt())
    }
}

internal class MarginPropertyImpl(
    name: String,
    val setParamFunc: MLP.(Int) -> Unit,
    val getParamFunc: MLP.() -> Int
) : Property<View, Float>(Float::class.java, name) {

    override fun get(view: View): Float {
        return (view.layoutParams as MLP).getParamFunc().toFloat()
    }

    override fun set(view: View, value: Float) {
        view.layoutParams = (view.layoutParams as MLP).apply {
            setParamFunc(value.toInt())
        }
    }
}


internal fun View.animatorOf(
    property: Property<View, Float>,
    duration: Long,
    interpolator: Interpolator,
    con: CancellableContinuation<Unit>,
    vararg float: Float
): ObjectAnimator {
    return ObjectAnimator.ofFloat(this, property, *float).apply {
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