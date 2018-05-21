package team_a.schedule.core.university

import team_a.schedule.R

class Teacher(Image:Int,Name:String,Hall:String,Phone:String){
    val image = Image
    val name = Name
    val hall = Hall
    val phone:String? = Phone
    override fun toString(): String {
        return "Teacher(image=$image, name='$name', hall='$hall', phone=$phone)"
    }

}



fun getTeachers():List<Teacher>{
    var data = mutableListOf<Teacher>()
    data.add(Teacher(R.drawable.borodichsm, "БОРОДИЧ СЕРГЕЙ МИТРОФАНОВИЧ", "Кафедра геометрии и математического анализа", "Sirius2018Serge@gmail.com"))
    data.add(Teacher(R.drawable.ivanovagv, "ИВАНОВА ЖАННА ВИКТОРОВНА ", "Кафедра геометрии и математического анализа", "IvanovaZhV@gmail.com"))
    data.add(Teacher(R.drawable.podoksenovmn, "ПОДОКСЁНОВ МИХАИЛ НИКОЛАЕВИЧ", "Кафедра геометрии и математического анализа", ""))
    data.add(Teacher(R.drawable.trubnikovyv, "ТРУБНИКОВ ЮРИЙ ВАЛЕНТИНОВИЧ", "Кафедра геометрии и математического анализа", "Trubnikov@mail.ru"))
    return data
}