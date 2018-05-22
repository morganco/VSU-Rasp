package team_a.schedule.core.load

import android.content.Context
import com.beust.klaxon.Klaxon
import team_a.schedule.core.rasp.Day
import team_a.schedule.core.rasp.Lesson
import team_a.schedule.core.rasp.Schedule
import java.io.*



class ScheduleSaveLoader(val data:MutableList<Schedule>)
{

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
                "null",
                "Null",
                "null",
                mutableListOf(Day("null",
                        "null",
                        mutableListOf(Lesson("Null",
                                "Null",
                                "Null",
                                "Null",
                                "Форточка"
                        )))))
//тут предполагалась загрузка из интернета, но нам не написали обработку запроса
        return schedule
    }

    fun saveScheduleToCacheDir(schedule: Schedule, context: Context, fileName: String) {
        val cacheDir = context.cacheDir
        File(cacheDir, "/userdata").mkdir()
        val file = File(cacheDir, fileName)
        val os = FileOutputStream(file)
        val oos = ObjectOutputStream(os)
        oos.writeObject(schedule)
        oos.close()
    }

    fun removeScheduleFromCacheDir(context: Context, name: String) {
        val cacheDir = context.cacheDir
        File(cacheDir, "/userdata/$name").delete()
    }

    fun loadScheduleFromAssets(context: Context, fileName: String):MutableList<Schedule>{
        val assetManager = context.assets
        val inps = assetManager.open("data")
        var b = Klaxon().parse<ScheduleSaveLoader>(inps.reader().readText())
        val a = b!!.data
        System.out.println(a[0].name)
        for(i in a) {
            var cacheDir = context.cacheDir
            cacheDir = File(cacheDir, "schedules")
            cacheDir.mkdir()
            cacheDir = File(cacheDir, i.faculty)
            cacheDir.mkdir()
            val file = File(cacheDir, i.group)
            val os = FileOutputStream(file)
            val oos = ObjectOutputStream(os)
            oos.writeObject(i)
            oos.close()
        }
    return a
    }

    fun getSavedData(context: Context): MutableList<Schedule> {
        val dir = context.cacheDir
        val path = "/userdata"
        val list = File(dir, path).list()
        val data = mutableListOf<Schedule>()
        if (list == null) return data
        for (i in list) {
            val file = File(dir, "/userdata/$i")
            if (file.exists()) {
                val inps = FileInputStream(file)
                val ois = ObjectInputStream(inps)
                data.add(ois.readObject() as Schedule)
                ois.close()
            }
        }
        return data
    }
}




/*
class ScheduleSaveLoader() {
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
                "null",
                "Null",
                "null",
                listOf(Day("null",
                        "null",
                        listOf(Lesson("Null",
                        "Null",
                        "Null",
                        "Null",
                        "Форточка"
                        )))))
//тут предполагалась загрузка из интернета, но нам не написали обработку запроса
        return schedule
    }

    fun saveScheduleToCacheDir(schedule: Schedule, context: Context, fileName: String) {
        val cacheDir = context.cacheDir
        File(cacheDir,"/userdata").mkdir()
        val file = File(cacheDir, fileName)
        val os = FileOutputStream(file)
        val oos = ObjectOutputStream(os)
        oos.writeObject(schedule)
        oos.close()
    }

    fun removeScheduleFromCacheDir(context: Context,name:String){
        val cacheDir = context.cacheDir
        File(cacheDir,"/userdata/$name").delete()
    }

    fun loadScheduleFromAssets(context: Context, fileName: String): Schedule {
        val assetManager = context.assets
            val inps = assetManager.open(fileName)
            val a = Klaxon().parse<Schedule>(inps.reader().readText())!!
            return a
    }

    fun getSavedData(context: Context):MutableList<Schedule> {
        val dir = context.cacheDir
        val path = "/userdata"
        val list = File(dir, path).list()
        val data = mutableListOf<Schedule>()
        if  (list==null) return data
        for (i in list) {
            val file = File(dir, "/userdata/$i")
            if (file.exists()) {
                val inps = FileInputStream(file)
                val ois = ObjectInputStream(inps)
                data.add(ois.readObject() as Schedule)
                ois.close()
            }
        }
        return data
    }
}*/