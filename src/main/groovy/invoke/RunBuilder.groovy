package invoke

import concordance.ConcordanceCollect
import concordance.ConcordanceDef
import dsl4cc.DSLbuild.DSLBuilder

class RunBuilder {
  static void main(String[] args) {
    String workingDirectory = System.getProperty("user.dir")
    DSLBuilder builder = new DSLBuilder("$workingDirectory/src/main/groovy/clusterDSLfiles/concordanceBible21112", ConcordanceDef, ConcordanceCollect)
    assert builder.builder() : " Build Failed"
  }
}
