/*Your program will
• Read a large list of 5-letter words from a file and store them
• Pick a random word from this list
• Repeatedly prompt the user to guess the chosen word
• Give feedback on which letters have been guessed correctly*/

public 
public 

fun main() 
{   
    //Variable initialisation

    MutableList<String> wordList = readWordList("portfolio/wordle/data/words")
    String wordle = ""

}

fun readWordList(filename: String): MutableList<String>
{
    String file = File(filename+".txt").readText()
    output = file.split("\n").toMutableList()

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
    String word = words[Random.nextInt( 0 , words.size - 1)]
    
    return word
}

fun obtainGuess(attempt: Int): String
{
    println("Attempt"+attempt+":")  //Print attempt number
    String guess = readLine()

    while(!isValid(guess))  //Loop message and input
    {
        println("Try again.")
        guess = readLine()
    }
    
    return guess
}

fun evaluateGuess(guess: String, target: String): List<Int>
{
    MutableList evaluation = mutableListOf<Int>()

    for (i in 0..4) 
    {
        if(guess[i] == target[i])
        {
            evaluation[i] = 1
        }
        else
        {
            evaluation[i] = 0
        }
    }

    return evaluation
}

fun displayGuess(guess: String, matches: List<Int>)
{
    String output = ""

    for (i in 0..4) {

        if(matches[i] = 1)
        {
            output += guess[i]
        }
        else
        {
            output += "?"
        }

    }

    return output
}