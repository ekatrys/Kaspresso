package com.kaspersky.kaspresso.params

import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException

/**
 * The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl] parameters.
 */
class FlakySafetyParams(
    timeoutMs: Long = DEFAULT_TIMEOUT_MS,
    intervalMs: Long = DEFAULT_INTERVAL_MS,

    /**
     * The set of exceptions, if caught, the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl] will continue
     * to attempt.
     */
    val allowedExceptions: Set<Class<out Throwable>> = DEFAULT_ALLOWED_EXCEPTIONS
) {
    companion object {
        private const val DEFAULT_TIMEOUT_MS: Long = 5_000L
        private const val DEFAULT_INTERVAL_MS: Long = 500L
        private val DEFAULT_ALLOWED_EXCEPTIONS = setOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java
        )
    }

    /**
     * The timeout during which attempts will be made by the
     * [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl].
     */
    var timeoutMs: Long = timeoutMs
        set(value) {
            if (intervalMs >= value) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }

    /**
     * The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl].
     */
    var intervalMs: Long = intervalMs
        set(value) {
            if (value >= timeoutMs) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }
}