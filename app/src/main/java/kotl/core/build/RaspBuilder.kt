package kotl.core.build

import kotl.core.build.comparators.LessonComparator
import kotl.core.rasp.Lesson
import kotl.core.rasp.Schedule

class RaspBuilder {
    fun buildScheduleByGroup(oldSchedule: Schedule, group: String, subGroup: String): Schedule {
        val tempSerial = oldSchedule.serial
        val tempFaculty = oldSchedule.faculty
        val tempEducationForm = oldSchedule.education_form
        val tempWeekNumber = oldSchedule.week_number
        val tempLessons: List<Lesson> = mutableListOf()
        for (i in oldSchedule.pairs_list) {
            if (i.group == group && i.sub_group == subGroup) {
                tempLessons.plus(i)
            }
        }
        tempLessons.sortedWith(LessonComparator())
        return Schedule(tempSerial, tempFaculty, tempEducationForm, tempWeekNumber, tempLessons)
    }

    fun buildScheduleByTeacher(oldSchedule: Schedule, teacherName: String): Schedule {
        val tempSerial = oldSchedule.serial
        val tempFaculty = oldSchedule.faculty
        val tempEducationForm = oldSchedule.education_form
        val tempWeekNumber = oldSchedule.week_number
        val tempLessons: List<Lesson> = mutableListOf()
        for (i in oldSchedule.pairs_list) {
            if (i.prep == teacherName) {
                tempLessons.plus(i)
            }
        }
        tempLessons.sortedWith(LessonComparator())
        return Schedule(tempSerial, tempFaculty, tempEducationForm, tempWeekNumber, tempLessons)
    }
}