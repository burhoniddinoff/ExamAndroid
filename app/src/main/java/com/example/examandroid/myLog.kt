package com.example.examandroid

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log

fun String.myLog() = Log.d("TTT", this)

fun String.createSpannableText(query : String) : SpannableString {
    val spannableString = SpannableString(this)
    val start = this.lowercase().indexOf(query.lowercase())
    val end = start + query.length
    if(start < 0 || end > this.length) return spannableString
    spannableString.setSpan(
        ForegroundColorSpan(Color.parseColor("#ffffff")),
        start,
        end,
        Spannable.SPAN_EXCLUSIVE_INCLUSIVE
    )
    return spannableString
}