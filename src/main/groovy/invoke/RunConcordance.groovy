package invoke

import concordance.ConcordanceCollect
import concordance.ConcordanceDef
import cluster_cli.run.HostRun

class RunConcordance {
  static void main(String[] args) {
    String structureFile = "D:\\IJGradle\\ClusterConcordance\\src\\main\\groovy\\clusterDSLfiles\\concordance"
    Class  emitClass = ConcordanceDef
    Class collectClass = ConcordanceCollect
    new HostRun(structureFile, emitClass, collectClass, "Local").invoke()

  }
}
