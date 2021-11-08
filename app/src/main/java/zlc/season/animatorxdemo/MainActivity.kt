package zlc.season.animatorxdemo

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.RectF
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import zlc.season.animatorx.*
import zlc.season.animatorxdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var width = 0
        var height = 0

        binding.root.post {
            width = binding.root.width
            height = binding.root.height
        }

        binding.apply {
            btnLeft.click {
                val oldLeft = testView.left.toFloat()
                testView.animLeft(to = 0f)
                delay(200)
                testView.animLeft(to = oldLeft)
            }

            btnRight.click { testView.runAnim(View::animRight, from = testView.right, to = width) }
            btnTop.click { testView.runAnim(View::animTop, from = testView.top, to = 0) }
            btnBottom.click { testView.runAnim(View::animBottom, from = testView.bottom, height) }
            btnAll.click {
                testView.runAnim(
                    View::animRect,
                    from = RectF(testView.left.toFloat(), testView.top.toFloat(), testView.right.toFloat(), testView.bottom.toFloat()),
                    to = RectF(0f, 0f, width.toFloat(), height.toFloat())
                )
            }

            btnMarginLeft.click { testView.runAnim(View::animMarginStart, from = testView.marginStart, to = width - testView.width) }
            btnMarginRight.click { testView.runAnim(View::animMarginEnd, from = testView.marginEnd, to = width - testView.width) }
            btnMarginTop.click { testView.runAnim(View::animMarginTop, from = testView.marginTop, to = height - testView.height) }
            btnMarginBottom.click { testView.runAnim(View::animMarginBottom, from = testView.marginBottom, to = height - testView.height) }

            btnTx.click { testView.runAnim(View::animTranslationX, from = testView.translationX.toInt(), to = width - testView.right) }
            btnTy.click { testView.runAnim(View::animTranslationY, from = testView.translationY.toInt(), to = height - testView.bottom) }
            btnTz.click { testView.runAnim(View::animTranslationZ, from = testView.translationZ.toInt(), to = 100) }
            btnX.click { testView.runAnim(View::animX, from = testView.x.toInt(), to = width - testView.width) }
            btnY.click { testView.runAnim(View::animY, from = testView.y.toInt(), to = height - testView.height) }
            btnXy.click {
                coroutineScope {
                    async { testView.runAnim(View::animX, from = testView.x.toInt(), to = width - testView.width) }
                    async { testView.runAnim(View::animY, from = testView.y.toInt(), to = height - testView.height) }
                }
            }

            btnScaleX.click { testView.runAnim(View::animScaleX, from = testView.scaleX.toInt(), to = 3) }
            btnScaleY.click { testView.runAnim(View::animScaleY, from = testView.scaleY.toInt(), to = 3) }
            btnScale.click { testView.runAnim(View::animScale, from = testView.scaleX.toInt(), to = 3) }

            btnRotateX.click { testView.runAnim(View::animRotationX, from = testView.rotationX.toInt(), to = 360) }
            btnRotateY.click { testView.runAnim(View::animRotationY, from = testView.rotationY.toInt(), to = 360) }
            btnRotate.click { testView.runAnim(View::animRotation, from = testView.rotationX.toInt(), to = 360) }

            btnAlpha.click { testView.runAnim(View::animAlpha, from = testView.alpha.toInt(), to = 0) }
            btnWidth.click { testView.runAnim(View::animWidth, from = testView.width, to = width) }
            btnHeight.click { testView.runAnim(View::animHeight, from = testView.height, to = height) }
        }

    }

    private suspend fun View.runAnim(
        block: suspend View.(Float) -> Unit,
        from: Int,
        to: Int
    ) {
        block(to.toFloat())
        delay(200)
        block(from.toFloat())
    }

    private suspend fun View.runAnim(
        block: suspend View.(RectF) -> Unit,
        from: RectF,
        to: RectF
    ) {
        block(to)
        delay(200)
        block(from)
    }

    private fun View.click(block: suspend () -> Unit) {
        setOnClickListener {
            lifecycleScope.launch {
                block()
            }
        }
    }
}
