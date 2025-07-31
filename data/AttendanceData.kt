package data
import java.time.LocalDateTime
data class AttendanceData(
    val employeeId: String,
    val checkInDateTime: LocalDateTime,
    var checkOutDateTime: LocalDateTime? = null
){
    override fun toString(): String {
        val out = checkOutDateTime?.toString() ?: "N/A"
        return "IN: $checkInDateTime | OUT: $out"
    }
}

