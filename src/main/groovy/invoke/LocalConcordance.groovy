package invoke

import concordance.ConcordanceCollect
import concordance.ConcordanceDef
import cluster_cli.run.HostRun

class LocalConcordance {
  static void main(String[] args) {
    String structureFile = "D:\\IJGradle\\ClusterConcordance\\src\\main\\groovy\\localDSLfiles\\localACM1n1w"
    Class  emitClass = ConcordanceDef
    Class collectClass = ConcordanceCollect
    new HostRun(structureFile, emitClass, collectClass, "Local").invoke()

  }
}
