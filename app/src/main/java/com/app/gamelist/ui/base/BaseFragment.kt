package com.app.gamelist.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.app.gamelist.R
import com.app.gamelist.utils.DialogUtils
import com.app.gamelist.utils.customscreens.LoadingDialog

abstract class BaseFragment : Fragment(), BaseNavigator {
    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract fun initNavigator()

    protected abstract fun initUI()

    protected abstract fun initListener()

    private val dialog: AlertDialog by lazy {
        LoadingDialog.Builder().setContext(context)
            .setCancelable(false)
            .build()
    }

    private val loadingDialog: AlertDialog by lazy {
        AlertDialog.Builder(context)
            .setCancelable(false)
            .setView(LayoutInflater.from(context).inflate(R.layout.loading_dialog_layout, null))
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigator()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        DialogUtils.showAlertDialog(context!!, model, object : DialogUtils.DialogAlertListener {
            override fun onPositiveClick() {
            }

            override fun onNegativeClick() {
            }

        })
    }
}