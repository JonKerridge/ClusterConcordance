package invoke

import cluster_cli.parse.Parser

class RunParser {
  static void main(String[] args) {
    String workingDirectory = System.getProperty("user.dir")
    Parser parser = new Parser("$workingDirectory/src/main/groovy/localDSLfiles/localBible11121" )
    assert parser.parse() :"Parsing failed"
  }

}
