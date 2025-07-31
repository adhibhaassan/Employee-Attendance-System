package operation

import data.EmployeeData
import data.Role

class EmployeeService {
    val employeeList = EmployeeManager()
    var idCounter = 1

    fun getEmployeeDetails(): String {
        print("First name: ")
        val firstName = readln().trim()
        if (firstName.isBlank() || firstName.any { it.isDigit() } ) {
            println("Invalid First Name.")
            return "-1"
        }

        print("Last name: ")
        val lastName = readln().trim()
        if (lastName.isBlank() || lastName.any { it.isDigit() } ) {
            println("Invalid Last Name.")
            return "-1"
        }

        println("Select Role:\n1. Intern | 2. Employee | 3. Manager\nChoose any one.")
        val roleInput = readlnOrNull()?.toIntOrNull()
        val role = when (roleInput) {
            1 -> Role.INTERN
            2 -> Role.EMPLOYEE
            3 -> Role.MANAGER
            else -> null
        }
        if (role == null) {
            println("Invalid role selected.")
            return "-1"
        }

        print("Reporting to (Manager ID): ")
        val reportingId = readln().trim()

        val id = generateEmployeeId(firstName, lastName)
        val employee = EmployeeData(id, firstName, lastName, role, reportingId)

        return if (employeeList.add(employee)) {
            idCounter++  // Increment only if add returns true
            id
        }
        else {
            "-1"
        }
    }

    fun generateEmployeeId(first: String, last: String): String {
        val initials = first.first().uppercaseChar().toString() + last.first().uppercaseChar().toString()
        val formattedCounter = "%04d".format(idCounter)
        return initials + formattedCounter
    }

    fun initializeManagers() {
        val predefinedManagers = listOf(
            Pair("Alice", "Johnson"),
            Pair("Bob", "Smith"),
            Pair("Carol", "Taylor"),
            Pair("David", "Williams"),
            Pair("Eve", "Brown"),
            Pair("Frank", "Jones"),
            Pair("Grace", "Garcia"),
            Pair("Hank", "Martinez"),
            Pair("Jack", "Miller")
        )
        for ((first, last) in predefinedManagers) {
            val id = generateEmployeeId(first, last)
            val manager = EmployeeData(id, first, last, Role.MANAGER, "0")
            employeeList.add(manager)
            idCounter++  // Manually increment for predefined only since they're always valid
        }
    }

    fun listEmployees(): List<EmployeeData> = employeeList

    fun isValidId(id: String): Boolean {
        return employeeList.any { it.id == id }
    }
}
