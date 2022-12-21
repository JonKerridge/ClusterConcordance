package concordance

class CheckResults {
  static void main(String[] args) {
    String testName = "bible"
    int collectors = 2
    List <String> fileNames = []
    int minSeqLen = 2
    for ( c in 0 ..< collectors) fileNames << "./${testName}results${c}.dsl4ccout"
    fileNames.each { String name ->
      println "$name"
      File objFile = new File(name)
      objFile.withObjectInputStream { inStream ->
        inStream.eachObject { ConcordanceDef cd ->
          String concordanceEntry = " "
          cd.wordMap.each {
            concordanceEntry = " "
            if (it.value.size() >= minSeqLen) {
              concordanceEntry = concordanceEntry + it.key + ", "
              concordanceEntry = concordanceEntry + it.value.size() + ", "
              concordanceEntry = concordanceEntry + it.value
              println "$concordanceEntry"
            }
          }
          println "\nEnd of ${cd.strLen}\n"
        }
      }
    }
  }
}
