package com.app.gamelist.ui.base

import android.app.AlertDialog
import android.content.Context
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

abstract class BaseActivity : AppCompatActivity(), IBaseNavigator {
    @get:LayoutRes
    protected abstract val layoutId: Int?

    protected abstract fun initNavigator()

    protected abstract fun initUI()

    protected abstract fun initListener()

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
        //invisibleStatusBar()
    }

    private fun invisibleStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }
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
}