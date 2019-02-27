JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Client.java \
        Front.java \
        Catalog.java \
        Order.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
