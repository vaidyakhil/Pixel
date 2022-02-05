package utils

import org.json.JSONArray
import org.json.JSONObject

fun <T> JSONArray.map(transformer: ((item: JSONObject) -> T)): ArrayList<T> {
    val res: ArrayList<T> = ArrayList()
    for (index in 0 until this.length()) {
        res.add(transformer.invoke(this.get(index) as JSONObject))
    }
    return res
}