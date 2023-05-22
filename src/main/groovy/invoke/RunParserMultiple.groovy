package invoke

import cluster_cli.parse.Parser

class RunParserMultiple {
  static void main(String[] args) {
    String workingDirectory = System.getProperty("user.dir")
    for (n in 1..2)
      for (w in [1,2]){
        Parser parser = new Parser("$workingDirectory/src/main/groovy/localDSLfiles/localACM${n}n${w}w")
        assert parser.parse() :"Parsing failed"
//        parser = new Parser("$workingDirectory/src/main/groovy/clusterDSLfiles/netBunyann${n}w${w}")
//        assert parser.parse() :"Parsing failed"
//        parser = new Parser("$workingDirectory/src/main/groovy/clusterDSLfiles/netShakespearen${n}w${w}")
//        assert parser.parse() :"Parsing failed"
      }
  }

}
