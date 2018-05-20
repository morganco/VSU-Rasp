package team_a.schedule.core.rasp

import team_a.schedule.core.rasp.Lesson

class Day(var lessons: List<Lesson>) {
    val date = lessons[0].date
    override fun toString(): String {
        var temp = StringBuilder("[")
        for(i in lessons) {
            temp.append(i.toString()+",")
        }
        temp.append("]")
        return temp.toString()
    }
}