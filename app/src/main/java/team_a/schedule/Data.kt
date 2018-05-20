package team_a.schedule

import java.io.Serializable


class Teacher(Image:Int,Name:String,Hall:String,Phone:String):Serializable{
    val image = Image
    val name = Name
    val hall = Hall
    val phone:String? = Phone
    override fun toString(): String {
        return "Teacher(image=$image, name='$name', hall='$hall', phone=$phone)"
    }

}

class University{
    private val math:Array<String> = arrayOf("МАТФАК","1","2","12","41")
    private val filf:Array<String> = arrayOf("ФИЛФАК","21","2","51")
    private val fizf:Array<String> = arrayOf("ФИЗФАК","27","33","49")
    private val unidata:Array<Array<String>> = arrayOf(math,filf,fizf)
    fun getFaculties():List<String>{
        val v = mutableListOf<String>()
        for(i in 0 until unidata.size){
            v.add(unidata[i][0])
        }
        return v
    }
    fun getGroups(fac:String):List<String>{
        val v = mutableListOf<String>()
        for(i in 0 until unidata.size){
            if(unidata[i][0]==fac){
                for(j in 1 until unidata[i].size){
                    v.add(unidata[i][j])
                }
                break
            }
        }
        return v
    }

}


fun getTeachers():List<Teacher>{
    var data = mutableListOf<Teacher>()
    data.add(Teacher(R.drawable.borodichsm,"БОРОДИЧ СЕРГЕЙ МИТРОФАНОВИЧ","Кафедра геометрии и математического анализа","Sirius2018Serge@gmail.com"))
    data.add(Teacher(R.drawable.ivanovagv,"ИВАНОВА ЖАННА ВИКТОРОВНА ","Кафедра геометрии и математического анализа","IvanovaZhV@gmail.com"))
    data.add(Teacher(R.drawable.podoksenovmn,"ПОДОКСЁНОВ МИХАИЛ НИКОЛАЕВИЧ","Кафедра геометрии и математического анализа",""))
    data.add(Teacher(R.drawable.trubnikovyv,"ТРУБНИКОВ ЮРИЙ ВАЛЕНТИНОВИЧ","Кафедра геометрии и математического анализа","Trubnikov@mail.ru"))
    return data
}