package android.support.test.espresso.assertion

import android.support.test.espresso.ViewAssertion
import com.kaspersky.uitest_framework.util.AppendableDescription

fun ViewAssertion?.describe(): String {

    if (this == null) return "null"

    val builder = StringBuilder("Check ")

    if (this is ViewAssertions.MatchesViewAssertion) {
        this.viewMatcher.describeTo(AppendableDescription(builder))
    } else {
        builder.append(this::class.java.simpleName)
    }

    return builder.toString()
}