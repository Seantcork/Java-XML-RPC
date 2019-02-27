#!/usr/bin/python

import xmlrpclib, sys

# A simple example XML-RPC client program.


def main():
	if len(sys.argv) != 2:
	    print "Usage: [server]"
	    sys.exit()

	hostname = sys.argv[1]
	x = 1
	y = 2

	name = "http://" + hostname + ":8124"
	server = xmlrpclib.Server(name)
	welcome_reply = server.Front.welcome(x, y)
	print(str(welcome_reply))

	while(True):
		request = raw_input("Here are the available actions\nsearch topic\nlookup item_number\nbuy item_number\n")
		args = request.split(" ", 1)
		reply = server.Front.HandleRequest(args[0], args[1])
		print(reply)

	
main()