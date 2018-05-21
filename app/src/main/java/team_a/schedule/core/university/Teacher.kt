package team_a.schedule.core.university

import android.content.Context
import android.graphics.drawable.Drawable

class Teacher(){
    var image : Drawable? = null
    var name : String? = null
    var hall : String? = null
    var phone:String ? = null

    fun setValues(Image:Drawable,Name:String,Hall:String,Phone:String):Teacher{
            image = Image
            name = Name
            hall = Hall
            phone = Phone
        return this
    }
    override fun toString(): String {
        return "Teacher(image=$image, name='$name', hall='$hall', phone=$phone)"
    }
    fun getTeachers(context: Context):MutableList<Teacher>{
        val input = context.assets.open("teachers/Data").reader().readLines()
        val data = mutableListOf<Teacher>()
        var i = 0
        while(i<input.size){
            val path = input[i]
            val Image = Drawable.createFromStream(context.assets.open("teachers/images/$path"), null)
            i++
            val Name = input[i]
            i++
            val Hall = input[i]
            i++
            val Phone = input[i]
            i++
            data.add(Teacher().setValues(Image,Name,Hall,Phone))
            i++
        }
        return data
    }
}
