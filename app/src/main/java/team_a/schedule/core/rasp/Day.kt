package team_a.schedule.core.rasp

import team_a.schedule.core.rasp.Lesson
import java.io.Serializable

data class Day(var date:String, var dayName: String, var lessons: MutableList<Lesson>):Serializable {

}