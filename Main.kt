import operation.EmployeeService
import operation.AttendanceService

fun main() {
    val empService = EmployeeService()
    empService.initializeManagers()
    val attService = AttendanceService()

    while (true) {
        println("\n1. Add Employee\n2. List Employees\n3. Check-In\n4. Check-Out\n5. View Attendance\n6. Exit")
        print("Enter choice: ")
        when (readln().toIntOrNull()) {
            1 -> addEmployee(empService)
            2 -> listEmployees(empService)
            3 -> println(attService.handleCheckIn(empService))
            4 -> attService.checkOutEmployee()
            5 -> viewAttendance(attService, empService)
            6 -> break
            else -> println("Invalid choice.")
        }
    }
}

fun addEmployee(empService: EmployeeService) {
    val id = empService.getEmployeeDetails()
    if (id != "-1") println("Employee added: $id")
}

fun listEmployees(empService: EmployeeService) {
    println(empService.listEmployees())
}

fun viewAttendance(attService: AttendanceService, empService: EmployeeService) {
    val list = attService.getIdForAttendance(empService)
    if (list == null) {
        println("Invalid ID.")
    }
    else {
        println(
            if (list.isEmpty()) "No records." else list.joinToString("\n")
        )
    }
}

