package invoke

import dsl4cc.DSLparse.DSLParser

class RunParser {
  static void main(String[] args) {
    String workingDirectory = System.getProperty("user.dir")
    DSLParser parser = new DSLParser("$workingDirectory/src/main/groovy/clusterDSLfiles/concordanceBible21112")
    assert parser.parse() :"Parsing failed"
  }

}
