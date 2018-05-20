package team_a.schedule.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import team_a.schedule.R


class AdapterList(private val values: List<String>): RecyclerView.Adapter<AdapterList.ViewHolder>() {
    override fun getItemCount() = values.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.NameView?.text = values[position]
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var NameView: TextView? = null
        init {
            NameView = itemView.findViewById(R.id.item_text)
        }
    }
}