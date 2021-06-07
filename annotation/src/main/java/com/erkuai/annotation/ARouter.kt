package com.erkuai.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class ARouter(val path: String, val group: String = "")