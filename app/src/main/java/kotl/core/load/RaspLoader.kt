package kotl.core.load

import com.beust.klaxon.Klaxon
import kotl.core.rasp.Lesson
import kotl.core.rasp.Schedule

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