# 390R Final Presentation - Checkpoint 1

## Target Overview

FiveM is a popular modification (mod) for the game Grand Theft Auto V. Essentially it allows players to host custom servers with modded assets, scripted behavior, as well as other custom features. FiveM Client gives players a GUI client to browse, connect to, and play servers. The FiveM server allows developers to create scripts that interact with the FiveM framework to set up custom behavior to change the gameplay of the Grand Theft Auto V experience. When a player loads into a server the server will send scripts for the client to download and execute the Lua scripts on the client. There are several other features like an HTTP client, client side browsers (sadly up-to-date), as well as a console (which might be helpful in dynamic testing). 

The feature of most interest is the script execution from server to client which would allow for the possibility of remote code execution on the client from joining a server. There have already been [vulnerabilities in the past](https://back.engineering/26/09/2022/) from this feature and we can use other features like the FiveM console for dynamic testing before hand. The target is very complicated and has a very large attack surface but we will focus our project on what is most easily testable and documented to do some exploit hunting.

## Debug Environment

(TODO)

## Map Out the Codebase

## Plans For the Rest of the Project