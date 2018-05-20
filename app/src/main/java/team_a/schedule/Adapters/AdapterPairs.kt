package team_a.schedule.Adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import team_a.schedule.R
import team_a.schedule.core.rasp.Lesson

class AdapterPairs(private val values: List<Lesson>): RecyclerView.Adapter<AdapterPairs.ViewHolder>() {

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.NameView?.text = values[position].name
        holder.HallView?.text = values[position].num
        holder.TeacherView?.text = values[position].prep
        when(position){
            0 -> holder.TimeView?.text = " 08:00   09:20 "
            1 -> holder.TimeView?.text = " 09:35   10:55 "
            2 -> holder.TimeView?.text = " 11:25   12:45 "
            3 -> holder.TimeView?.text = " 13:00   14:20 "
            4 -> holder.TimeView?.text = " 14:35   15:55 "
            5 -> holder.TimeView?.text = " 16:10   17:30 "
        }
        when(values[position].type){
            "лк" -> holder.TypeView?.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.lectureBackground))
            "пр" -> holder.TypeView?.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.practicalBackground))
            "лб" -> holder.TypeView?.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.laboratoryBackground))
            null -> holder.TypeView?.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.NonePair))
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var NameView: TextView? = null
        var TeacherView: TextView? = null
        var HallView: TextView? = null
        var TimeView: TextView? = null
        var TypeView: LinearLayout? = null
        init {
            NameView = itemView.findViewById(R.id.item_pair_name)
            HallView = itemView.findViewById(R.id.item_hall_number)
            TeacherView = itemView.findViewById(R.id.item_teacher_name)
            TimeView = itemView.findViewById(R.id.item_pair_time)
            TypeView = itemView.findViewById(R.id.item_cell)
        }
    }
}