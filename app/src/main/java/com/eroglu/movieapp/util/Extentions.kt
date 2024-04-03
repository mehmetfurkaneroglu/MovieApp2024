package com.eroglu.movieapp.util

import android.os.Build
import android.os.Bundle
import java.io.Serializable

//Ref : https://stackoverflow.com/a/35522422/3341089
inline fun <T1: Any, T2: Any, R: Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2)->R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}
inline fun <T1: Any, T2: Any, T3: Any, R: Any> safeLet(p1: T1?, p2: T2?, p3: T3?, block: (T1, T2, T3)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}
inline fun <T1: Any, T2: Any, T3: Any,T4: Any, T5: Any, T6: Any, R: Any> safeLet(p1: T1?, p2: T2?, p3: T3?,p4: T4?, p5: T5?, p6: T6?, block: (T1, T2, T3, T4, T5, T6)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null && p6 != null) block(p1, p2, p3, p4, p5, p6) else null
}
inline fun <T1: Any, T2: Any, T3: Any,T4: Any, T5: Any, R: Any> safeLet(p1: T1?, p2: T2?, p3: T3?,p4: T4?, p5: T5?,block: (T1, T2, T3, T4, T5)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null ) block(p1, p2, p3, p4, p5) else null
}
inline fun <T1: Any, T2: Any, T3: Any,T4:Any, R: Any> safeLet(p1: T1?, p2: T2?, p3: T3?,p4:T4?, block: (T1, T2, T3,T4)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null) block(p1, p2, p3,p4) else null
}


fun <T : Serializable?> Bundle.getMSerializable(key: String, m_class: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        this.getSerializable(key, m_class)
    else
        this.getSerializable(key) as T?
}
