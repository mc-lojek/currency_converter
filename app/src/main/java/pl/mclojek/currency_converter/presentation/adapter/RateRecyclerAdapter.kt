package pl.mclojek.currency_converter.presentation.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.mclojek.currency_converter.R
import pl.mclojek.currency_converter.domain.model.DailyRateSummary
import pl.mclojek.currency_converter.domain.model.Rate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RateRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items: MutableList<DailyRateSummary> = arrayListOf(
        DailyRateSummary(LocalDate.now(), "USD", listOf(Rate("GBP",0.444f), Rate("PLN" ,0.522f))),
        DailyRateSummary(LocalDate.now(), "USD", listOf(Rate("GBP",0.444f), Rate("PLN" ,0.522f))),
        DailyRateSummary(LocalDate.now(), "USD", listOf(Rate("GBP",0.444f), Rate("PLN" ,0.522f))),
    )

    fun addItem(summary: DailyRateSummary) {
        items.add(summary)
        notifyItemRangeInserted(itemCount - (items[0].rates.size + 1), itemCount)
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isEmpty()) 0
        else if (position % (items[0].rates.size + 1) == 0) 0
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
            is DayViewHolder -> holder.bind(items[position / (items[0].rates.size + 1)])
            is RateViewHolder -> {
                val dayIndex = position / (items[0].rates.size + 1)
                val rateIndex = position % (items[0].rates.size + 1) - 1

                println("pos: ${position} di: ${dayIndex} ri: ${rateIndex}")

                holder.bind(items[dayIndex].rates[rateIndex])
            }
        }
    }

    override fun getItemCount(): Int {
        return if (items.isEmpty()) 0
        else items.size * (items[0].rates.size + 1)
    }

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

}