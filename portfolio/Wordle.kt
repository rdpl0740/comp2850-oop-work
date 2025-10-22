/*Your program will
• Read a large list of 5-letter words from a file and store them
• Pick a random word from this list
• Repeatedly prompt the user to guess the chosen word
• Give feedback on which letters have been guessed correctly*/
public MutableList<String> wordList = mutableListOf<string>()

fun main() 
{
    wordList = readWordList("portfolio/wordle/data/words")
    
}

fun readWordList(filename: String): MutableList<String>
{
    String file = File(filename+".txt").readText()
    output = file.split("\n").toMutableList()

    return output
}

fun isValid(word: String): Boolean
{
    if(wordList.contains(word))
    {
        return true
    }
    
    return false
}
    
