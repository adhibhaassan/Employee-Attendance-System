package operation

import data.AttendanceData
class AttendanceManager : ArrayList<AttendanceData>() {

    override fun add(element: AttendanceData): Boolean {
        val today = element.checkInDateTime.toLocalDate()

        if (any { it.employeeId == element.employeeId && it.checkInDateTime.toLocalDate() == today }) {
            println("Already checked in today.")
            return false
        }
        return super.add(element)
    }

    override fun toString(): String {
        return if (isEmpty()) "No records." else joinToString("\n") {
            val out = it.checkOutDateTime?.toString() ?: "N/A"
            "ID: ${it.employeeId} | IN: ${it.checkInDateTime} | OUT: $out"
        }
    }

    fun allByEmployee(empId: String): List<AttendanceData> {
        return filter { it.employeeId == empId }
    }
}

