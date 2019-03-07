package android.support.test.espresso.web.assertion

import android.support.test.espresso.web.model.Atom
import android.webkit.WebView
import com.kaspersky.uitest_framework.kakao.interceptors.ExecutingInterceptor
import com.kaspersky.uitest_framework.kakao.interceptors.WebAssertionInterceptor
import com.kaspersky.uitest_framework.util.AppendableDescription
import org.hamcrest.Matcher

class WebAssertionProxy<E>(
        private val webAssertion: WebAssertion<E>,
        atom: Atom<E>,
        private val matcher: Matcher<E>,
        private val interceptors: List<WebAssertionInterceptor>,
        private val executingInterceptor: ExecutingInterceptor?
): WebAssertion<E>(atom) {

    override fun checkResult(view: WebView?, result: E) {

        interceptors.forEach {
            it.intercept(this, view, result as Any)
        }

        val assertionToExecute = {
            (webAssertion as WebViewAssertions.ResultCheckingWebAssertion).checkResult(view, result)
        }

        executingInterceptor?.interceptAndExecute(assertionToExecute) ?: assertionToExecute.invoke()
    }

    fun describe(): String {

        val builder = StringBuilder("Web check ")

        matcher.describeTo(AppendableDescription(builder))

        return builder.toString()
    }
}