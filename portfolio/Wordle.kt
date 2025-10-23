/*Your program will
• Read a large list of 5-letter words from a file and store them
• Pick a random word from this list
• Repeatedly prompt the user to guess the chosen word
• Give feedback on which letters have been guessed correctly*/

fun main() 
{   
    //Variable initialisation
    var noOfAttempts: Int = 0
    val wordList: MutableList<String> = readWordList("data/words.txt")  // Initialize list
    val wordle: String = pickRandomWord(wordList)                       // Target word
    var hasWon: Boolean = false


    for (attempts in 0..10) {

        var guess: String = obtainGuess(attempts)
        val matches: List<Int> = evaluateGuess(guess, wordle)
        displayGuess(guess, matches)

        if( matches.contains(0) )
        {
            println("Incorrect, try again.")
        }
        else
        {
            noOfAttempts = attempts
            hasWon = true       //Only necessary for the case when attempts = 10, 
            println("Correct!")
            break
        }
    }

    if(!hasWon)
    {
        println("You have run out of attempts and failed.")
    }
    
}

fun readWordList(filename: String): MutableList<String>
{
    val file: String = File(filename).readText()
    val output: MutableList<String> = file.split("\n").toMutableList()

    return output
}

fun isValid(word: String): Boolean
{
    if( word.length == 5 )
    {
        return true
    }

    return false
}
    
fun pickRandomWord(words: MutableList<String>): String
{
    val word: String = words[Random.nextInt( 0 , words.size - 1)]
    
    return word
}

fun obtainGuess(attempt: Int): String
{
    println("Attempt"+attempt+":")  //Print attempt number
    var guess: String = readLine() ?: ""

    while(!isValid(guess))  //Loop message and input
    {
        println("Try again.")
        guess = readLine() ?: ""
    }
    
    return guess
}

fun evaluateGuess(guess: String, target: String): List<Int>
{
    val evaluation: MutableList<Int> = mutableListOf<Int>()

    for (i in 0..4) 
    {
        if(guess[i] == target[i])
        {
            evaluation.add(1)
        }
        else
        {
            evaluation.add(0)
        }
    }

    return evaluation
}

fun displayGuess(guess: String, matches: List<Int>)
{
    var output: String = ""

    for (i in 0..4) {

        if(matches[i] == 1)
        {
            output += guess[i]
        }
        else
        {
            output += "?"
        }

    }

    println(output)
}
