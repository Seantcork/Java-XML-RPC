#!/usr/bin/python

import xmlrpclib, sys

# A simple example XML-RPC client program.

if len(sys.argv) != 4:
    print "Usage: [server] [x] [y]"
    sys.exit()

hostname = sys.argv[1]
x = int(sys.argv[2])
y = int(sys.argv[3])

name = "http://" + hostname + ":8888"
server = xmlrpclib.Server(name)
answer = server.Sample.sumAndDifference(x, y)

print "Sum is " + str(answer[0])
print "Difference is " + str(answer[1])
