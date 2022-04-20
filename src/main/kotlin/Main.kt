import utils.ScannerInput

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
         > |   3) (custom method)|
         > |   4) Update a Police Officers                 |
         > |   5) Delete a Police Officers                 |
         > |   6) (custom method)               |
         > |   7) Search Police Officers                  |
         > --------------------------------------
         > |   8) Save Police Officers                   |
         > |   9) Load Police Officers                   |
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
            2 -> listOficer()
            3 -> println("custom method")
            4 -> updateOfficer()
            5 -> deleteOfficer()
            6 -> println("custom method")
            7 -> searchOfficer()
            8 -> saveOfficers()
            9 -> loadOfficers()
            0 -> exitApp()
            else -> println("Invalid option enteredL $option")
        }
    } while(true)
}

