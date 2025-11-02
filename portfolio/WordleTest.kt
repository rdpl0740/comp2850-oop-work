import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow
import java.io.ByteArrayOutputStream
import java.io.ByteArrayInputStream

import java.io.PrintStream

val WordleTest = StringSpec({

    "isValid should only return true when 5 characters are input" {
        isValid("comps") shouldBe true
        isValid("TAMED") shouldBe true
        isValid("sang") shouldBe false
        isValid("AaAAaAAAAAAAAAAAAAAAAAAaAaA") shouldBe false
        isValid("     ") shouldBe true
        isValid("") shouldBe false
    }

    "pickRandomWord should always return the entirety of a word from the specified word list" {
        pickRandomWord(mutableListOf("Apple", "Apple")) shouldBe "Apple"
        pickRandomWord(mutableListOf("Apple", "     ", "Silly")).length shouldBe 5
    }

    "obtainGuess should print correct number of attempts"{
        val outputStream = ByteArrayOutputStream()
        val preOutStream = System.out
        System.setOut(PrintStream(outputStream))
        var output = outputStream.toString().trim()
        
        //Test 1
        for (i in 0..9) {
            outputStream.reset()

            obtainGuess(i)
            output = outputStream.toString().trim()
            output shouldBe "Attempt"+i+":"
        }

        //Restore system output stream
        System.setOut(preOutStream)
    }

    "obtainGuess should print 'Try again.' on invalid input"{
        val preOutStream = System.out
        val preInpStream = System.`in`

        val outputStream = ByteArrayOutputStream()
        val testInp1 = ByteArrayInputStream("Brazen".toByteArray())
        val testInp2 = ByteArrayInputStream("Red".toByteArray())
        val testInp3 = ByteArrayInputStream("Press".toByteArray())

        System.setOut(PrintStream(outputStream))
        System.setIn(testInp1)
        obtainGuess(0)
        var output = outputStream.toString().trim()
        
        output = outputStream.toString().trim()
        output shouldBe "Try again."

        outputStream.reset()

        System.setIn(testInp2)
        output = outputStream.toString().trim()
        output shouldBe "Try again."

        outputStream.reset()

        System.setIn(testInp3)
        output = outputStream.toString().trim()
        outputStream.toString().isBlank() shouldBe true
        outputStream.reset()

        System.setOut(preOutStream)
        System.setIn(preInpStream)
        
    }

    "evaluateGuess should read 1 on overlapping matching words, and 0 on separate words"{
        evaluateGuess("slime", "SLIME") shouldBe mutableListOf(0,0,0,0,0)
        evaluateGuess("reads", "beads") shouldBe mutableListOf(0,1,1,1,1)
        evaluateGuess("CREAM", "CREAM") shouldBe mutableListOf(1,1,1,1,1)
    }

    "displayGuess should only show correctly guessed letters"{
        val preOutStream = System.out
        val outputStream = ByteArrayOutputStream()

        System.setOut(outputStream)

        //Test 1
        displayGuess("ABCDE", mutableListOf(0,0,0,0,0))
        var output = outputStream.toString().trim()
        output shouldBe "?????"

        outputStream.reset()
        
        //Test 1
        displayGuess("ABCDE", mutableListOf(1,0,1,0,1))
        output = outputStream.toString().trim()
        output shouldBe "A?C?E"

        outputStream.reset()

        //Test 3
        displayGuess("ABCDE", mutableListOf(1,1,1,1,1))
        output = outputStream.toString().trim()
        output shouldBe "ABCDE"
        
        outputStream.reset()

        System.setOut(preOutStream)
    }
})