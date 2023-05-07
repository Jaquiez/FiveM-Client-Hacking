def getNativeInvokeCalls(max: Int=5): Unit = {
    var vec: Vector[(String, String,String)] = Vector()
    print("[")
    def addRecurse(cur: Traversal[Method],count: Int){
        cur.foreach({res=>
            if(res.size > 0 && count<max){
                if(res.fullName.contains("NativeInvoke.Invoke")){
                    print("{\"fullName\":\"")
                    print(res.fullName.replace("\"","'"))
                    print("\",\"filename\":\"")
                    print(res.filename)
                    print("\",\"lineNumber\":\"")
                    print(res.lineNumber)
                    print("\"},")
                }
                addRecurse(res.callee,count+1)
            }
        })
    }
    addRecurse(cpg.method,0)
    println("]")
}