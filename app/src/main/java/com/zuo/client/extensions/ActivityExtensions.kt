package com.zuo.client.extensions

import android.app.Dialog
import android.support.v7.app.AppCompatActivity

/**
 * 作者：zuo
 * 时间：2018/3/9 14:40
 */
fun AppCompatActivity.showLoading(){
    Dialog(this).show()
}