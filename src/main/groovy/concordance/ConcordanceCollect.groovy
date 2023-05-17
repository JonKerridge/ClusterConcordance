package concordance

import cluster_cli.records.CollectInterface

class ConcordanceCollect implements CollectInterface<ConcordanceDef> {

  def collatedMap = [:]
  @Override
  void collate(ConcordanceDef concordanceDef, List list) {
    concordanceDef.wordMap.each{wordKeyList, wordMapEntry ->
      collatedMap.put(wordKeyList, wordMapEntry)
    }
  }

  @Override
  void finalise(List param) {
    String fileName = param[0]
    int minSeqLen = param[1]
    def printWriter = new PrintWriter(fileName)
    collatedMap.each{wordKeyList, wordMapEntry ->
      String concordanceEntry = " "
      if (wordMapEntry.size() >= minSeqLen) {
        concordanceEntry = concordanceEntry + wordKeyList + ", "
        concordanceEntry = concordanceEntry + wordMapEntry.size() + ", "
        concordanceEntry = concordanceEntry + wordMapEntry
        printWriter.println(concordanceEntry)
      }
    }
    printWriter.flush()
    printWriter.close()
  }
}
