package invoke

class RunNode {
  static void main(String[] args) {
    switch (args.size()){
      case 1:
        new dsl4cc.DSLInvoke.RunNode( args[0]).invoke()
        break
      case 2:
        new dsl4cc.DSLInvoke.RunNode( args[0], args[1]).invoke()
        break
      default:
        println "Run Node usage" +
            "\n new dsl4cc.DSLInvoke.RunNode( args[0]).invoke()" +
            "\n new dsl4cc.DSLInvoke.RunNode( args[0], args[1]).invoke()" +
            "\n\twhere args[0] is the host ip address and the node's ip determined automatically" +
            "\n\targs[1] is the ip address of the node being created, " +
            "\n\t\tused for testing and will be of form 127.0.0.?" +
            "\n\t\tand host ip (args[0]) will typically be 127.0.0.1"
    }
  }
}
