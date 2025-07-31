package data
import java.time.LocalDateTime
data class AttendanceData(
    val employeeId: String,
    val checkInDateTime: LocalDateTime,
    var checkOutDateTime: LocalDateTime? = null
)

