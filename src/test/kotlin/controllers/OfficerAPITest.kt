package controllers

import models.Officer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import persistence.XMLSerializer
import java.io.File
import kotlin.test.assertEquals

class OfficerAPITest {



    private var officer1: Officer? = null
    private var officer2: Officer? = null
    private var officer3: Officer? = null
    private var officer4: Officer? = null
    private var officer5: Officer? = null



    private var populatedOfficers: OfficerAPI? = OfficerAPI(XMLSerializer(File("officers.xml")))
    private var emptyOfficers: OfficerAPI? = OfficerAPI(XMLSerializer(File("officers.xml")))


    @BeforeEach
    fun setup(){
        officer1 = Officer("Maciej", "20/05/2005", "Junior", 5, 10, 10000.00, 5)
        officer2 = Officer("Zach", "01/07/2000", "Senior", 18, 13, 20000.00, 8)
        officer3 = Officer("Dom", "20/12/2010", "Junior", 7, 5, 30000.00, 2)
        officer4 = Officer("Tristan", "15/08/2001", "Expert", 30, 18, 50000.00, 15)
        officer5 = Officer("Teo", "25/09/2001", "Veteran", 28, 15, 60000.00, 12)

        //adding 5 Note to the notes api
        populatedOfficers!!.add(officer1!!)
        populatedOfficers!!.add(officer2!!)
        populatedOfficers!!.add(officer3!!)
        populatedOfficers!!.add(officer4!!)
        populatedOfficers!!.add(officer5!!)
    }

    @AfterEach
    fun tearDown(){
        officer1 = null
        officer2 = null
        officer3 = null
        officer4 = null
        officer5 = null

        populatedOfficers = null
        emptyOfficers = null
    }







    @Nested

    inner class PersistenceTests {

        @Test
        fun `saving and loading an empty collection in XML doesn't crash app`() {
            // Saving an empty notes.XML file.
            val storingOfficers = OfficerAPI(XMLSerializer(File("officers.xml")))
            storingOfficers.store()

            //Loading the empty notes.xml file into a new object
            val loadedOfficers = OfficerAPI(XMLSerializer(File("officers.xml")))
            loadedOfficers.load()

            //Comparing the source of the notes (storingNotes) with the XML loaded notes (loadedNotes)
            assertEquals(0, storingOfficers.numberOfficers())
           populatedOfficers!!.clear()

            assertEquals(storingOfficers.numberOfficers(), populatedOfficers?.numberOfficers())
        }

        @Test
        fun `saving and loading an loaded collection in XML doesn't loose data`() {
            // Storing 3 notes to the notes.XML file.
            val storingOfficers = OfficerAPI(XMLSerializer(File("notes.xml")))
            storingOfficers.add(officer1!!)
            storingOfficers.add(officer2!!)
            storingOfficers.add(officer3!!)
            storingOfficers.store()

            //Loading notes.xml into a different collection
            val loadedOfficers = OfficerAPI(XMLSerializer(File("notes.xml")))
            loadedOfficers.load()

            //Comparing the source of the notes (storingNotes) with the XML loaded notes (loadedNotes)
            assertEquals(3, storingOfficers.numberOfficers())
            assertEquals(3, loadedOfficers.numberOfficers())
            assertEquals(storingOfficers.numberOfficers(), loadedOfficers.numberOfficers())
            assertEquals(storingOfficers.findOfficer(0), loadedOfficers.findOfficer(0))
            assertEquals(storingOfficers.findOfficer(1), loadedOfficers.findOfficer(1))
            assertEquals(storingOfficers.findOfficer(2), loadedOfficers.findOfficer(2))
        }
    }




    @Test
    fun `saving and loading an empty collection in JSON doesn't crash app`() {
        // Saving an empty notes.json file.
        val storingOfficers = OfficerAPI(JSONSerializer(File("officers.json")))
        storingOfficers.store()

        //Loading the empty notes.json file into a new object
        val loadedNotes = OfficerAPI(JSONSerializer(File("officers.json")))
        loadedNotes.load()

        //Comparing the source of the notes (storingNotes) with the json loaded notes (loadedNotes)
        assertEquals(0, storingOfficers.numberOfficers())
        assertEquals(0, storingOfficers.numberOfficers())
        assertEquals(storingOfficers.numberOfficers(), storingOfficers.numberOfficers())
    }

    @Test
    fun `saving and loading an loaded collection in JSON doesn't loose data`() {
        // Storing 3 notes to the notes.json file.
        val storingOfficers = OfficerAPI(JSONSerializer(File("officers.json")))
        storingOfficers.add(officer1!!)
        storingOfficers.add(officer2!!)
        storingOfficers.add(officer3!!)
        storingOfficers.store()

        //Loading notes.json into a different collection
        val loadedOfficers = OfficerAPI(JSONSerializer(File("officers.json")))
        loadedOfficers.load()

        //Comparing the source of the notes (storingNotes) with the json loaded notes (loadedNotes)
        assertEquals(3, storingOfficers.numberOfficers())
        assertEquals(3, loadedOfficers.numberOfficers())
        assertEquals(storingOfficers.numberOfficers(), loadedOfficers.numberOfficers())
        assertEquals(storingOfficers.findOfficer(0), loadedOfficers.findOfficer(0))
        assertEquals(storingOfficers.findOfficer(1), loadedOfficers.findOfficer(1))
        assertEquals(storingOfficers.findOfficer(2), loadedOfficers.findOfficer(2))
    }

}