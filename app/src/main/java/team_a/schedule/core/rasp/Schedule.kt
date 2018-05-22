package team_a.schedule.core.rasp

import java.io.Serializable

data class Schedule(var serial: String,
               var name:String,
               var faculty: String,
               var group: String,
               var education_form: String?,
               var week_number: String?,
               var pairs_list: MutableList<Day>):Serializable{

}