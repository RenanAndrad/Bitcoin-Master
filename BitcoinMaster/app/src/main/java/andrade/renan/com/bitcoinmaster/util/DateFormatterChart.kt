package andrade.renan.com.bitcoinmaster.util

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class DateFormatterChart: ValueFormatter() {


    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return convertLongToTime(value.toLong())
    }


    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy")
        var teste:String = format.format(date)
        return teste
    }

}
