package team_a.schedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import android.widget.Toast
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import team_a.schedule.Adapters.AdapterList
import team_a.schedule.Adapters.AdapterPairs
import team_a.schedule.Adapters.AdapterScheduleList
import team_a.schedule.Adapters.AdapterTeachers
import team_a.schedule.core.load.ScheduleSaveLoader
import team_a.schedule.core.rasp.Schedule
import team_a.schedule.core.university.*
import java.io.File


class MainActivity : AppCompatActivity() {
    var ShedulesData = mutableListOf<Schedule>()
    val menuList = listOf("Расписания", "Преподаватели")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ShedulesData = ScheduleSaveLoader(ShedulesData).getSavedData(baseContext)
        ScheduleSaveLoader(ShedulesData).loadScheduleFromAssets(baseContext,"data")
        if(!File(baseContext.cacheDir,"firstlaunch").exists()){
            File(baseContext.cacheDir,"firstlaunch").createNewFile()
        }
        getShedulesScreen()
    }



    fun getShedulesScreen() {
        setContentView(R.layout.schedules_layout)
        val recyclerView = findViewById<RecyclerView>(R.id.shed_r)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterScheduleList(ShedulesData)

        val menurecyclerView = findViewById<RecyclerView>(R.id.menu_recycler)
        menurecyclerView.layoutManager = LinearLayoutManager(this)
        menurecyclerView.adapter = AdapterList(menuList)
    }

    fun getTeachersScreen() {
        setContentView(R.layout.teachers_layout)
        val recyclerView = findViewById<RecyclerView>(R.id.teachers_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterTeachers(Teacher().getTeachers(baseContext))

        val menurecyclerView = findViewById<RecyclerView>(R.id.menu_recycler)
        menurecyclerView.layoutManager = LinearLayoutManager(this)
        menurecyclerView.adapter = AdapterList(menuList)
    }


    fun onClickSheduleButton(view: View) {
        setContentView(R.layout.new_schedule_list)
        val data = University(baseContext).constructor()
        val faculty_adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data.getFaculties())
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListOf())

        faculty_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var spinner = findViewById<View>(R.id.faculty_spinner) as Spinner
        spinner.adapter = faculty_adapter
        spinner.setSelection(0)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                adapter.clear()
                adapter.addAll(data.getGroups(selectedItem))
                spinner = findViewById<View>(R.id.group_spinner) as Spinner
                spinner.adapter = adapter
                spinner.setSelection(0)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

    }

    fun onClickNewSheduleButton(view: View) {
        val name = findViewById<EditText>(R.id.new_shedule_name).text.toString()
        val group = findViewById<Spinner>(R.id.group_spinner).selectedItem.toString()
        val faculty = findViewById<Spinner>(R.id.faculty_spinner).selectedItem.toString()
        if (name != "") {
            for (i in 0 until ShedulesData.size) {
                if (name == ShedulesData[i].name) {
                    Toast.makeText(baseContext, "Такое имя уже существует", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            val data = ScheduleSaveLoader(ShedulesData).loadScheduleFromCacheDir(baseContext,"schedules/$faculty/$group")
            //val data = ScheduleSaveLoader(null).loadScheduleFromAssets(baseContext, "schedules/$faculty/$group")
            ShedulesData.add(Schedule(data.serial,name,faculty,group,data.education_form,data.week_number,data.pairs_list))
            ScheduleSaveLoader(ShedulesData).saveScheduleToCacheDir(ShedulesData[ShedulesData.size - 1],baseContext,"userdata/$name")
            getShedulesScreen()
        } else {
            Toast.makeText(baseContext, "Вы ввели не все данные", Toast.LENGTH_SHORT).show()
        }
    }

    var back_pressed: Long = 0
    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
            super.onBackPressed()
        else {
            Toast.makeText(baseContext, "Нажмите еще раз, чтобы выйти",
                    Toast.LENGTH_SHORT).show()
            getShedulesScreen()
        }
        back_pressed = System.currentTimeMillis()
    }

    fun onClickNewShedBackButton(view: View) {
        getShedulesScreen()
    }

    fun onClickDeleteButton(view: View) {
        val text = view.findViewById<TextView>(R.id.delete_button).text
        for (i in 0 until ShedulesData.size) if (i.toString() == text.toString()) {

            val alertDialog = AlertDialog.Builder(this@MainActivity)
            alertDialog.setTitle("Вы уверены, что хотите это удалить?")

            alertDialog.setIcon(R.drawable.index)

            alertDialog.setPositiveButton("ДА", DialogInterface.OnClickListener { dialog, which ->
                ScheduleSaveLoader(ShedulesData).removeScheduleFromCacheDir(baseContext,ShedulesData[i].name)
                ShedulesData.removeAt(i)
                Toast.makeText(getApplicationContext(), "Удалено", Toast.LENGTH_SHORT).show()
                getShedulesScreen()
            })

            alertDialog.setNegativeButton("НЕТ", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(getApplicationContext(), "Отмена", Toast.LENGTH_SHORT).show()
            })
            alertDialog.show()
        }
        getShedulesScreen()
    }

    fun onClickShedule(view: View) {
        val text = view.findViewById<TextView>(R.id.delete_button).text.toString().toInt()
        val slider: ScreenPager = ScreenPager(baseContext)
        for (i in 0 until ShedulesData[text].pairs_list.size) {
            val day = ShedulesData[text].pairs_list[i]
            setContentView(R.layout.day_schedule)
            val view: View = findViewById(R.id.Day_shedule)
            val recyclerView = view.findViewById<RecyclerView>(R.id.Day_list)
            findViewById<TextView>(R.id.day_data).text = day.date.toString()
            findViewById<TextView>(R.id.day_name).text = day.dayName.toString()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = AdapterPairs(ShedulesData[text].pairs_list[i].lessons)
            slider.addScreen(view)
        }
        setContentView(slider)
    }

    fun onClickMenu(view: View) {
        val item = view.findViewById<TextView>(R.id.item_text).text.toString()
        when (item) {
            "Расписания" -> getShedulesScreen()
            "Преподаватели" -> getTeachersScreen()
        }
    }

    fun elem(a: Int) {

    }

}






