package team_a.schedule.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import team_a.schedule.R
import team_a.schedule.core.university.Teacher


class AdapterTeachers(private val values: List<Teacher>): RecyclerView.Adapter<AdapterTeachers.ViewHolder>() {

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_teacher, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.NameView?.text = values[position].name
        holder.ImageView?.setImageResource(values[position].image)
        holder.HallView?.text = values[position].hall
        holder.PhoneView?.text = values[position].phone
    }



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ImageView: ImageView? = null
        var NameView: TextView? = null
        var HallView: TextView? = null
        var PhoneView: TextView? = null
        init {
            NameView = itemView.findViewById(R.id.NameTeacher)
            ImageView = itemView.findViewById(R.id.ImageTeacher)
            HallView = itemView.findViewById(R.id.Caphedra)
            PhoneView = itemView.findViewById(R.id.PhoneTeacher)
        }
    }
}
