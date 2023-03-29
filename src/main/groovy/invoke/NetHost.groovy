package invoke

import cluster_cli.run.HostRun
import concordance.ConcordanceCollect
import concordance.ConcordanceDef
import jcsp.userIO.Ask

class NetHost {
  static void main(String[] args) {
    String structureFile
    if (args.size() == 0)
      structureFile = Ask.string("Full pathname of the structure file? : ")
    else
      structureFile = args[0]
    new HostRun(structureFile, ConcordanceDef, ConcordanceCollect).invoke()
  }
}
