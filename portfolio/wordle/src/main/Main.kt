fun main() 
{   
    
    //Variable initialisation
    val wordList: MutableList<String> = readWordList("wordle/data/words.txt")  // Initialize list
    val wordle: String = pickRandomWord(wordList)                       // Target word
    var hasWon: Boolean = false
    println(wordle)

    for (attempts in 1..10) {

        
        var guess: String = obtainGuess(attempts)
        val matches: List<Int> = evaluateGuess(guess, wordle)
        println(matches)
        displayGuess(guess, matches)

        if( matches.contains(0) )
        {
            println("Incorrect, try again.")
        }
        else
        {
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