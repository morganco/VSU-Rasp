package team_a.schedule.core.rasp

import java.io.Serializable

data class Lesson(val name: String,
             val prep: String,
             val hall: String,
             val sub_group: String,
             val type: String): Serializable {
    override fun toString(): String {
        return "{\"name\":\"$name\", \"prep\":\"$prep\",\"hall\":\"$hall\", \"sub_group\":\"$sub_group\", \"type\":\"$type\"}"

    }
}