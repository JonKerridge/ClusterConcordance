package sequentialTest

import concordance.ConcordanceCollect
import concordance.ConcordanceDef

int startN = 1
int finalN = 8
int minSeqLen = 2
//String sourceFileName = "bible"  //ACM, bible, JohnBunyan, Shakespeare
//String inputFilePath =  "D:/IJGradle/ClusterConcordance/src/main/groovy/testFiles/"
String inFileName = "./ACM.txt"
//String printFileName = inputFilePath + sourceFileName + ".prn"
//File printFile = new File(printFileName)
//printWriter = printFile.newPrintWriter()
long startTime, endTime
startTime = System.currentTimeMillis()
ConcordanceDef concordanceDef = new ConcordanceDef([startN, finalN, inFileName])
ConcordanceDef cd = concordanceDef.create()
ConcordanceCollect cc = new ConcordanceCollect()
while (cd != null){
  cd.createIntValueList()
  cd.createValueIndicesMap()
  cd.createWordMap()
  cc.collate(cd, [])
//  printWriter.println "\n\nOutput for sequences of ${cd.strLen} words"
  println "\n\nOutput for sequences of ${cd.strLen} words"
  String concordanceEntry = " "
  cd.wordMap.each {
    concordanceEntry = " "
    if (it.value.size() >= minSeqLen) {
      concordanceEntry = concordanceEntry + it.key + ", "
      concordanceEntry = concordanceEntry + it.value.size() + ", "
      concordanceEntry = concordanceEntry + it.value
//      printWriter.println "$concordanceEntry"
      println "$concordanceEntry"
    }
  }
//  printWriter.println "\nEnd of ${cd.strLen}"
  println "\nEnd of ${cd.strLen}"
  cd = concordanceDef.create()
  } // while
//  printWriter.flush()
//  printWriter.close()
endTime = System.currentTimeMillis()
println "elapsed time = ${endTime - startTime}"
