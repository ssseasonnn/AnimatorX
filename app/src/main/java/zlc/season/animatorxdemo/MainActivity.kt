package zlc.season.animatorxdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import zlc.season.animatorx.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            launch {
                animBtn1()
            }
        }

        button2.setOnClickListener {
            launch {
                animBtn2()
            }
        }

        button3.setOnClickListener {
            launch {
                animBtn3()
            }
        }

        button4.setOnClickListener {
            launch {
                animBtn4()
            }
        }

        btn_launch.setOnClickListener {
            launch {
                animBtn1()
                animBtn2()
                animBtn3()
                animBtn4()
            }
        }

        btn_async.setOnClickListener {
            launch {
                val anim1 = async { animBtn1() }
                val anim2 = async { animBtn2() }
                val anim3 = async { animBtn3() }
                val anim4 = async { animBtn4() }
                awaitAll(anim1, anim2, anim3, anim4)
            }
        }
    }

    private suspend fun animBtn4() {
        coroutineScope {
            val scaleX = async { button4.scaleX(1.0f, 3f) }
            val scaleY = async { button4.scaleY(1.0f, 3f) }
            awaitAll(scaleX, scaleY)
        }
    }

    private suspend fun animBtn3() {
        button3.rotation(0f, 180f)
    }

    private suspend fun animBtn2() {
        button2.alpha(1.0f, 0.1f)
    }

    private suspend fun animBtn1() {
        button1.x(0f, 800f)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
