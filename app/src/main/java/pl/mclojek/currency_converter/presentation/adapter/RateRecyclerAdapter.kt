package pl.mclojek.currency_converter.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.mclojek.currency_converter.R
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import pl.mclojek.currency_converter.domain.model.Rate
import java.time.format.DateTimeFormatter

class RateRecyclerAdapter :
    PagingDataAdapter<DailyRateSummary, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(0) == null) 0
        else if (position % (getItem(0)!!.rates.size + 1) == 0) 0
        else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_item_day, parent, false)
                DayViewHolder(v)
            }
            else -> {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_item_rate, parent, false)
                RateViewHolder(v)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("tagtagtag", "binduje ${position}")

        when (holder) {
            is DayViewHolder -> holder.bind(getItem(position / (getItem(0)!!.rates.size + 1))!!)
            is RateViewHolder -> {
                val dayIndex = position / (getItem(0)!!.rates.size + 1)
                val rateIndex = position % (getItem(0)!!.rates.size + 1) - 1

                println("pos: ${position} di: ${dayIndex} ri: ${rateIndex}")

                holder.bind(getItem(0)!!.rates[rateIndex])
            }
        }
    }
//
//    override fun getItemCount(): Int {
//        return if (getItem(0) == null) 0
//        else itemCountsize * (items[0].rates.size + 1)
//    }

    class DayViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(summary: DailyRateSummary) {
            v.findViewById<TextView>(R.id.day_tv).text =
                summary.day.format(DateTimeFormatter.ISO_DATE)
        }
    }

    class RateViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(rate: Rate) {
            v.findViewById<TextView>(R.id.rate_tv).text = "${rate.currency} : ${rate.value}"
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<DailyRateSummary>() {
            override fun areItemsTheSame(
                oldItem: DailyRateSummary,
                newItem: DailyRateSummary
            ): Boolean {
                return oldItem.day == newItem.day
            }

            override fun areContentsTheSame(
                oldItem: DailyRateSummary,
                newItem: DailyRateSummary
            ): Boolean {
                return oldItem.rates.size == newItem.rates.size && oldItem.day == newItem.day
            }
        }
    }

}