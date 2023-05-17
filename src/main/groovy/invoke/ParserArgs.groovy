package invoke

import cluster_cli.parse.Parser
import jcsp.userIO.Ask

class ParserArgs {
  static void main(String[] args) {
    String parseFile
    if (args.size() == 0) parseFile = Ask.string("Full pathname of the .clic file to be parsed (excluding suffix)? : ")
    else parseFile = args[0]
    Parser parser = new Parser(parseFile )
    assert parser.parse(): "Parsing failed"
  }
}
