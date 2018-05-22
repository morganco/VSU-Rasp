package team_a.schedule.core.rasp

import team_a.schedule.core.rasp.Lesson
import java.io.Serializable

data class Day(var date:String, var dayName: String, var lessons: MutableList<Lesson>):Serializable {

    override fun toString(): String {
        var temp = StringBuilder("{ [ \"date\":\"$date\",\"lessons\"++" +
                "" + " [")
       for(i in lessons) {
           temp.append(i.toString()+",")
        }
        temp.append("]")
        return temp.toString()
    }
}