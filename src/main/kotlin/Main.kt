import utils.ScannerInput

fun main(args: Array<String>) {

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
         > |   20) Save Police Officers                   |
         > |   21) Load Police Officers                   |
         > |   0) Exit                          |
         > --------------------------------------
         > ==>> """.trimMargin(">")
    )
}