package team_a.schedule.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import team_a.schedule.R
import team_a.schedule.core.rasp.Schedule


class AdapterScheduleList(private val values: List<Schedule>): RecyclerView.Adapter<AdapterScheduleList.ViewHolder>() {

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.NameView?.text = values[position].name
        holder.GroupView?.text = values[position].group
        holder.FacultyView?.text = values[position].faculty
        holder.ButtonView?.text = position.toString()
    }



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var NameView: TextView? = null
        var GroupView: TextView? = null
        var FacultyView: TextView? = null
        var ButtonView: Button? = null
        init {
            NameView = itemView.findViewById(R.id.NameShedule)
            GroupView = itemView.findViewById(R.id.NameGroup)
            FacultyView = itemView.findViewById(R.id.NameFaculty)
            ButtonView = itemView.findViewById(R.id.delete_button)
        }
    }
}
