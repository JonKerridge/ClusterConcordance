package concordance

import dsl4cc.DSLrecords.CollectInterface

class ConcordanceCollect implements CollectInterface<ConcordanceDef> {
  @Override
  void collate(ConcordanceDef concordanceDef, List list) {
    String concordanceEntry
    concordanceEntry = " "
    concordanceDef.wordMap.each {
      concordanceEntry = " "
      if (it.value.size() >= 2) {
        concordanceEntry = concordanceEntry + it.key + ", "
        concordanceEntry = concordanceEntry + it.value.size() + ", "
        concordanceEntry = concordanceEntry + it.value
        println "$concordanceEntry"
      }
    }
    println "\nEnd of ${concordanceDef.strLen}\n"

  }

  @Override
  void finalise(List list) {
    println "Collecting finished"
  }
}
