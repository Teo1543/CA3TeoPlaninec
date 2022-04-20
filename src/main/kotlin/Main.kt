import controllers.OfficerAPI
import models.Officer
import persistence.JSONSerializer
import persistence.XMLSerializer
import utils.ScannerInput
import utils.ScannerInput.readNextDouble
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import java.io.File
import java.lang.System.exit


private val officerAPI = OfficerAPI(JSONSerializer(File("officers.json")))

fun main(args: Array<String>) {
    runMenu()
}


fun mainMenu(): Int {
    return ScannerInput.readNextInt(
        """ 
         > --------------------------------------
         > |       Police Officers APP             |
         > --------------------------------------
         > | NOTE MENU                          |
         > |   1) Add a Police Officers                    |
         > |   2) List Police Officers                    |
         > |   3) Officer Type
         > |   4) Update a Police Officers                 |
         > |   5) Delete a Police Officers                 |
         > |   6) List Crime Number              |
         > |   7) Search Police Officers 
         >     8) List solved crime        
         >     9) Officers With Higher solved crimes     |
         > --------------------------------------
         > |   10) Save Police Officers                   |
         > |   11) Load Police Officers                   |
         > |   0) Exit                          |
         > --------------------------------------
         > ==>> """.trimMargin(">")
    )
}

fun runMenu() {
    do {
        val option = mainMenu()
        when(option) {
            1 -> addOfficer()
            2 -> listOfficer()
            3 -> officerType()
            4 -> updateOfficer()
            5 -> deleteOfficer()
            6 -> listCrimeNumber()
            7 -> searchOfficer()
            8 -> unsolvedCrimes()
            9 -> officersWithHigherSolvedCrimes()
            10 ->  save()
            11 -> load()
            0 -> exitApp()
            else -> println("Invalid option entered $option")
        }
    } while(true)
}

fun addOfficer() {
    var name = readNextLine("Enter name of Officer: ")
    var dateOfBirth = readNextLine("Enter date of birth of Officer: ")
    var type = readNextLine("Enter Officer type: ")
    var crimesSolved = readNextInt("Enter crimes solved: ")
    var crimesUnsolved = readNextInt("Enter Crimes unsolved: ")
    var salary = readNextDouble("Enter salary: ")
    var peopleSaved = readNextInt("Enter people saved: ")
    val isAdded = officerAPI.add(Officer(name, dateOfBirth, type, crimesSolved, crimesUnsolved, salary, peopleSaved))

    if(isAdded) {
        println("Added successfully")
    } else {
        println("Add failed")
    }
}

fun listOfficer() {
    var officers = officerAPI.listAllOfficers()

    println(officers)
}

fun officerType() {
    var type = readNextLine("Enter officer type: ")
    var officers = officerAPI.officerTypeFinder(type)

    println(officers)
}

fun updateOfficer() {
    listOfficer()
    if(officerAPI.numberOfficers() > 0) {
        val indexToUpdate = readNextInt("Enter the index of the officer to updateL ")
        if(officerAPI.isValidIndex(indexToUpdate)) {
            val name = readNextLine("Enter name: ")
            val dateOfBirth = readNextLine("Enter date of birth: ")
            val type = readNextLine("Enter officer type: ")
            val crimeSolved = readNextInt("Enter crimes solved: ")
            val unsolvedCrimes = readNextInt("Enter unsolved crimes: ")
            val salary = readNextDouble("Enter salary: ")
            val peopleSaved = readNextInt("Enter people saved: ")

            if(officerAPI.updateOfficer(indexToUpdate, Officer(name, dateOfBirth, type, crimeSolved, unsolvedCrimes, salary, peopleSaved))) {
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        }
    }
}

fun deleteOfficer() {
    listOfficer()
    if(officerAPI.numberOfficers() > 0) {
        val indexToUpdate = readNextInt("Enter the index of the Officer to delete: ")

        val officerToDelete = officerAPI.deleteOfficer(indexToUpdate)
        if(officerToDelete != null) {
            println("Delete Successful! Deleted Officer: ${officerToDelete.name}")
        } else {
            println("Delete NOT Successful")
        }
    }
}


fun searchOfficer() {
    var type = readNextLine("Enter officer type: ")

   var officers = officerAPI.officerTypeFinder(type)

    println(officers)
}

fun listCrimeNumber() {
    var number = readNextInt("Enter crime number: ")
   var officers = officerAPI.listCrimeByNumber(number)
    println(officers)
}

fun unsolvedCrimes() {
    println("Crime level 5 or more")
    println(officerAPI.listUnsolvedCrime())
}

fun officersWithHigherSolvedCrimes() {
    println(officerAPI.officersWithHigherSolvedCrimes());
}

fun exitApp() {
    println("Exiting...bye")
    exit(0)
}

fun save() {
    try {
        officerAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun load() {
    try {
        officerAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}



