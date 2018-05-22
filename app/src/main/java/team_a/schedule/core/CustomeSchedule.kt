package team_a.schedule.core

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import team_a.schedule.Adapters.AdapterPairs
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
        val recyclerView = context.findViewById<RecyclerView>(R.id.recycler_custom_day)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AdapterPairs(day.lessons)
    }
    fun onClickDay(view: Button){
        day.dayName = view.text.toString()
        getDayScreen()
    }
    fun onClickAddLesson(view: View){
        context.setContentView(R.layout.custom_lesson)

        val adapter = ArrayAdapter<String>(context, R.layout.item_spinner, listOf("лк","пр","лб"))

        adapter.setDropDownViewResource(R.layout.item_spinner)
        var spinner = context.findViewById<View>(R.id.lessonType_spinner) as Spinner
        spinner.adapter = adapter
        spinner.setSelection(0)
    }
    fun onClickConfirmLesson(view: View){
        val name = context.findViewById<EditText>(R.id.custom_lesson_name).text.toString()
        val teacher = context.findViewById<EditText>(R.id.custom_lesson_teacher).text.toString()
        val hall = context.findViewById<EditText>(R.id.custom_lesson_hall).text.toString()
        val type = context.findViewById<Spinner>(R.id.lessonType_spinner).selectedItem.toString()
        for(i in context.ShedulesData){
            if(i.name == name) {
                Toast.makeText(context.baseContext, "Такое имя уже существует", Toast.LENGTH_SHORT).show()
                return
            }
        }
        if(name!="" && teacher!="" && hall!="" && type!=""){
            day.lessons.add(Lesson(name,teacher,hall,"",type))
            getDayScreen()
        }
        else Toast.makeText(context.baseContext, "Вы ввели не все данные", Toast.LENGTH_SHORT).show()
    }

    fun reset(){
        day = Day("","", mutableListOf())
        val data = mutableListOf<Day>()
        for(i in 0 until 6){
           data.add(day)
        }
        schedule = Schedule("","","ПОЛЬЗОВАТЕЛЬСКОЕ","","","",data)
    }

    fun onClickConfirmDay(view: View){
        val i:Int
        when(day.dayName){
            "ПОНЕДЕЛЬНИК" -> i = 0
            "ВТОРНИК" -> i = 1
            "СРЕДА" -> i = 2
            "ЧЕТВЕРГ" -> i = 3
            "ПЯТНИЦА" -> i = 4
            "СУББОТА" -> i = 5
        }
        schedule.pairs_list.add(day)
        day = Day("","", mutableListOf())
    }
}