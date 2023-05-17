package sequentialTest

import concordance.ConcordanceCollect
import concordance.ConcordanceDef

int startN = 1
int finalN = 8
int minSeqLen = 2
String sourceFileName = "Shakespeare"  //ACM, bible, Bunyan, Shakespeare
String inputFilePath =  "D:/IJGradle/ClusterConcordance/src/main/groovy/testFiles/"
String inFileName = inputFilePath + sourceFileName + ".txt"
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
  cd = concordanceDef.create()
} // while
cc.finalise(["./${sourceFileName}prnSeq.txt", 2])
endTime = System.currentTimeMillis()
println "elapsed time = ${endTime - startTime}"
