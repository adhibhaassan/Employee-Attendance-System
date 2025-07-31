package operation
import data.AttendanceData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AttendanceService {
    private val attendanceList = AttendanceManager()

    fun handleCheckIn(empService: EmployeeService): String {
        print("Employee ID: ")
        val id = readln().trim()
        if (!empService.isValidId(id)) return "Invalid ID"

        val time = getDateTime() ?: return "Invalid datetime"
        return if (attendanceList.add(AttendanceData(id, time))) {
            "Check-in successful: $time"
        } else {
            "Check-in failed."
        }
    }

    fun checkOutEmployee() {
        print("Enter your Employee ID: ")
        val id = readln().trim()

        val records = attendanceList.allByEmployee(id)
        val activeRecord = records.lastOrNull { it.checkOutDateTime == null }

        if (activeRecord == null) {
            println("No active check-in found for this ID.")
            return
        }

        val checkOutTime = getDateTime() ?: return

        if (checkOutTime.isBefore(activeRecord.checkInDateTime)) {
            println("Check-out time cannot be before check-in time.")
            return
        }

        activeRecord.checkOutDateTime = checkOutTime

        val duration = java.time.Duration.between(activeRecord.checkInDateTime, checkOutTime)
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60

        println("Checked out at ${checkOutTime.toLocalTime()} on ${checkOutTime.toLocalDate()}")
        println("Worked ${"%02d".format(hours)}h ${"%02d".format(minutes)}m")
    }

    fun getIdForAttendance(empService: EmployeeService): List<AttendanceData>? {
        print("Enter Employee ID: ")
        val id = readln().trim()
        return if (!empService.isValidId(id)){
            null
        }
        else {
            attendanceList.allByEmployee(id)
        }
    }

    fun getDateTime(): LocalDateTime? {
        print("Enter date-time (yyyy-MM-dd HH:mm)(OR) Press Enter for current: ")
        val input = readln().trim()
        val now = LocalDateTime.now()
        return if (input.isEmpty()) {
            now
        } else {
            try {
                val parsedDateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                if (parsedDateTime.isAfter(now)) {
                    println("Cannot use a future date/time.")
                    null
                } else {
                    parsedDateTime
                }
            } catch (e: Exception) {
                println("Invalid date format.")
                null
            }
        }
    }
}
