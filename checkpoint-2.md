...

## Debugging FiveM - a painful experience

Our expectation from the last checkpoint unfortunately did not come to a good end.
When we try running the debug build, for unknown reasons the server does not send
back the authentication ticket. Even when the server is deployed locally by us,
the behavior is the same. We believe that the release build of FiveM has
specific signature/key to link through Steam and FiveM signal server. Since we
do not have them in our debug build, the connection process fails.

![FiveM Debug build error](./images/Screenshot%202023-05-07%20185543.png)

So we change to working directly with Release build instead, which we download
directly from [FiveM website](https://fivem.net/).
However, we unsuccessfully run FiveM through WinDbg or x64dbg,
because the process seems to aware of debugger and automatically exits shortly after starting up.
Nothing great happens when we switch to attaching process either.
For most processes, in few seconds after attaching, the debugger receives
0xC0000005 (EXCEPTION_ACCESS_VIOLATION) and cannot continue. We believe this
is the work of anti-debugger measures.
In the end, we could not figure out a workaround for accessing the memory,
and we decided to rely on observation from outside.