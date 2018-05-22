package team_a.schedule.core

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import team_a.schedule.Adapters.AdapterCustomePairs
import team_a.schedule.MainActivity
import team_a.schedule.R
import team_a.schedule.core.rasp.Day
import team_a.schedule.core.rasp.Lesson
import team_a.schedule.core.rasp.Schedule

class CustomSchedule(val context: MainActivity){
    var schedule = Schedule("","","","","","", mutableListOf())
    var day = Day("","", mutableListOf())
    fun getDayScreen(){
        context.setContentView(R.layout.custom_day)
        //Toast.makeText(context.baseContext, schedule.pairs_list[position].lessons[0].name, Toast.LENGTH_SHORT).show()
        val recyclerView = context.findViewById<RecyclerView>(R.id.recycler_custom_day)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AdapterCustomePairs(day.lessons)
    }
    fun onClickDay(view: Button){
        day = Day("","", mutableListOf())
        val name  = view.text.toString()
        day = schedule.pairs_list[getDayNumber(name)]
        day.dayName = name
        getDayScreen()
    }
    fun onClickAddLesson() {
        context.setContentView(R.layout.custom_lesson)
        val adapter = ArrayAdapter<String>(context, R.layout.item_spinner, listOf("лк","пр","лб"))
        adapter.setDropDownViewResource(R.layout.item_spinner)
        val spinner = context.findViewById<View>(R.id.lessonType_spinner) as Spinner
        spinner.adapter = adapter
        spinner.setSelection(0)
    }
    fun onClickConfirmLesson() {
        val name = context.findViewById<EditText>(R.id.custom_lesson_name).text.toString()
        val teacher = context.findViewById<EditText>(R.id.custom_lesson_teacher).text.toString()
        val hall = context.findViewById<EditText>(R.id.custom_lesson_hall).text.toString()
        val type = context.findViewById<Spinner>(R.id.lessonType_spinner).selectedItem.toString()
        for(i in context.scheduleData){
            if(i.name == name) {
                Toast.makeText(context.baseContext, "Такое имя уже существует", Toast.LENGTH_SHORT).show()
                return
            }
        }
       // if(name!="" && teacher!="" && hall!="" && type!=""){
            day.lessons.add(Lesson(name,teacher,hall,"",type))
            getDayScreen()
        //}
        //else Toast.makeText(context.baseContext, "Вы ввели не все данные", Toast.LENGTH_SHORT).show()
    }

    fun reset(){
        val data = mutableListOf<Day>()
        for(i in 0 until 6){
            val day = Day("",getDayName(i), mutableListOf())
            data.add(day)
        }
        schedule = Schedule("","","ПОЛЬЗОВАТЕЛЬСКОЕ","","","",data)
    }

    fun getDayNumber(s:String): Int{
        when(s){
            "ПОНЕДЕЛЬНИК" -> return 0
            "ВТОРНИК" -> return 1
            "СРЕДА" -> return 2
            "ЧЕТВЕРГ" -> return 3
            "ПЯТНИЦА" -> return 4
            "СУББОТА" -> return 5
        }
        return 0
    }
    fun getButtonOnName(name:String):Button{
        when(name){
            "ПОНЕДЕЛЬНИК" -> return context.findViewById<Button>(R.id.day1)
            "ВТОРНИК" -> return context.findViewById<Button>(R.id.day2)
            "СРЕДА" -> return context.findViewById<Button>(R.id.day3)
            "ЧЕТВЕРГ" -> return context.findViewById<Button>(R.id.day4)
            "ПЯТНИЦА" -> return context.findViewById<Button>(R.id.day5)
            "СУББОТА" -> return context.findViewById<Button>(R.id.day6)
        }
        return context.findViewById<Button>(R.id.day1)
    }
    fun getDayName(i:Int):String{
        when(i){
           0 ->return "ПОНЕДЕЛЬНИК"
           1 ->return "ВТОРНИК"
           2 ->return "СРЕДА"
           3 ->return "ЧЕТВЕРГ"
           4 ->return "ПЯТНИЦА"
           5 ->return "СУББОТА"
        }
        return ""
    }
    fun onClickConfirmDay() {
        context.setContentView(R.layout.custom_schedule)
        for(i in schedule.pairs_list) {
            val b = getButtonOnName(i.dayName)
            if (i.lessons.size > 0) {
                b.setBackgroundColor(ContextCompat.getColor(context, R.color.lectureBackground))
            } else {
                b.setBackgroundColor(ContextCompat.getColor(context, R.color.primaryButtons))
            }
        }
        schedule.pairs_list[getDayNumber(day.dayName)] = day
        day = Day("","", mutableListOf())
    }
    fun onClickLesson(view: View){
        val text = view.findViewById<TextView>(R.id.lesson_id).text.toString().toInt()
        day.lessons.removeAt(text)
        getDayScreen()
    }
}