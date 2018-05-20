package team_a.schedule.core.rasp

class Lesson(val date: String,
             val name: String,
             val prep: String,
             val hall: String,
             val group: String,
             val sub_group: String,
             val type: String,
             val num: String) {
    override fun toString(): String {
        return "{date:\"$date\", name:\"$name\", prep:\"$prep\",hall:\"$hall\", group:\"$group\", sub_group:\"$sub_group\", type:\"$type\", num:\"$num\"}"

    }
}