package team_a.schedule.core.rasp

import team_a.schedule.core.rasp.Day
import java.io.Serializable

class Schedule(val serial: String,
               val name:String,
               val faculty: String,
               val group: String,
               val education_form: String?,
               val week_number: String?,
               val pairs_list: List<Day>){
    override fun toString(): String {
        return "{" +
                "serial:\"$serial\"," +
                "faculty:\"$faculty\"," +
                "group:\"$group\"," +
                "education_form:\"$education_form\"," +
                "week_number:\"$week_number\"," +
                "pairs_list:$pairs_list" +
                "}"
    }
}