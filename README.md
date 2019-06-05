A Non Blocking Producer/Consumer stream processing service that exposes an HTTP api

This is a simple backed server parser from provided generator

In the Main.class file there are 3 variables to define
1. file_path = the full path of the generator itself
2. httpServerPort = the http server port (default = 3000)
3. urlPrefix = the url prefix for the http request from the server (default = "/getallevents")

when you run program type in the browser : "http://localhost:3000/getallevents" to see the parsed data from the generator
