package com.kaspersky.uitest_framework.interceptors.logging

import android.support.test.espresso.web.model.Evaluation
import com.kaspersky.uitest_framework.kakao.interceptors.AtomInterceptor
import com.kaspersky.uitest_framework.logger.UiTestLogger

class LoggingAtomInterceptor(
        private val uiTestLogger: UiTestLogger
): AtomInterceptor {

    override fun intercept(evaluation: Evaluation?) {
        uiTestLogger.i("Web action: ${evaluation?.message ?: "null"}")
    }
}