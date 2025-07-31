package operation
import data.EmployeeData
import data.Role

class EmployeeService {
    private val employeeList = EmployeeManager()
    private var idCounter = 1
    fun initializeManagers() {
        val predefinedManagers = listOf(
            Triple("Alice", "Johnson", 9876543210), //built-in data class holding 3 values
            Triple("Bob", "Smith", 9123456789),
            Triple("Carol", "Taylor", 9234567890),
            Triple("David", "Williams", 9345678901),
            Triple("Eve", "Brown", 9456789012),
            Triple("Frank", "Jones", 9567890123),
            Triple("Grace", "Garcia", 9678901234),
            Triple("Hank", "Martinez", 9789012345),
            Triple("Ivy", "Davis", 9890123456),
            Triple("Jack", "Miller", 9901234567)
        )

        for ((first, last, contact) in predefinedManagers) {
            val id = generateEmployeeId(first, last)
            val manager = EmployeeData(id, first, last, Role.MANAGER, contact, "0")
            employeeList.add(manager)
        }
    }

    private fun generateEmployeeId(first: String, last: String): String {
        val initials = first.first().uppercaseChar().toString() + last.first().uppercaseChar().toString()
        val formattedCounter = "%04d".format(idCounter++)
        return initials + formattedCounter
    }

    fun getEmployeeDetails(): String {
        print("First name: ")
        val firstName = readln().trim().replaceFirstChar { it.uppercase() }

        print("Last name: ")
        val lastName = readln().trim().replaceFirstChar { it.uppercase() }

        println("Select Role:\n1. Intern | 2. Employee | 3. Manager\nChoose any one.")
        val roleInput = readlnOrNull()?.toIntOrNull()
        val role = when (roleInput) {
            1 -> Role.INTERN
            2 -> Role.EMPLOYEE
            3 -> Role.MANAGER
            else -> {
                println("Invalid role selected.")
                return "-1"
            }
        }

        print("Contact number (10 digits): ")
        val contactInput = readln().trim()
        if (contactInput.length != 10 || contactInput.any { !it.isDigit() }) {
            println("Invalid contact number. It must be a 10-digit number.")
            return "-1"
        }
        val contactNumber = contactInput.toLong()

        print("Reporting to (Manager ID): ")
        val reportingId = readln().trim()
        if (!isValidManagerId(reportingId)) {
            println("Invalid manager ID. Please enter a valid manager's ID.\nCheck the employee list.")
            return "-1"
        }

        val id = addEmployee(firstName, lastName, role, contactNumber, reportingId)
        println("Employee added with ID: $id")
        return id
    }

    fun addEmployee(first: String, last: String, role: Role, contact: Long, reportTo: String): String {
        val id = generateEmployeeId(first, last)
        val employee = EmployeeData(id, first, last, role, contact, reportTo)
        if (employeeList.add(employee)) {
            return id
        }
        return "-1"
    }

    fun listEmployees(): List<EmployeeData> = employeeList

    fun isValidId(id: String): Boolean {
        return employeeList.any { it.id == id }
    }

    fun isValidManagerId(id: String): Boolean {
        return employeeList.any { it.id == id && it.role == Role.MANAGER }
    }
}
