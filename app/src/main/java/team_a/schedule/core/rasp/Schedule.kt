package team_a.schedule.core.rasp

import team_a.schedule.core.rasp.Day

class Schedule(val serial: String,
               val name:String,
               val faculty: String,
               val education_form: String?,
               val week_number: String?,
               val pairs_list: List<Day>) {
    val group = pairs_list[0].lessons[0].group
    override fun toString(): String {
        return "{" +
                "serial:\"$serial\"," +
                "faculty:\"$faculty\"," +
                "education_form:\"$education_form\"," +
                "week_number:\"$week_number\"," +
                "pairs_list:$pairs_list" +
                "}"
    }
}