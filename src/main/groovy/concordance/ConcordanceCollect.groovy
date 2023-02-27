package concordance

import cluster_cli.records.CollectInterface

class ConcordanceCollect implements CollectInterface<ConcordanceDef> {
  @Override
  void collate(ConcordanceDef concordanceDef, List list) {
    // does nothing apart from print the string length of object processed
    // the data will be held in the output file created by the collect process
    println "\nEnd of ${concordanceDef.strLen}\n"

  }

  @Override
  void finalise(List list) {
    println "Collecting finished"
  }
}
