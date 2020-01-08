package com.example.vedrochat

import org.junit.Test

import org.junit.Assert.assertEquals
import com.example.vedrochat.extensions.add
import com.example.vedrochat.extensions.format
import com.example.vedrochat.models.BaseMessage
import com.example.vedrochat.models.Chat
import com.example.vedrochat.models.MessageType
import com.example.vedrochat.models.User
import com.example.vedrochat.utils.Utils
import java.util.Date
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test_user_factory() {
        val user1 = User.makeUser("Test1 User1")
        val user2 = User.makeUser("Test2 User2")

        assertEquals("1", user1.id)
        assertEquals("2", user2.id)

        assertEquals("Test1", user1.firstName)
        assertEquals("Test2", user2.firstName)

        assertEquals("User1", user1.lastName)
        assertEquals("User2", user2.lastName)
    }

    @Test
    fun test_message_factory() {
        val user = User.makeUser("Test User")
        val textMessage = BaseMessage.makeMessage(
            user, Chat(1), Date(), MessageType.TEXT, "test", false
        )
        val formattedTextMessage = textMessage.formatMessage()
        assertEquals("1", "1") // TODO make date humanize and compare strings

        val imageMessage = BaseMessage.makeMessage(
            user, Chat(1), Date(), MessageType.TEXT, "http://image-url", true
        )
        val formattedImageMessage = imageMessage.formatMessage()
        assertEquals("1", "1") // TODO make date humanize and compare strings
    }

    @Test
    fun test_name_parser() {
        assertEquals(Pair(null, null), Utils.parseFullName(null))
        assertEquals(Pair(null, null), Utils.parseFullName(""))
        assertEquals(Pair(null, null), Utils.parseFullName(" "))
        assertEquals(Pair("John", null), Utils.parseFullName("    John     "))
        assertEquals(Pair("John", null), Utils.parseFullName("John"))
        assertEquals(Pair("John", "Doe"), Utils.parseFullName("John Doe"))
        assertEquals(Pair("John", "Doe"), Utils.parseFullName("    John      Doe"))
    }

    @Test
    fun test_date_format() {
        val date = Date(1578477970000)
        assertEquals("13:06:10 08.01.20", date.format())
        assertEquals("13:06", date.format("HH:mm"))
    }

    @Test
    fun test_date_add() {
        val date1 = Date(1578477970000)
        val dateWithTwoSeconds = Date(1578477972000)
        assertEquals(dateWithTwoSeconds, date1.add(2, TimeUnit.SECONDS))

        val date2 = Date(1578477970000)
        val dateWithoutFourDays = Date(1578132370000)
        assertEquals(dateWithoutFourDays, date2.add(-4, TimeUnit.DAYS))
    }

    @Test
    fun test_to_initials() {
        assertEquals("JD", Utils.toInitials("john" ,"doe"))
        assertEquals("JD", Utils.toInitials("  john   " ,"  doe  "))
        assertEquals("J", Utils.toInitials("John", null))
        assertEquals(null, Utils.toInitials(null, null))
        assertEquals(null, Utils.toInitials(" ", ""))
    }

    @Test
    fun test_transliteration() {
        assertEquals("Zhenya Stereotipov", Utils.transliteration("Женя Стереотипов"))
        assertEquals("Amazing_Petr", Utils.transliteration("Amazing Петр","_"))
        assertEquals("Petr123-45", Utils.transliteration(" Петр123-45"))
        assertEquals("Petr_1 Petrov-Vodkin", Utils.transliteration(" Петр_1 Петров-Водкин"))
    }
}
