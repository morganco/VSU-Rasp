package team_a.schedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import android.widget.Toast
import android.support.v7.app.AlertDialog
import team_a.schedule.Adapters.AdapterList
import team_a.schedule.Adapters.AdapterPairs
import team_a.schedule.Adapters.AdapterScheduleList
import team_a.schedule.Adapters.AdapterTeachers
import team_a.schedule.core.CustomSchedule
import team_a.schedule.core.load.ScheduleSaveLoader
import team_a.schedule.core.rasp.Schedule
import team_a.schedule.core.university.*
import java.io.File


open class MainActivity : AppCompatActivity() {
    var scheduleData = mutableListOf<Schedule>()
    private val menuList = listOf("РАСПИСАНИЯ", "ПРЕПОДАВАТЕЛИ","НАСТРОЙКИ")
    private var backPressed:Long = 0
    var customSchedule = CustomSchedule(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleData = ScheduleSaveLoader(scheduleData).getSavedData(baseContext)

        if(!File(baseContext.cacheDir,"firstlaunch").exists()){
            ScheduleSaveLoader(scheduleData).loadScheduleFromAssets(baseContext,"data")
            File(baseContext.cacheDir,"firstlaunch").createNewFile()
        }
        getShedulesScreen()
    }

    fun getShedulesScreen() {
        setContentView(R.layout.schedules_layout)
        val recyclerView = findViewById<RecyclerView>(R.id.shed_r)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterScheduleList(scheduleData)

        val menurecyclerView = findViewById<RecyclerView>(R.id.menu_recycler)
        menurecyclerView.layoutManager = LinearLayoutManager(this)
        menurecyclerView.adapter = AdapterList(menuList)

        getHiddenScreen()
    }

    fun getTeachersScreen() {
        setContentView(R.layout.teachers_layout)
        val recyclerView = findViewById<RecyclerView>(R.id.teachers_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterTeachers(Teacher().getTeachers(baseContext))

        getHiddenScreen()
    }

    fun getHiddenScreen(){
        val menurecyclerView = findViewById<RecyclerView>(R.id.menu_recycler)
        menurecyclerView.layoutManager = LinearLayoutManager(this)
        menurecyclerView.adapter = AdapterList(menuList)
    }

    fun onClickSheduleButton(view: View) {
        setContentView(R.layout.new_schedule_list)
        val data = University(baseContext).constructor()
        val faculty_adapter = ArrayAdapter<String>(this, R.layout.item_spinner, data.getFaculties())
        val adapter = ArrayAdapter<String>(this, R.layout.item_spinner, arrayListOf())

        faculty_adapter.setDropDownViewResource(R.layout.item_spinner)
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
            for (i in 0 until scheduleData.size) {
                if (name == scheduleData[i].name) {
                    Toast.makeText(baseContext, "Такое имя уже существует", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            val data = ScheduleSaveLoader(scheduleData).loadScheduleFromCacheDir(baseContext,"schedules/$faculty/$group")
            scheduleData.add(Schedule(data.serial,name,faculty,group,data.education_form,data.week_number,data.pairs_list))
            ScheduleSaveLoader(scheduleData).saveScheduleToCacheDir(scheduleData[scheduleData.size - 1],baseContext,"userdata/$name")
            getShedulesScreen()
        } else {
            Toast.makeText(baseContext, "Вы ввели не все данные", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (backPressed + 1000 > System.currentTimeMillis())
            super.onBackPressed()
        else {
            Toast.makeText(baseContext, "Нажмите еще раз, чтобы выйти",
                    Toast.LENGTH_SHORT).show()
            getShedulesScreen()
        }
        backPressed = System.currentTimeMillis()
    }

    fun onClickNewShedBackButton(view: View) {
        getShedulesScreen()
    }

    fun onClickDeleteButton(view: View) {
        val text = view.findViewById<TextView>(R.id.delete_button).text
        for (i in 0 until scheduleData.size) if (i.toString() == text.toString()) {

            val alertDialog = AlertDialog.Builder(this@MainActivity)
            alertDialog.setTitle("Вы уверены, что хотите это удалить?")

            alertDialog.setIcon(R.drawable.cross)
            alertDialog.setPositiveButton("ДА", { _, _ ->
                ScheduleSaveLoader(scheduleData).removeScheduleFromCacheDir(baseContext,scheduleData[i].name)
                scheduleData.removeAt(i)
                Toast.makeText(applicationContext, "Удалено", Toast.LENGTH_SHORT).show()
                getShedulesScreen()
            })

            alertDialog.setNegativeButton("НЕТ", { _, _ ->
                Toast.makeText(applicationContext, "Отмена", Toast.LENGTH_SHORT).show()
            })
            alertDialog.show()
        }
        getShedulesScreen()
    }

    fun onClickShedule(view: View) {
        val text = view.findViewById<TextView>(R.id.delete_button).text.toString().toInt()
        val slider = ScreenPager(baseContext)
        for (i in 0 until scheduleData[text].pairs_list.size) {
            val day = scheduleData[text].pairs_list[i]
            setContentView(R.layout.day_schedule)
            val view: View = findViewById(R.id.Day_shedule)
            val recyclerView = view.findViewById<RecyclerView>(R.id.Day_list)
            findViewById<TextView>(R.id.day_data).text = day.date
            findViewById<TextView>(R.id.day_name).text = day.dayName
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = AdapterPairs(scheduleData[text].pairs_list[i].lessons)
            slider.addScreen(view)
        }
        setContentView(slider)
    }

    fun onClickMenu(view: View) {
        val item = view.findViewById<TextView>(R.id.item_text).text.toString()
        when (item) {
            "РАСПИСАНИЯ" -> getShedulesScreen()
            "ПРЕПОДАВАТЕЛИ" -> getTeachersScreen()
            "НАСТРОЙКИ"->{setContentView(R.layout.settings_layout)
                          getHiddenScreen()}
        }
    }

    fun onClickUpdate(view: View){
        ScheduleSaveLoader(scheduleData).loadScheduleFromAssets(baseContext,"data")
        getShedulesScreen()
        scheduleData.clear()
        Toast.makeText(baseContext, "ДАННЫЕ ОБНОВЛЕНЫ",
                Toast.LENGTH_SHORT).show()
    }

    fun onClickEraseCache(view: View){
        val list = baseContext.cacheDir.list()
        for (i in list){
            File(baseContext.cacheDir,i).deleteRecursively()
        }
        ScheduleSaveLoader(scheduleData).loadScheduleFromAssets(baseContext,"data")
        File(baseContext.cacheDir,"firstlaunch").createNewFile()
        Toast.makeText(baseContext, "КЭШ ОЧИЩЕН",
                Toast.LENGTH_SHORT).show()
    }

    fun onClickCustomSchedule(view: View){
        customSchedule = CustomSchedule(this)
        customSchedule.reset()
        setContentView(R.layout.custom_schedule)
    }

    fun onClickCustomDay(view: View){
        customSchedule.onClickDay(view as Button)
    }

    fun onClickCustomAddLesson(view: View){
        customSchedule.onClickAddLesson()
    }

    fun onClickCustomConfirmLesson(view: View){
        customSchedule.onClickConfirmLesson()
    }

    fun onClickCustomBackLesson(view: View){
        customSchedule.getDayScreen()
    }


    fun onClickCustomBackSchedule(view: View){
        setContentView(R.layout.settings_layout)
        getHiddenScreen()
    }

    fun onClickCustomConfirmDay(view: View){
        customSchedule.onClickConfirmDay()
    }

    fun onClickCustomLesson(view: View){
        customSchedule.onClickLesson(view)
    }

    fun onClickCustomeConfirmSchedule(view: View){
        customSchedule.schedule.name = findViewById<EditText>(R.id.custom_name).text.toString()
        if(customSchedule.schedule.name==""){
            Toast.makeText(baseContext, "Вы ввели не все данные", Toast.LENGTH_SHORT).show()
            return
        }
        scheduleData.add(customSchedule.schedule)
        ScheduleSaveLoader(scheduleData).saveScheduleToCacheDir(customSchedule.schedule,baseContext,"userdata/${customSchedule.schedule.name}")
        customSchedule.reset()
        getShedulesScreen()
    }
}


