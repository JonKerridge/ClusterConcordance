package invoke

import concordance.ConcordanceDef
import org.junit.Test

class ResultCheck {
  @Test
  public void test(){
    int nodes = 1
    int collectors = 1
    int minValue, maxValue
    minValue = 501
    maxValue = 900
    List <String> fileNames = []
    for ( n in 0 ..< nodes)
      for ( c in 0 ..< collectors)
        fileNames << "C:/RunJars/clic/concordanceACM-${n}-${c}.cliCout"
    println "Files are $fileNames"
    fileNames.each { String name ->
      println "$name"
      List retrieved = []
      File objFile = new File(name)
      objFile.withObjectInputStream { inStream ->
        inStream.eachObject { ConcordanceDef cd ->
          String concordanceEntry
          concordanceEntry = " "
          cd.wordMap.each {
            concordanceEntry = " "
            if (it.value.size() >= 2) {
              concordanceEntry = concordanceEntry + it.key + ", "
              concordanceEntry = concordanceEntry + it.value.size() + ", "
              concordanceEntry = concordanceEntry + it.value
              println "$concordanceEntry"
            }
          }
          println "\nEnd of ${cd.strLen}\n"
        }
        inStream.close()
      } // inStream
      println "$retrieved"
    } // filenames
  } // test
}
