import re

translte = """[{ "trace": V8_Trace },
	{ "setTickFunction": V8_SetTickFunction },
	{ "setEventFunction": V8_SetEventFunction },
	{ "setCallRefFunction": V8_SetCallRefFunction },
	{ "setDeleteRefFunction": V8_SetDeleteRefFunction },
	{ "setDuplicateRefFunction": V8_SetDuplicateRefFunction },
	{ "canonicalizeRef": V8_CanonicalizeRef },
	{ "makeFunctionReference": V8_MakeFunctionReference },
	{ "getTickCount": V8_GetTickCount },
	{ "invokeNative": V8_InvokeNative<StringHashGetter> },
	{ "invokeNativeByHash": V8_InvokeNative<IntHashGetter> },
	{ "invokeNativeRaw": V8_InvokeNativeRaw },
	{ "snap": V8_Snap },
	{ "startProfiling": V8_StartProfiling },
	{ "stopProfiling": V8_StopProfiling },
	{ "setUnhandledPromiseRejectionFunction": V8_SetUnhandledPromiseRejectionRoutine },
	{ "submitBoundaryStart": V8_SubmitBoundaryStart },
	{ "submitBoundaryEnd": V8_SubmitBoundaryEnd },
	{ "setStackTraceFunction": V8_SetStackTraceRoutine },
	{ "pointerValueIntInitialized": V8_GetPointerField<V8MetaFields::PointerValueInt> },
	{ "pointerValueFloatInitialized": V8_GetPointerField<V8MetaFields::PointerValueFloat> },
	{ "pointerValueInt": V8_GetMetaField<V8MetaFields::PointerValueInt> },
	{ "pointerValueFloat": V8_GetMetaField<V8MetaFields::PointerValueFloat> },
	{ "pointerValueVector": V8_GetMetaField<V8MetaFields::PointerValueVector> },
	{ "returnResultAnyway": V8_GetMetaField<V8MetaFields::ReturnResultAnyway> },
	{ "resultAsInteger": V8_GetMetaField<V8MetaFields::ResultAsInteger> },
	{ "resultAsLong": V8_GetMetaField<V8MetaFields::ResultAsLong> },
	{ "resultAsFloat": V8_GetMetaField<V8MetaFields::ResultAsFloat> },
	{ "resultAsString": V8_GetMetaField<V8MetaFields::ResultAsString> },
	{ "resultAsVector": V8_GetMetaField<V8MetaFields::ResultAsVector> },
	{ "resultAsObject2": V8_GetMetaField<V8MetaFields::ResultAsObject> },
	{ "getResourcePath": V8_GetResourcePath }]"""

pattern = r":\s(\w+<\w+::\w+>|[\w:]+)"

# Use a capturing group to match the template function call
# and its arguments, and surround it with quotes in the
# replacement string
replacement = r': "\1"'

result = re.sub(pattern, replacement, translte)
print(result)

"""
[{ "trace": "V8_Trace" },
    { "setTickFunction": "V8_SetTickFunction" },
    { "setEventFunction": "V8_SetEventFunction" },
    { "setCallRefFunction": "V8_SetCallRefFunction" },
    { "setDeleteRefFunction": "V8_SetDeleteRefFunction" },
    { "setDuplicateRefFunction": "V8_SetDuplicateRefFunction" },
    { "canonicalizeRef": "V8_CanonicalizeRef" },
    { "makeFunctionReference": "V8_MakeFunctionReference" },
    { "getTickCount": "V8_GetTickCount" },
    { "invokeNative": "V8_InvokeNative<StringHashGetter>" },
    { "invokeNativeByHash": "V8_InvokeNative<IntHashGetter>" },
    { "invokeNativeRaw": "V8_InvokeNativeRaw" },
    { "snap": "V8_Snap" },
    { "startProfiling": "V8_StartProfiling" },
    { "stopProfiling": "V8_StopProfiling" },
    { "setUnhandledPromiseRejectionFunction": "V8_SetUnhandledPromiseRejectionRoutine" },
    { "submitBoundaryStart": "V8_SubmitBoundaryStart" },
    { "submitBoundaryEnd": "V8_SubmitBoundaryEnd" },
    { "setStackTraceFunction": "V8_SetStackTraceRoutine" },
    { "pointerValueIntInitialized": "V8_GetPointerField<V8MetaFields::PointerValueInt>" },
    { "pointerValueFloatInitialized": "V8_GetPointerField<V8MetaFields::PointerValueFloat>" },
    { "pointerValueInt": "V8_GetMetaField<V8MetaFields::PointerValueInt>" },
    { "pointerValueFloat": "V8_GetMetaField<V8MetaFields::PointerValueFloat>" },
    { "pointerValueVector": "V8_GetMetaField<V8MetaFields::PointerValueVector>" },
    { "returnResultAnyway": "V8_GetMetaField<V8MetaFields::ReturnResultAnyway>" },
    { "resultAsInteger": "V8_GetMetaField<V8MetaFields::ResultAsInteger>" },
    { "resultAsLong": "V8_GetMetaField<V8MetaFields::ResultAsLong>" },
    { "resultAsFloat": "V8_GetMetaField<V8MetaFields::ResultAsFloat>" },
    { "resultAsString": "V8_GetMetaField<V8MetaFields::ResultAsString>" },
    { "resultAsVector": "V8_GetMetaField<V8MetaFields::ResultAsVector>" },
    { "resultAsObject2": "V8_GetMetaField<V8MetaFields::ResultAsObject>" },
    { "getResourcePath": "V8_GetResourcePath" }].reduce((acc,e)=>`${acc}"${Object.values(e)[0]}",`,"(") + ")" 
"""