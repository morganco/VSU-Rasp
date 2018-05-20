package team_a.schedule.core.load

import android.content.Context
import team_a.schedule.core.rasp.Day
import team_a.schedule.core.rasp.Lesson
import team_a.schedule.core.rasp.Schedule
import java.io.*

class ScheduleSaveLoader {
    //    fun LoadFromJSONString(JSONString: String): Schedule {
//        val result = Klaxon().parse<Schedule>(JSONString)
//                ?: Schedule("NullSchedule",
//                        "Null",
//                        "Null",
//                        "Null",
//                        listOf(Lesson("Null",
//                                "Null",
//                                "Null",
//                                "Null",
//                                "Null",
//                                "Форточка",
//                                "Null")))
//        return result
//    }
    fun loadScheduleFromCacheDir(context: Context, fileName: String): Schedule {
        val cacheDir = context.cacheDir
        val file = File(cacheDir, fileName)
        if (file.exists()) {
            val inps = FileInputStream(file)
            val ois = ObjectInputStream(inps)
            val schedule: Schedule = ois.readObject() as Schedule
            ois.close()
            return schedule
        }
        val schedule = Schedule("NullSchedule",
                "Null",
                "Null",
                "Null",
                "null",
                listOf(Day(listOf(Lesson("Null",
                        "Null",
                        "Null",
                        "Null",
                        "Null",
                        "Null",
                        "Форточка",
                        "Null")))))
//тут предполагалась загрузка из интернета, но нам не написали обработку запроса
        return schedule
    }

    fun saveScheduleToCacheDir(schedule: Schedule, context: Context, fileName: String) {
        val cacheDir = context.cacheDir
        val file = File(cacheDir, fileName)
        val os = FileOutputStream(file)
        val oos = ObjectOutputStream(os)
        oos.writeObject(schedule)
        oos.close()
    }

    fun loadScheduleFromAssets(context: Context, fileName: String): Schedule {
        val assetManager = context.assets
            val inps = assetManager.open(fileName)
            val ois = ObjectInputStream(inps)
            val schedule: Schedule = ois.readObject() as Schedule
            ois.close()
            return schedule
    }

}