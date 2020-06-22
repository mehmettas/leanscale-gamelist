package com.app.gamelist.ui.base

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.app.gamelist.R
import com.app.gamelist.utils.DialogUtils
import com.app.gamelist.utils.customscreens.LoadingDialog

abstract class BaseActivity : AppCompatActivity(), BaseNavigator {
    @get:LayoutRes
    protected abstract val layoutId: Int?

    protected abstract fun initNavigator()

    protected abstract fun initUI()

    protected abstract fun initListener()

    private var doNotAnimateExit = false

    private val dialog: AlertDialog by lazy {
        LoadingDialog.Builder().setContext(this)
            .setCancelable(false)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutId?.let { setContentView(it) }
        initNavigator()
        initUI()
        initListener()
    }

    override fun showLoading() {
        dialog.show()
    }

    override fun hideLoading() {
        dialog.dismiss()
    }

    override fun onError(errorMessage: String, errorCode: Int) {
        val model = DialogUtils.DialogModel(
            "",
            errorMessage,
            0,
            "",
            "",
            R.drawable.ic_launcher_background,
            false
        )

        DialogUtils.showAlertDialog(this, model, object : DialogUtils.DialogAlertListener {
            override fun onPositiveClick() {

            }

            override fun onNegativeClick() {

            }

        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outR = Rect()
                v.getGlobalVisibleRect(outR)
                val isKeyboardOpen = !outR.contains(ev.rawX.toInt(), ev.rawY.toInt())
                if (isKeyboardOpen) {
                    print("Entro al IF")
                    v.clearFocus()
                    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }

                v.isCursorVisible = (!isKeyboardOpen)

            }
        }
        return super.dispatchTouchEvent(ev)
    }

    open fun overridePendingTransitionExit() {
        if (doNotAnimateExit) {
            return
        }
        overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_exit)
    }

    open fun overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_exit)
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        overridePendingTransitionEnter()
    }

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }
}