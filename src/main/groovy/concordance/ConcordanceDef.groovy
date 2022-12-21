package concordance

import dsl4cc.DSLrecords.EmitInterface

class ConcordanceDef implements  EmitInterface<ConcordanceDef>, Serializable{

  // properties created using initial constructor
  int initialStringLength, finalStringLength
  String inputFileName
  Boolean initialised
  int currentStringLength

  ConcordanceDef(List params){
    initialStringLength = params[0]
    finalStringLength = params[1]
    inputFileName = params[2]
    initialised = false
    currentStringLength = initialStringLength
  }

  // properties used in constructor within create method

  int strLen
  def wordBuffer = new ArrayList(10000)	// the words, punctuation removed, in the text
  def intValueList = new ArrayList(10000)	// the integer value associated with each word
  int wordCount = 0						// number of words in text

  ConcordanceDef(ArrayList wordBuffer,
                  ArrayList intValueList,
                  int wordCount,
                  int stringLength){
    this.wordBuffer = wordBuffer
    this.intValueList = intValueList
    this.wordCount = wordCount
    this.strLen = stringLength
  }

  /**
   * endPunctuationList the list of punctuation symbols
   * that can be removed from the ends of words
   */
  def endPunctuationList =[',','.',';',':','?','!', '\'', '"', '_', '}', ')']

  /**
   * startPunctuationList the list of punctuation symbols
   * that can be removed from the start of words
   */
  def startPunctuationList = ['\'' ,'"', '_', '\t', '{', '(']

  /**
   * processLine takes a line of text
   * which is split into words using tokenize(' ')
   *
   * @param line a line that has been read from a file
   * @return list of words containing each word in the line
   */
  List <String> processLine (String line){
    List <String> words = line.tokenize(' ')
    return words
  }

  /**
   *
   * removePunctuation removes any punctuation characters
   * from the start and end of a word
   * @param w String containing the word to be processed
   * @return String rw containing the input word less any punctuation symbols
   *
   */
  String removePunctuation(String w) {
    String ew = w
    String rw
    int len = w.size()
    if ( len == 1 )
      rw = w
    else {
      String lastCh = w.substring(len-1, len)
      while (endPunctuationList.contains(lastCh)){
        ew = w.substring(0, len-1)
        len = len - 1
        if ( len > 0)
          lastCh = ew.substring(len-1, len)
        else {
          lastCh = ' '
          ew = ' '
        }
      }
      String firstCh = ew.substring(0, 1)
      if ( startPunctuationList.contains(firstCh) ) {
        rw = w.substring(1, len)
      }
      else {
        rw = ew
      }
    }
    return rw
  }

  /**
   * charSum transforms a word into a single integer based upon the
   * sum of the ASCII characters that make up the word
   *
   * @param w String containing word to be transformed into an integer
   * @return int containing the integer equivalent of word
   */
  int charSum(w) {
    int sum = 0
    StringBuffer wbuff = new StringBuffer(w)
    int len = wbuff.length()
    for ( i in 0..< len) {
      sum = sum + (int) wbuff[i]
    }
    return sum
  }

  @Override
  ConcordanceDef create() {
    if (!initialised) {
      // read in the file, remove punctuation and calculate each single word character sum
      def fileHandle = new File(inputFileName)
      def fileReader = new FileReader(fileHandle)
      fileReader.eachLine { line ->
        List<String> words = processLine(line)
//      println "$words"
        for (w in words) {
          wordBuffer << removePunctuation(w)
          intValueList << charSum(wordBuffer[wordCount])
          wordCount = wordCount + 1
        }
      }
      initialised = true
      println "Emit has processed $inputFileName with $wordCount words"
    }
    if (currentStringLength <= finalStringLength) {
      ConcordanceDef cd = new ConcordanceDef(wordBuffer, intValueList, wordCount, currentStringLength)
      currentStringLength++
      return cd
    } else return null
  }

  // calculated properties during processing
  def sequenceList = new ArrayList(10000)	// integer value for each sequence of length strLen
  def valueIndicesMap = [:]		// for each distinct value (key) in sequenceList
  // the indices as a list of its location (value)
  def wordMap = [:]		// map of words (key) and list of indices (value)

  void createIntValueList () {
    sequenceList = []
    int partSum = 0
    for (w in 0..wordCount-strLen) {
      for ( i in 0..< strLen) partSum = partSum + intValueList[w + i]
      sequenceList << partSum
      partSum = 0
    }
//		println "\tcreated sequenceList with strLen = $strLen"
  } //createIntValueList

  void createValueIndicesMap () {
    def index = 0
    def indexList = []
    valueIndicesMap = [:]
    for ( v in sequenceList){
      indexList = valueIndicesMap.get (v, [])
      indexList << index
      valueIndicesMap.put (v, indexList)
      index = index + 1
    }
//		println "\t\tcreated valueIndicesMap with strLen = $strLen"
  } //createValueIndicesMap

  void createWordMap () {
    def sequenceValues = valueIndicesMap.keySet()
    def wordKeyList = []
    def indexList = []
    def wordMapEntry = []
    wordMap = [:]
    for ( sv in sequenceValues){
      indexList = valueIndicesMap.get(sv)
      wordMapEntry = []
      for ( il in indexList){
        wordKeyList = []
        for ( w in 0..(strLen-1)) wordKeyList << wordBuffer[il + w]
        wordMapEntry = wordMap.get (wordKeyList, [])
        wordMapEntry << il
        wordMap.put (wordKeyList, wordMapEntry)
      }
    }
//		println "\t\t\tcreated wordMap with strLen = $strLen"
  } //createWordMap

  String toString(){
    return "words:$wordCount, string length: $strLen, " +
        "sequenceList size: ${sequenceList.size()}, " +
        "valueIndicesMap size: ${valueIndicesMap.size()}, " +
        "wordMap size: ${wordMap.size()}"
  }

}
