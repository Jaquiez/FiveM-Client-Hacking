def getInvokeCalls(max: Int=5): Unit = {
    def addRecurse(cur: Traversal[Method],count: Int){
        cur.foreach({res=>
            if(res.size > 0 && count<max){
                if(res.code.contains("NativeInvoke")){
                    print("NativeInvoke @ ")
                    print(res.filename)
                    print(":" )
                    print(res.lineNumber)
                    println()
                }
                addRecurse(res.callee,count+1)
            }
        })
    }
    addRecurse(cpg.method,0)
}
def showV8CallGraph(max: Int=10){
    val sources = List("V8_Trace","V8_SetTickFunction","V8_SetEventFunction","V8_SetCallRefFunction","V8_SetDeleteRefFunction","V8_SetDuplicateRefFunction","V8_CanonicalizeRef","V8_MakeFunctionReference","V8_GetTickCount","V8_InvokeNative<StringHashGetter>","V8_InvokeNative<IntHashGetter>","V8_InvokeNativeRaw","V8_Snap","V8_StartProfiling","V8_StopProfiling","V8_SetUnhandledPromiseRejectionRoutine","V8_SubmitBoundaryStart","V8_SubmitBoundaryEnd","V8_SetStackTraceRoutine","V8_GetPointerField<V8MetaFields::PointerValueInt>","V8_GetPointerField<V8MetaFields::PointerValueFloat>","V8_GetMetaField<V8MetaFields::PointerValueInt>","V8_GetMetaField<V8MetaFields::PointerValueFloat>","V8_GetMetaField<V8MetaFields::PointerValueVector>","V8_GetMetaField<V8MetaFields::ReturnResultAnyway>","V8_GetMetaField<V8MetaFields::ResultAsInteger>","V8_GetMetaField<V8MetaFields::ResultAsLong>","V8_GetMetaField<V8MetaFields::ResultAsFloat>","V8_GetMetaField<V8MetaFields::ResultAsString>","V8_GetMetaField<V8MetaFields::ResultAsVector>","V8_GetMetaField<V8MetaFields::ResultAsObject>","V8_GetResourcePath") 
    def recurseSearch(cur: Traversal[Method],count: Int){
        cur.filter(!_.name.contains("<operator>"))
            .foreach({res=>
            println(s"${"-"*(count+1)} ${res.name}")
            if(res.size > 0 && count<max){
                recurseSearch(res.callee,count+1)
            }
        })
    }
    cpg.method.filter{method=>sources.contains(method.name)}
        .foreach{res=>
            println(res.name)
            recurseSearch(res,0);
    }
}

def sink2src(max: Int=5) {
    val sinks = List("printf", "memcpy", "scanf","free","Delete","strcpy","malloc","calloc","execve","sprintf","vsprintf","gets","fgets","read")
    val sources = List("V8_Trace","V8_SetTickFunction","V8_SetEventFunction","V8_SetCallRefFunction","V8_SetDeleteRefFunction","V8_SetDuplicateRefFunction","V8_CanonicalizeRef","V8_MakeFunctionReference","V8_GetTickCount","V8_InvokeNative<StringHashGetter>","V8_InvokeNative<IntHashGetter>","V8_InvokeNativeRaw","V8_Snap","V8_StartProfiling","V8_StopProfiling","V8_SetUnhandledPromiseRejectionRoutine","V8_SubmitBoundaryStart","V8_SubmitBoundaryEnd","V8_SetStackTraceRoutine","V8_GetPointerField<V8MetaFields::PointerValueInt>","V8_GetPointerField<V8MetaFields::PointerValueFloat>","V8_GetMetaField<V8MetaFields::PointerValueInt>","V8_GetMetaField<V8MetaFields::PointerValueFloat>","V8_GetMetaField<V8MetaFields::PointerValueVector>","V8_GetMetaField<V8MetaFields::ReturnResultAnyway>","V8_GetMetaField<V8MetaFields::ResultAsInteger>","V8_GetMetaField<V8MetaFields::ResultAsLong>","V8_GetMetaField<V8MetaFields::ResultAsFloat>","V8_GetMetaField<V8MetaFields::ResultAsString>","V8_GetMetaField<V8MetaFields::ResultAsVector>","V8_GetMetaField<V8MetaFields::ResultAsObject>","V8_GetResourcePath")
    
    def recurseSearch(cur: Traversal[Method], count: Int): Boolean = {
    cur.filter(!_.name.contains("<operator>")).foreach { res =>
        if (res.size > 0 && count < max) {
        if (sinks.contains(res.name)) {
            println(s"${res.method.name} @ ${res.filename}:${res.lineNumber}")
            println("Call Stack:")
            println(s"  -> ${res.name}")
            return true
        } else {
            val found = recurseSearch(res.callee, count + 1)
            if (found) {
            println(s"  -> ${res.name}")
            return true
            }
        }
        }
    }
    false
    }


    cpg.method.filter{method=>sources.contains(method.name)}
        .foreach{res=>
            var found = recurseSearch(res,0)
            if(found){
                println(s"  -> ${res.name}")
            }
    }
}
