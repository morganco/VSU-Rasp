package kotlin.core.load

import com.beust.klaxon.Klaxon
import kotlin.core.rasp.Lesson
import kotlin.core.rasp.Schedule

class RaspLoader {
    fun LoadFromJSONString(JSONString: String): Schedule {
        val result = Klaxon().parse<Schedule>(JSONString)
                ?: Schedule("NullSchedule",
                        "Null",
                        "Null",
                        "Null",
                        listOf(Lesson("Null",
                                "Null",
                                "Null",
                                "Null",
                                "Null",
                                "Форточка",
                                "Null")))
        return result
    }
}