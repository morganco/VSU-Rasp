package team_a.schedule.core.build.comparators

import team_a.schedule.core.rasp.Lesson

class LessonComparator : Comparator<Lesson> {
    override fun compare(p0: Lesson?, p1: Lesson?): Int {
        val compare = Integer.compare(p0?.date?.toInt() ?: 0, p1?.date?.toInt() ?: 0)
        return if(compare == 0)
            Integer.compare(p0?.num?.toInt() ?: 0, p1?.num?.toInt()?: 0)
        else
            compare
    }
}