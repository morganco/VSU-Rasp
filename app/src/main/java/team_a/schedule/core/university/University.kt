package team_a.schedule.core.university

import android.content.Context
import team_a.schedule.R
import java.io.File
import java.io.Serializable


class University(val context: Context){
    private val unidata = mutableListOf<MutableList<String>>()
    fun constructor(): University{
        val dir = File(context.cacheDir,"schedules").list()
        for(i in dir){
            System.out.println(i)
            val data = mutableListOf<String>()
            data.add(i)
            val ddir = File(context.cacheDir,"schedules/$i").list()
            for(j in ddir) data.add(j)
                unidata.add(data)
        }
        return this
    }

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
